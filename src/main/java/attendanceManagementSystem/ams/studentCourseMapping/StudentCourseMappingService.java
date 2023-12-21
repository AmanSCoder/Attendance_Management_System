package attendanceManagementSystem.ams.studentCourseMapping;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseMappingService
{
    private final StudentCourseMappingRepository studentCourseMappingRepository;
    @Autowired
    public StudentCourseMappingService(StudentCourseMappingRepository studentCourseMappingRepository)
    {
        this.studentCourseMappingRepository = studentCourseMappingRepository;
    }

}
