package attendanceManagementSystem.ams.AttendanceSheet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("attendance")
public class AttendanceSheetController
{
    private final AttendanceSheetService attendanceSheetService;
    private HashMap<LocalDate, HashMap<String,String>> json=new HashMap<>();
    private HashMap<String,String> innerValue=new HashMap<>();
    private List<String> studentList;
    private List<LocalDate> dateList;


    @Autowired
    public AttendanceSheetController(AttendanceSheetService attendanceSheetService)
    {
        this.attendanceSheetService = attendanceSheetService;
    }
    @GetMapping
    public String AttendanceEnroll()
    {
        return "AttendanceEnroll";
    }
    @PostMapping("success")
    public String registerNewAttendanceSheet(@RequestParam("classId") String classId,
                                             @RequestParam("courseId") String courseId,
                                             @RequestParam("facultyId") String facultyId,
                                             @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                             @RequestParam("daysOfWeek") List<String> daysOfWeek,
                                             @RequestParam("studentRegNos") String studentRegNos) throws JsonProcessingException, SQLException
    {
        studentList=attendanceSheetService.getStudentList(studentRegNos);
        dateList=attendanceSheetService.getDateList(startDate,endDate,daysOfWeek);
        innerValue=attendanceSheetService.getInnerValue(studentList);
        json=attendanceSheetService.getJson(dateList,innerValue);
        ObjectMapper objectMapper=new ObjectMapper();
        attendanceSheetService.addNewAttendanceSheet(new AttendanceSheet(classId,objectMapper.valueToTree(json),facultyId,courseId));

        return "Success";
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAttendanceSheets()
    {
        Iterable<AttendanceSheet> attendanceSheets = attendanceSheetService.getAllAttendanceSheets();
        return ResponseEntity.ok(attendanceSheets);
    }


}
