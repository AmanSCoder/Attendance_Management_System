package attendanceManagementSystem.ams.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public String addNewStudent(Student student)
    {
        Optional<Student> studentById=studentRepository.findById(student.getId());
        if(studentById.isPresent())
        {
            return "Failure";
//            throw new IllegalStateException("Registration Number already taken");
        }
        studentRepository.save(student);
        System.out.println(student);
        return "Success";
    }
}
