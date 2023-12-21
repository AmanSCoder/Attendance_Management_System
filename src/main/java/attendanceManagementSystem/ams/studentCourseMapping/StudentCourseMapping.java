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
    private String courseId;
    private String facultyId;

    public StudentCourseMapping()
    {
    }

    public StudentCourseMapping(String studentId, String courseId, String facultyId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.facultyId = facultyId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

}
