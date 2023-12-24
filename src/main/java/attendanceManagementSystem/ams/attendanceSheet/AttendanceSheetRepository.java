package attendanceManagementSystem.ams.attendanceSheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import attendanceManagementSystem.ams.course.Course;



public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet, String> {

    @Query("SELECT DISTINCT a.classId, a.course.courseId, a.course.courseName FROM AttendanceSheet a WHERE a.faculty.facultyId = :facultyId")
    List<Object[]> findDistinctClassAndCoursesByFacultyId(@Param("facultyId") String facultyId);

}
