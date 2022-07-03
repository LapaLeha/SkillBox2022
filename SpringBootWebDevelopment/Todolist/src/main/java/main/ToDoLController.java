package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.TodoL;

import java.util.List;

@RestController
public class ToDoLController {

    @RequestMapping(value = "/todolist/", method = RequestMethod.GET)
    public List<TodoL> list() {
        return Storage.getToDoL();
    }

    @RequestMapping(value = "/todolist/", method = RequestMethod.POST)
    public TodoL add(String name) {
        return Storage.addToDoL(name);
    }

    @GetMapping("/todolist/{id}")
    public ResponseEntity get(int id) {
        TodoL toDol = Storage.getToDoL(id);
        if (toDol == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDol, HttpStatus.OK);
    }

    @RequestMapping(value = "/todolist/", method = RequestMethod.DELETE)
    public void deleteToDoL(int id) {
        Storage.deleteToDoL(id);
    }

    @RequestMapping(value = "/todolist/", method = RequestMethod.DELETE)
    public void deleteToDoLAll() {
        Storage.deleteToDoLAll();
    }

    @PutMapping("/todolist/{id}")
    public ResponseEntity put (int id,TodoL toDolNew) {
        TodoL toDol = Storage.putToDoL(id,toDolNew);
        if (toDol == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDol, HttpStatus.OK);
    }
}
