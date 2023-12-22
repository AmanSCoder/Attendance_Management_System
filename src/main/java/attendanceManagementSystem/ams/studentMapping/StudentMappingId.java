package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import attendanceManagementSystem.ams.student.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class StudentMappingId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private AttendanceSheet attendanceSheet;

    public StudentMappingId() {
    }

    public StudentMappingId(Student student, AttendanceSheet attendanceSheet) {
        this.student = student;
        this.attendanceSheet = attendanceSheet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendanceSheet getAttendanceSheet() {
        return attendanceSheet;
    }

    public void setAttendanceSheet(AttendanceSheet attendanceSheet) {
        this.attendanceSheet = attendanceSheet;
    }
}







//package attendanceManagementSystem.ams.studentMapping;
//
//import attendanceManagementSystem.ams.student.Student;
//import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//public class StudentMappingId implements Serializable {
//    private Student student;
//    private AttendanceSheet attendanceSheet;

//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StudentMappingId that = (StudentMappingId) o;
//        return Objects.equals(student, that.student) &&
//                Objects.equals(attendanceSheet, that.attendanceSheet);
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(student, attendanceSheet);
//    }
//
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
