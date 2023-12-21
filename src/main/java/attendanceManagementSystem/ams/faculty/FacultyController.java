package attendanceManagementSystem.ams.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("faculty")
public class FacultyController
{
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping
    public String facultyEnroll()
    {
        return "FacultyEnroll";
    }
    @PostMapping("success")
    public String registerNewFaculty(@RequestParam String facultyId,
                                   @RequestParam String facultyName)
    {
        Faculty faculty=new Faculty(facultyId,facultyName);
        return facultyService.addNewStudent(faculty);
    }

}
