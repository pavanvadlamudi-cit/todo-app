package ie.cit.adf.dao;

import ie.cit.adf.domain.Todo;

import java.util.List;

public interface TodoRepository {
	void insert(Todo todo);

	List<Todo> getAll();

	void delete(String id);

	Todo findById(String id);

	void update(Todo todo);

}
