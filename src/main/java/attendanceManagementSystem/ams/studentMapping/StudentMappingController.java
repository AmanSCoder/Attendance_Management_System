package attendanceManagementSystem.ams.studentMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("studentmapping")
public class StudentMappingController
{
    private final StudentMappingService studentMappingService;
    @Autowired
    public StudentMappingController(StudentMappingService studentMappingService)
    {
        this.studentMappingService = studentMappingService;
    }

    @GetMapping("success")
    public String registerNewMapping(@ModelAttribute("studentList") List<String> studentList,
                                     @ModelAttribute("classId") String classId)
    {
        studentMappingService.addNewMapping(studentList,classId);
        return "Success";
    }
    @GetMapping("get-all")
    public String getAllMapping(Model model)
    {
        List<StudentMapping> mappings = studentMappingService.getAllMapping();
        model.addAttribute("mappings", mappings);
        return "StudentMappingGet";
    }


}
