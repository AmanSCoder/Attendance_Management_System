package attendanceManagementSystem.ams.studentCourseMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("studentcoursemapping")
public class StudentCourseMappingController
{
    private final StudentCourseMappingService studentCourseMappingService;
    @Autowired

    public StudentCourseMappingController(StudentCourseMappingService studentCourseMappingService)
    {
        this.studentCourseMappingService = studentCourseMappingService;
    }

    @GetMapping("success")
    public String registerNewMapping(@ModelAttribute("studentList") List<String> studentList,
                                     @ModelAttribute("courseId") String courseId,
                                     @ModelAttribute("facultyId") String facultyId)
    {
        studentCourseMappingService.addNewMapping(studentList,courseId,facultyId);
        return "Success";
    }

}
