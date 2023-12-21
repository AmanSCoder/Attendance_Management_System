package attendanceManagementSystem.ams.studentCourseMapping;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseMappingService
{
    private final StudentCourseMappingRepository studentCourseMappingRepository;
    @Autowired
    public StudentCourseMappingService(StudentCourseMappingRepository studentCourseMappingRepository)
    {
        this.studentCourseMappingRepository = studentCourseMappingRepository;
    }

    public void addNewMapping(List<String> studentList, String courseId, String facultyId)
    {
        List<StudentCourseMapping> studentCourseMapping=new ArrayList<>();
        for(String student:studentList)
        {
            studentCourseMapping.add(new StudentCourseMapping(student,courseId,facultyId));
        }
        studentCourseMappingRepository.saveAll(studentCourseMapping);
    }
}
