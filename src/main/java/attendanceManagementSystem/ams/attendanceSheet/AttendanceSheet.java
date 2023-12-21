package attendanceManagementSystem.ams.attendanceSheet;

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

    private String facultyId;
    private String courseId;

    public AttendanceSheet()
    {
    }

    public AttendanceSheet(String classId, JsonNode jsonData, String facultyId, String courseId) {
        this.classId = classId;
        this.jsonData = jsonData;
        this.facultyId = facultyId;
        this.courseId = courseId;
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

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}
