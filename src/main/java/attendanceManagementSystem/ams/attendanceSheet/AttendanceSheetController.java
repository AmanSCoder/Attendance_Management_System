package attendanceManagementSystem.ams.attendanceSheet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import jakarta.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import attendanceManagementSystem.ams.course.Course;
import attendanceManagementSystem.ams.faculty.Faculty;

@Controller
@RequestMapping("attendance")
public class AttendanceSheetController
{
    private final AttendanceSheetService attendanceSheetService;
    private HashMap<LocalDate, HashMap<String,String>> json=new HashMap<>();
    private HashMap<String,String> innerValue=new HashMap<>();
    private List<String> studentList;
    private List<LocalDate> dateList;


    @Autowired
    public AttendanceSheetController(AttendanceSheetService attendanceSheetService)
    {
        this.attendanceSheetService = attendanceSheetService;
    }
    @GetMapping
    public String AttendanceEnroll()
    {
        return "AttendanceEnroll";
    }
    @PostMapping("success")
    public String registerNewAttendanceSheet(@RequestParam("classId") String classId,
                                             @RequestParam("courseId") String courseId,
                                             @RequestParam("facultyId") String facultyId,
                                             @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                             @RequestParam("daysOfWeek") List<String> daysOfWeek,
                                             @RequestParam("studentRegNos") String studentRegNos,
                                             RedirectAttributes redirectAttributes) throws JsonProcessingException, SQLException
    {
        studentList=attendanceSheetService.getStudentList(studentRegNos);
        dateList=attendanceSheetService.getDateList(startDate,endDate,daysOfWeek);
        innerValue=attendanceSheetService.getInnerValue(studentList);
        json=attendanceSheetService.getJson(dateList,innerValue);
        ObjectMapper objectMapper=new ObjectMapper();
        if(attendanceSheetService.checkStudentList(studentList))
        {
            attendanceSheetService.addNewAttendanceSheet(new AttendanceSheet(classId, objectMapper.valueToTree(json), new Faculty(facultyId), new Course(courseId)));

            redirectAttributes.addFlashAttribute("studentList", studentList);
            redirectAttributes.addFlashAttribute("classId", classId);
            return "redirect:/studentmapping/success";
        }
        return "Failure";
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAttendanceSheets()
    {
        Iterable<AttendanceSheet> attendanceSheets = attendanceSheetService.getAllAttendanceSheets();
        return ResponseEntity.ok(attendanceSheets);
    }

    
    @GetMapping("/faculty-login")
    public String getFacultyInput() {
        return "FacultyLogin";
    }
    
    @GetMapping("/faculty-courses")
    public String getFacultyCourses(@RequestParam String facultyId, Model model) {
        List<Object[]> courses = attendanceSheetService.getCoursesForFaculty(facultyId);
        model.addAttribute("courses", courses);
        return "Faculty_Courses";
    }
    @GetMapping("/student-login")
    public String getStudentInput()
    {
        return "StudentLogin";
    }

    @GetMapping("/student-page")
    public ResponseEntity<?> getStudentClasses(@RequestParam String studentId)
    {
        Iterable<AttendanceSheet> classes=attendanceSheetService.getStudentClasses(studentId);
        return ResponseEntity.ok(classes);
    }


}
