package attendanceManagementSystem.ams.attendanceSheet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import attendanceManagementSystem.ams.student.Student;
import attendanceManagementSystem.ams.student.StudentRepository;
import attendanceManagementSystem.ams.studentMapping.StudentMappingRepository;

        @Service
        public class AttendanceSheetService {
            private final AttendanceSheetRepository attendanceSheetRepository;
            private final StudentRepository studentRepository;
            private final StudentMappingRepository studentMappingRepository;

            @Autowired
            public AttendanceSheetService(AttendanceSheetRepository attendanceSheetRepository,
                                          StudentRepository studentRepository,
                                          StudentMappingRepository studentMappingRepository) {
                this.attendanceSheetRepository = attendanceSheetRepository;
                this.studentRepository=studentRepository;
                this.studentMappingRepository=studentMappingRepository;
            }


            public void addNewAttendanceSheet(AttendanceSheet attendanceSheet) throws SQLException
            {
//                System.out.println(attendanceSheet.getClassId());
//                System.out.println(attendanceSheet.getCourse().getId());
//                System.out.println(attendanceSheet.getFaculty().getId());
//                System.out.println(attendanceSheet.getJsonData());
//                attendanceSheetRepository.save(attendanceSheet);

                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/attendancesystem", "postgres", "Abd@69877");
                String sql="insert into attendance_sheet values (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
               /* statement.setObject(1,attendanceSheet.getClassId());
                statement.setObject(2,attendanceSheet.getCourse().getCourseId());
                statement.setObject(3,attendanceSheet.getFaculty().getFacultyId());
                statement.setObject(4,attendanceSheet.getJsonData(),Types.OTHER);*/
                statement.setString(1, attendanceSheet.getClassId());
                statement.setString(2, attendanceSheet.getCourse().getCourseId());
                statement.setString(3, attendanceSheet.getFaculty().getFacultyId());
                JsonNode jsonData = attendanceSheet.getJsonData();
                PGobject pgObject = new PGobject();
                pgObject.setType("json");
                pgObject.setValue(attendanceSheet.getJsonData().toString());
                statement.setObject(4, pgObject);


                int r=statement.executeUpdate();
                System.out.println(r);

      }

    public List<String> getStudentList(String studentRegNos) {
        return Arrays.asList(studentRegNos.split(","));
    }

    public List<LocalDate> getDateList(LocalDate startDate, LocalDate endDate, List<String> daysOfWeek) {
        List<LocalDate> dateList = new ArrayList<>();
        for (String day : daysOfWeek) {
            switch (day) {
                case "Monday" -> dateList.addAll(listDaysBetweenDates(startDate, endDate, DayOfWeek.MONDAY));
                case "Tuesday" -> dateList.addAll(listDaysBetweenDates(startDate, endDate, DayOfWeek.TUESDAY));
                case "Wednesday" -> dateList.addAll(listDaysBetweenDates(startDate, endDate, DayOfWeek.WEDNESDAY));
                case "Thursday" -> dateList.addAll(listDaysBetweenDates(startDate, endDate, DayOfWeek.THURSDAY));
                case "Friday" -> dateList.addAll(listDaysBetweenDates(startDate, endDate, DayOfWeek.FRIDAY));
            }
        }
        Collections.sort(dateList);
        return dateList;
    }

    private static List<LocalDate> listDaysBetweenDates(LocalDate startDate, LocalDate endDate, DayOfWeek dayOfWeek) {
        LocalDate current = startDate;
        List<LocalDate> dateListByDay = new ArrayList<>();
        while (current.isBefore(endDate) || current.isEqual(endDate)) {
            if (current.getDayOfWeek() == dayOfWeek) {
                dateListByDay.add(current);
            }
            current = current.plusDays(1);
        }
        return dateListByDay;
    }

    public HashMap<String, String> getInnerValue(List<String> studentList)
    {
        HashMap<String,String> innerValue=new HashMap<>();
        for(String student:studentList)
        {
            innerValue.put(student,"Not Marked");
        }
        return innerValue;
    }

    public HashMap<LocalDate, HashMap<String, String>> getJson(List<LocalDate> dateList, HashMap<String, String> innerValue)
    {
        HashMap<LocalDate,HashMap<String,String>> json=new HashMap<>();
        for(LocalDate localDate:dateList)
        {
            json.put(localDate,innerValue);
        }
        return json;
    }


    public Iterable<AttendanceSheet> getAllAttendanceSheets()
    {
        return attendanceSheetRepository.findAll();
    }

    public boolean checkStudentList(List<String> studentList)
    {
        for(String student:studentList)
        {
            Optional< Student> studentById=studentRepository.findById(student);
            if(!(studentById.isPresent()))
            {
                return false;
            }
        }
        return true;
    }


    public List<Object[]> getCoursesForFaculty(String facultyId) {
        return attendanceSheetRepository.findDistinctClassAndCoursesByFacultyId(facultyId);
    }


    public List<LocalDate> getAttendanceDates(String classId, String courseId) {
        Optional<AttendanceSheet> optionalAttendanceSheet = attendanceSheetRepository.findById(classId);

        if (optionalAttendanceSheet.isPresent()) {
            AttendanceSheet attendanceSheet = optionalAttendanceSheet.get();

            if (attendanceSheet.getCourse().getCourseId().equals(courseId)) {
                JsonNode jsonData = attendanceSheet.getJsonData();
                Iterator<Map.Entry<String, JsonNode>> fields = jsonData.fields();
                
                List<LocalDate> dateList = new ArrayList<>();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    LocalDate date = LocalDate.parse(entry.getKey());
                    dateList.add(date);
                }
                
                return dateList;
            }
        }

        return Collections.emptyList();
    }

    public List<Student> getStudentsForAttendance(String classId, String courseId, String facultyId, LocalDate date) {
        Optional<AttendanceSheet> attendanceSheetOptional = attendanceSheetRepository.findById(classId);

        if (attendanceSheetOptional.isPresent()) {
            AttendanceSheet attendanceSheet = attendanceSheetOptional.get();
            if (attendanceSheet.getCourse().getCourseId().equals(courseId) &&
                    attendanceSheet.getFaculty().getFacultyId().equals(facultyId)) {

                // Get the JsonNode from the entity
                JsonNode jsonData = attendanceSheet.getJsonData();

                // Extract students for the given date from the JsonNode
                List<String> studentIds = extractStudentsForDate(jsonData, date);

                // Fetch Student objects based on the student IDs
                List<Student> students = studentRepository.findAllById(studentIds);

                return students;
            }
        }

        return Collections.emptyList();
    }


    private List<String> extractStudentsForDate(JsonNode jsonData, LocalDate date) {
        List<String> studentsForDate = new ArrayList<>();

        // Check if the date exists in the JsonNode
        if (jsonData != null && jsonData.has(date.toString())) {
            JsonNode dateData = jsonData.get(date.toString());

            // Iterate over students for the given date and add them to the list
            dateData.fields().forEachRemaining(entry -> {
                String studentId = entry.getKey();
                String attendanceStatus = entry.getValue().asText();
                // Add your logic here to process the student details as needed
                studentsForDate.add(studentId + ": " + attendanceStatus);
            });
        }

        return studentsForDate;
    }





    public void updateAttendance(String classId, String courseId, String facultyId,
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                 Map<String, String> attendanceMap) {
        Optional<AttendanceSheet> attendanceSheetOptional = attendanceSheetRepository.findById(classId);

        if (attendanceSheetOptional.isPresent()) {
            AttendanceSheet attendanceSheet = attendanceSheetOptional.get();
            if (attendanceSheet.getCourse().getCourseId().equals(courseId) &&
                    attendanceSheet.getFaculty().getFacultyId().equals(facultyId)) {

                JsonNode jsonData = attendanceSheet.getJsonData();
                AtomicReference<ObjectNode> dateDataRef = new AtomicReference<>();

                // Check if the date exists in the JSON data
                if (jsonData != null) {
                    if (jsonData.has(date.toString())) {
                        dateDataRef.set((ObjectNode) jsonData.get(date.toString()));
                    } else {
                        // If the date doesn't exist, create a new entry
                        dateDataRef.set(JsonNodeFactory.instance.objectNode());
                        ((ObjectNode) jsonData).set(date.toString(), dateDataRef.get());
                    }

                    // Update attendance status for each student
                    attendanceMap.forEach((studentId, status) -> dateDataRef.get().put(studentId, status));

                    // Save the updated attendance sheet
                    attendanceSheetRepository.save(attendanceSheet);
                }
            }
        }
    }

    
    public Map<String, String> getAttendanceData(String classId, String facultyId, String courseId, String date) {
        // Execute the SQL query
        String jsonData = attendanceSheetRepository.getJsonData(classId, facultyId, courseId, date);

        // Convert JSON data to HashMap
        return convertJsonToHashMap(jsonData);
    }
    
    private Map<String, String> convertJsonToHashMap(String jsonData) {
        Map<String, String> resultMap = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            // Assuming your JSON structure is like {"studentId": "attendanceStatus"}
            jsonNode.fields().forEachRemaining(entry -> resultMap.put(entry.getKey(), entry.getValue().asText()));
        } catch (IOException e) {
            // Handle exception or log it
            e.printStackTrace();
        }

        return resultMap;
    }

    public Iterable<AttendanceSheet> getStudentClasses(String studentId)
    {
        return studentMappingRepository.findDistinctClassIdByStudentId(studentId);

    }
}
