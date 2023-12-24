package attendanceManagementSystem.ams.student;

import jakarta.persistence.*;

@Entity
@Table
public class Student
{
    @Id
//    @SequenceGenerator(name="student_sequence",
//            sequenceName = "student_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "student_sequence"
//    )
    private String studentId;
    private String studentName;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }
    public Student(String studentId)
    {
        this.studentId=studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + studentId + '\'' +
                ", name='" + studentName + '\'' +
                '}';
    }

    public String getStudentName() {
        return studentName;
    }

    public Student() {
    }
}
