package attendanceManagementSystem.ams.AttendanceSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AttendanceSheetService {
    private final AttendanceSheetRepository attendanceSheetRepository;

    @Autowired

    public AttendanceSheetService(AttendanceSheetRepository attendanceSheetRepository) {
        this.attendanceSheetRepository = attendanceSheetRepository;
    }


    public void addNewAttendanceSheet(AttendanceSheet attendanceSheet) throws SQLException
    {
//        System.out.println(attendanceSheet.getClassId());
//        System.out.println(attendanceSheet.getCourseId());
//        System.out.println(attendanceSheet.getFacultyId());
//        System.out.println(attendanceSheet.getJsonData());
//        attendanceSheetRepository.save(attendanceSheet);

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ams", "postgres", "Aman%9889");
        String sql="insert into attendance_sheet values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,attendanceSheet.getClassId());
        statement.setObject(2,attendanceSheet.getCourseId());
        statement.setObject(3,attendanceSheet.getFacultyId());
        statement.setObject(4,attendanceSheet.getJsonData(), Types.OTHER);

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
}
