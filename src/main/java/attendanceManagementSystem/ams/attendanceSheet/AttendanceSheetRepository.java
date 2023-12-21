package attendanceManagementSystem.ams.attendanceSheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet,String>, CrudRepository<AttendanceSheet, String>
{

}
