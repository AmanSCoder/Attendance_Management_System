package attendanceManagementSystem.ams.attendanceSheet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import attendanceManagementSystem.ams.student.Student;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping
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
    @GetMapping("attendance")
    public String AttendanceEnroll()
    {
        return "AttendanceEnroll";
    }
    @Transactional
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
    @GetMapping("attendance/get-all")
    public ResponseEntity<?> getAllAttendanceSheets()
    {
        Iterable<AttendanceSheet> attendanceSheets = attendanceSheetService.getAllAttendanceSheets();
        return ResponseEntity.ok(attendanceSheets);
    }

    
    @GetMapping("attendance/faculty-login")
    public String getFacultyInput() {
        return "FacultyLogin";
    }
    
    @GetMapping("attendance/faculty-courses")
    public String getFacultyCourses(@RequestParam(required = false) String facultyId, Model model) {
        // Your existing code
        List<Object[]> courses = attendanceSheetService.getCoursesForFaculty(facultyId);
        model.addAttribute("courses", courses);
        return "Faculty_Courses";
    }


    
    @GetMapping("attendance/attendance-dates")
    public String showAttendanceDates(@RequestParam String classId, @RequestParam String courseId, @RequestParam String facultyId, Model model) {
    	List<LocalDate> attendanceDates = attendanceSheetService.getAttendanceDates(classId, courseId);
        model.addAttribute("attendanceDates", attendanceDates);
        model.addAttribute("classId", classId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("facultyId",facultyId);
        return "AttendanceDates";
    }
    
    @GetMapping("attendance/mark-attendance")
    public String markAttendance(@RequestParam String classId,
                                 @RequestParam String courseId,
                                 @RequestParam String facultyId,
                                 @RequestParam String date,
                                 Model model)
    {
//        List<Student> studentList = attendanceSheetService.getStudentsForAttendance(classId, courseId, facultyId, LocalDate.parse(date));
        List<Student> studentList = attendanceSheetService.getStudentsForAttendance(classId);

        model.addAttribute("classId", classId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("facultyId", facultyId);
        model.addAttribute("date", date);
        model.addAttribute("studentList", studentList);
//        System.out.println(studentList);
        return "MarkAttendance";
    }
    

    @PostMapping("attendance/submit-attendance")
    public String submitAttendance(@RequestParam("classId") String classId,
                                   @RequestParam("courseId") String courseId,
                                   @RequestParam("facultyId") String facultyId,
                                   @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                   @RequestParam Map attendanceMap) throws SQLException {
        System.out.println(classId);
        System.out.println(courseId);
        System.out.println(facultyId);
        System.out.println(date);
        System.out.println(attendanceMap);
        for(var a:attendanceMap.keySet())
        {
            System.out.println(a+" "+a.getClass());
        }
        HashMap<String,String> attendance = new HashMap<>();
        attendance.put("a","Absent");
        System.out.println(attendanceMap);
//        attendanceSheetService.updateAttendance(classId ,date, attendance);
        return "Success"; // Redirect to a success page or back to the attendance page
    }

    @GetMapping("attendance/student-login")
    public String getStudentInput()
    {
        return "StudentLogin";
    }

//    @GetMapping("/student-page")
//    public ResponseEntity<?> getStudentClasses(@RequestParam String studentId)
//    {
//        Iterable<AttendanceSheet> classes=attendanceSheetService.getStudentClasses(studentId);
//        return ResponseEntity.ok(classes);
//    }

    @GetMapping("attendance/student-page")
    public String getStudentClasses(@RequestParam String studentId,Model model)
    {
        Iterable<AttendanceSheet> attendanceSheet=attendanceSheetService.getStudentClasses(studentId);
        model.addAttribute("attendanceSheet",attendanceSheet);
        model.addAttribute("studentId",studentId);
        return "Student_Page";
    }
    @GetMapping("attendance/student/course")
    public String getStudentCourse()
    {
        return "StudentCourseEntry";
    }


    @GetMapping("attendance/student/course-page")
    public String getStudentClassAttendance(@RequestParam("studentId") String studentId,
                                             @RequestParam("classId") String classId,
                                             Model model)
    {
        HashMap<LocalDate,String> attendanceByDate= attendanceSheetService.getStudentClassAttendance(studentId,classId);
        model.addAttribute("attendanceByDate",attendanceByDate);
        model.addAttribute("studentId",studentId);
        model.addAttribute("classId",classId);
        return "StudentAttendanceDisplay";
    }
    
    @GetMapping("admin/features")
    public String Admin() {
    	return "Admin";
    }
    
    @GetMapping("admin/login")
    	public String AdminLogin() {
    		return "AdminLogin";
    	}
    @PostMapping("admin/login")
    public String adminAuthenticate(@RequestParam("adminUser") String adminUser,
                                    @RequestParam("adminPassword") String adminPassword) {
    	System.out.print("done");
        // Add your logic to check admin credentials
        if ("user".equals(adminUser) && "password".equals(adminPassword)) {
            // Authentication successful
            return "Admin"; // Redirect to admin features page
        } else {
            // Authentication failed
            return "redirect:/admin/login?error"; // Redirect back to login page with an error parameter
        }
    }
    
    }



