package attendanceManagementSystem.ams.studentCourseMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentCourseMappingController
{
    private final StudentCourseMappingService studentCourseMappingService;
    @Autowired

    public StudentCourseMappingController(StudentCourseMappingService studentCourseMappingService)
    {
        this.studentCourseMappingService = studentCourseMappingService;
    }

}
