package attendanceManagementSystem.ams.attendanceSheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import attendanceManagementSystem.ams.course.Course;

public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet,String>, CrudRepository<AttendanceSheet, String>
{
	 @Query("SELECT DISTINCT a.course FROM AttendanceSheet a WHERE a.faculty.facultyId = :facultyId")
	    List<Course> findDistinctCoursesByFacultyId(@Param("facultyId") String facultyId);
}
