package attendanceManagementSystem.ams.faculty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Faculty
{
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty() {
    }
    public Faculty(String id) {
        this.id=id;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
