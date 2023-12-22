package attendanceManagementSystem.ams.attendanceSheet;

import attendanceManagementSystem.ams.course.Course;
import attendanceManagementSystem.ams.faculty.Faculty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;


@Entity
@Table
public class AttendanceSheet
{
    @Id
    private String classId;
    @Convert(converter = JsonNodeConverter.class)

    @Column(columnDefinition = "json")
    private JsonNode jsonData;

    @ManyToOne
    @JoinColumn(name="facultyId")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    public AttendanceSheet()
    {
    }

    public AttendanceSheet(String classId) {
        this.classId = classId;
    }

    public AttendanceSheet(String classId, JsonNode jsonData, Faculty facultyId, Course courseId) {
        this.classId = classId;
        this.jsonData = jsonData;
        this.faculty = facultyId;
        this.course = courseId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public JsonNode getJsonData() {
        return jsonData;
    }

    public void setJsonData(JsonNode jsonData) {
        this.jsonData = jsonData;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
