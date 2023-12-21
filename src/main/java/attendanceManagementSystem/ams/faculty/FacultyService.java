package attendanceManagementSystem.ams.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService
{
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository)
    {
        this.facultyRepository = facultyRepository;
    }

    public String addNewStudent(Faculty faculty)
    {
        Optional<Faculty> facultyById=facultyRepository.findById(faculty.getId());
        if(facultyById.isPresent())
        {
            return "Failure";
        }
        facultyRepository.save(faculty);
        return "Success";
    }
}
