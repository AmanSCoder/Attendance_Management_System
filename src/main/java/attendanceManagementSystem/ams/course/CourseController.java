package attendanceManagementSystem.ams.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("course")
public class CourseController
{
    private final CourseService courseService;
    @Autowired

    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }
    @GetMapping
    public String courseEnroll()
    {
        return "CourseEnroll";
    }
    @PostMapping("success")
    public String registerNewCourse(@RequestParam String courseId,
                                    @RequestParam String courseName)
    {
        Course course=new Course(courseId,courseName);
        return courseService.addNewCourse(course);
    }

}
