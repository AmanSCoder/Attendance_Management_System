package attendanceManagementSystem.ams.studentMapping;

import attendanceManagementSystem.ams.attendanceSheet.AttendanceSheet;
import attendanceManagementSystem.ams.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentMappingRepository extends JpaRepository<StudentMapping,StudentMappingId>, CrudRepository<StudentMapping,StudentMappingId>
{
    @Query("SELECT sm.id.attendanceSheet FROM StudentMapping sm WHERE sm.id.student.studentId=:studentId")
    Iterable<AttendanceSheet> findDistinctClassIdByStudentId(@Param("studentId") String studentId);

    @Query("select sm.id.student from StudentMapping sm where sm.id.attendanceSheet.classId=:classId")
    List<Student> findStudentByClassId(@Param("classId") String classId);

//    @Query("SELECT sm.id.attendanceSheet FROM StudentMapping sm WHERE sm.id.student.studentId=:studentId AND sm.id.attendanceSheet.classId=:classId")
//    Iterable<AttendanceSheet> findAttendanceByStudentIdAndClassId(@Param("studentId") String studentId,@Param("courseId") String classId);

}
