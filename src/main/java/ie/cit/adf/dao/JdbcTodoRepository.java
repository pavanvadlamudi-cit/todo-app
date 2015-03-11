package ie.cit.adf.dao;

import ie.cit.adf.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcTodoRepository implements TodoRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTodoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Todo todo) {
		jdbcTemplate.update("insert into todo(id, text, done) values(?,?,?)",
				todo.getId(), todo.getText(), todo.isDone());

	}

	public List<Todo> getAll() {
		return jdbcTemplate.query("select id, text, done from todo",
				new TodoRowMapper());
	}

	public void delete(String id) {
		jdbcTemplate.update("delete from todo where id=?", id);

	}

	public Todo findById(String id) {
		return jdbcTemplate.queryForObject(
				"select id, text, done from todo where id=?",
				new TodoRowMapper(), id);
	}

	public void update(Todo todo) {
		jdbcTemplate.update("update todo set text=?, done=? where id=?",
				todo.getText(), todo.isDone(), todo.getId());
	}
}

class TodoRowMapper implements RowMapper<Todo> {

	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("id");
		String text = rs.getString("text");
		boolean done = rs.getBoolean("done");
		Todo todo = new Todo();
		todo.setId(id);
		todo.setText(text);
		todo.setDone(done);
		return todo;
	}

}
