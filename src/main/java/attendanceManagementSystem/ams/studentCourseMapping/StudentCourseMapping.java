package attendanceManagementSystem.ams.studentCourseMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table
public class StudentCourseMapping
{
    @Id
    private String studentId;
    private String classId;

    public StudentCourseMapping() {
    }

    public StudentCourseMapping(String studentId, String classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
