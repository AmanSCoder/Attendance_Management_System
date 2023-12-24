package attendanceManagementSystem.ams.faculty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Faculty
{
    @Id
    private String facultyId;
    private String facultyName;

    public String getFacultyId() {
        return facultyId;
    }

    public void setfacultyId(String facultyId) {
        this.facultyId = facultyId;
    }


    public String getName() {
        return facultyName;
    }

    public void setName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Faculty(String facultyId, String facultyName) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }

    public Faculty() {
    }
    public Faculty(String facultyId) {
        this.facultyId=facultyId;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + facultyId + '\'' +
                ", name='" + facultyName + '\'' +
                '}';
    }
}
