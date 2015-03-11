package ie.cit.adf.web;

import ie.cit.adf.dao.TodoRepository;
import ie.cit.adf.domain.Todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TodoController {

	@Autowired
	private TodoRepository repo;

	@RequestMapping("all")
	public String getAllTodoItems(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todo";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String create(@RequestParam String text) {
		Todo todo = new Todo();
		todo.setText(text);
		repo.insert(todo);
		return "redirect:all";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		repo.delete(id);
		return "redirect:all";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@PathVariable String id) {
		Todo todo = repo.findById(id);
		todo.setDone(!todo.isDone());
		repo.update(todo);
		return "redirect:all";
	}

	@RequestMapping(value = "all", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Todo> getAllTodoItems() {
		return repo.getAll();
	}
}
