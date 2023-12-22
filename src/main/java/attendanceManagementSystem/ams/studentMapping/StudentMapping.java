package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import attendanceManagementSystem.ams.student.Student;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "student_mapping")
public class StudentMapping {
    @EmbeddedId
    private StudentMappingId id;


    public StudentMapping() {
        this.id = new StudentMappingId();
    }

    public StudentMapping(Student student, AttendanceSheet attendanceSheet) {
        this.id = new StudentMappingId();
        this.id.setStudent(student);
        this.id.setAttendanceSheet(attendanceSheet);
    }

    @ManyToOne
    @JoinColumn(name="student_id",insertable = false, updatable = false)
    public Student getStudent() {
        return id.getStudent();
    }

    public void setStudent(Student student) {
        this.id.setStudent(student);
    }

    @ManyToOne
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    public AttendanceSheet getAttendanceSheet() {
        return id.getAttendanceSheet();
    }

    public void setAttendanceSheet(AttendanceSheet attendanceSheet) {
        this.id.setAttendanceSheet(attendanceSheet);
    }

}












//package attendanceManagementSystem.ams.studentMapping;
//
//import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
//import attendanceManagementSystem.ams.student.Student;
//import jakarta.persistence.*;
//
//@Entity
//@Table
//@IdClass(StudentMappingId.class) // Composite primary key class
//public class StudentMapping {
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "studentId")
//    private Student student;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "classId")
//    private AttendanceSheet attendanceSheet;
//
//    public StudentMapping() {
//    }
//
//    public StudentMapping(Student student, AttendanceSheet attendanceSheet) {
//        this.student = student;
//        this.attendanceSheet = attendanceSheet;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public AttendanceSheet getAttendanceSheet() {
//        return attendanceSheet;
//    }
//
//    public void setAttendanceSheet(AttendanceSheet attendanceSheet) {
//        this.attendanceSheet = attendanceSheet;
//    }
//}
//




//package attendanceManagementSystem.ams.studentMapping;
//
//import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
//import jakarta.persistence.*;
//
//
//@Entity
//@Table
//@IdClass(StudentMappingId.class)
//public class StudentMapping
//{
//    @Id
//    private String studentId;
//    @Id
//    @ManyToOne
//    @JoinColumn(name="classId")
//    private AttendanceSheet attendanceSheet;
//
//    public StudentMapping() {
//    }
//
//    public StudentMapping(String studentId, AttendanceSheet attendanceSheet) {
//        this.studentId = studentId;
//        this.attendanceSheet = attendanceSheet;
//    }
//
//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }
//
//    public AttendanceSheet getAttendanceSheet() {
//        return attendanceSheet;
//    }
//
//    public void setAttendanceSheet(AttendanceSheet attendanceSheet) {
//        this.attendanceSheet = attendanceSheet;
//    }
//}
