package attendanceManagementSystem.ams.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CourseService
{
    private final CourseRepository courseRepository;
    @Autowired

    public CourseService(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public String addNewCourse(Course course)
    {
        Optional<Course> courseById=courseRepository.findById(course.getId());
        if(courseById.isPresent())
        {
            return "Failure";
        }
        courseRepository.save(course);
        return "Success";
    }
}
