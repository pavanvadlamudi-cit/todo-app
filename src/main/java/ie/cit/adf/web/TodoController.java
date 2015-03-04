package ie.cit.adf.web;

import ie.cit.adf.dao.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController {

	@Autowired
	private TodoRepository repo;

	@RequestMapping("all")
	public String getAllTodoItems(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todo";
	}
}
