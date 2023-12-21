package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import attendanceManagementSystem.ams.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentMappingService
{
    private final StudentMappingRepository studentMappingRepository;
    @Autowired
    public StudentMappingService(StudentMappingRepository studentMappingRepository)
    {
        this.studentMappingRepository = studentMappingRepository;
    }

    public void addNewMapping(List<String> studentList, String classId)
    {
        List<StudentMapping> studentMapping =new ArrayList<>();
        for(String studentId:studentList)
        {
            studentMapping.add(new StudentMapping(studentId,new AttendanceSheet(classId)));
        }
        studentMappingRepository.saveAll(studentMapping);
    }

    public List<StudentMapping> getAllMapping()
    {
        return studentMappingRepository.findAll();
    }
}
