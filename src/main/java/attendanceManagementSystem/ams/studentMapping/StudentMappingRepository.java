package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import attendanceManagementSystem.ams.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentMappingRepository extends JpaRepository<StudentMapping,String>, CrudRepository<StudentMapping,String>
{
//    @Query("SELECT sm FROM StudentMapping sm WHERE CAST(sm.student_id AS TEXT) = :student_id")
//    List<StudentMapping> findByStudentId(@Param("student_id") Student student_id);

//    @Query("select distinct sm from StudentMapping sm where sm.student.studentId=:studentId")
//    List<StudentMapping> findByStudentId(@Param("studentId") String studentId);
}
