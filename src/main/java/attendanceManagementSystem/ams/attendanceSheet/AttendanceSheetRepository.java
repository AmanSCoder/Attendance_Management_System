package attendanceManagementSystem.ams.attendanceSheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import attendanceManagementSystem.ams.student.Student;



public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet, String> {

    @Query("SELECT DISTINCT a.classId, a.course.courseId, a.course.courseName, a.faculty.facultyId FROM AttendanceSheet a WHERE a.faculty.facultyId = :facultyId")
    List<Object[]> findDistinctClassAndCoursesByFacultyId(@Param("facultyId") String facultyId);

    
    @Query("SELECT a.jsonData FROM AttendanceSheet a WHERE a.classId = :classId AND a.faculty.facultyId = :facultyId AND a.course.courseId = :courseId AND FUNCTION('jsonb_extract_path_text', a.jsonData, :date) IS NOT NULL")
    String getJsonData(@Param("classId") String classId, @Param("facultyId") String facultyId, @Param("courseId") String courseId, @Param("date") String date);

}
