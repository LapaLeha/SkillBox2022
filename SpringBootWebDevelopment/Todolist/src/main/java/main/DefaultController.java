package main;

import main.model.TodoL;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<TodoL> todoLInterable = todoRepository.findAll();
        ArrayList<TodoL> todoLS = new ArrayList<>();
        for (TodoL todo : todoLInterable) {
            todoLS.add(todo);
        }
        model.addAttribute("todolist", todoLS);
        model.addAttribute("todolistCount", todoLS.size());
        return "index";
    }

}
