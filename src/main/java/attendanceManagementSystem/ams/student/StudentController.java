package attendanceManagementSystem.ams.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController
{
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService=studentService;
    }

//    @GetMapping
//    public List<Student> getStudents()
//    {
//        return studentService.getStudents();
//    }
    @GetMapping()
    public String student_reg()
    {
        return "StudentEnroll";
    }
//    @GetMapping("success")
//    public String studentP()
//    {
//        return "studentP";
//    }
    @PostMapping("success")
    public String registerNewStudent(@RequestParam String registrationNumber,
                                     @RequestParam String studentName)
    {
        Student student=new Student(registrationNumber,studentName);

        return studentService.addNewStudent(student);
    }
}
