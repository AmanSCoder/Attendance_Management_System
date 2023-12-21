package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import jakarta.persistence.*;


@Entity
@Table
public class StudentMapping
{
    @Id
    private String studentId;
    @ManyToOne
    @JoinColumn(name="classId")
    private AttendanceSheet attendanceSheet;

    public StudentMapping() {
    }

    public StudentMapping(String studentId, AttendanceSheet attendanceSheet) {
        this.studentId = studentId;
        this.attendanceSheet = attendanceSheet;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public AttendanceSheet getAttendanceSheet() {
        return attendanceSheet;
    }

    public void setAttendanceSheet(AttendanceSheet attendanceSheet) {
        this.attendanceSheet = attendanceSheet;
    }
}
