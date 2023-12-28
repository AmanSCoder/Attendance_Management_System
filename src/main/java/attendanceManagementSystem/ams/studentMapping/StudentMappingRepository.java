package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface StudentMappingRepository extends JpaRepository<StudentMapping,StudentMappingId>, CrudRepository<StudentMapping,StudentMappingId>
{
    @Query("SELECT sm.id.attendanceSheet FROM StudentMapping sm WHERE sm.id.student.studentId=:studentId")
    Iterable<AttendanceSheet> findDistinctClassIdByStudentId(@Param("studentId") String studentId);
}
