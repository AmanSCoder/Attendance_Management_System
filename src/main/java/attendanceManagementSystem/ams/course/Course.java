package attendanceManagementSystem.ams.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table()
public class Course
{
    @Id
    private String courseId;
    private String courseName;


    public Course() {
    }
    public Course(String courseId) {
        this.courseId=courseId;
    }

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + courseId + '\'' +
                ", name='" + courseName + '\'' +
                '}';
    }
}
