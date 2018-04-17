package acacia.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface StudentsResultsDAO{
	@SqlQuery("SELECT frames FROM students_results "
			+ "WHERE student_id = "
			+ "(SELECT id FROM students WHERE onto_id = :student_id) "
			+ "AND lesson_id = "
			+ "(SELECT lesson_id FROM onto_sessions WHERE onto_id = :session_id);")
	String result(@Bind("student_id") String student_id, @Bind("session_id") String session_id);

}
