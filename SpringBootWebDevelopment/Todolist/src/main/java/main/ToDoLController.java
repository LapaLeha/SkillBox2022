package main;

import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.TodoL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoLController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todolist/")
    public List<TodoL> list() {
        Iterable<TodoL> todoLInterable = todoRepository.findAll();
        ArrayList<TodoL> todoLS = new ArrayList<>();
        for (TodoL todo : todoLInterable) {
            todoLS.add(todo);
        }
        return todoLS;
    }
    @PutMapping("/todolist/")
    public int add(TodoL todoL) {
        TodoL newTodoL = todoRepository.save(todoL);
        return newTodoL.getId();
    }

    @GetMapping("/todolist/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<TodoL> optionalTodoL = todoRepository.findById(id);
        if (optionalTodoL.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTodoL.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todolist/")
    public void deleteToDoL(@PathVariable int id) {
        todoRepository.deleteById(id);
    }

    @DeleteMapping("/todolist/")
    public void deleteToDoLAll() {
        todoRepository.deleteAll();
    }

}
