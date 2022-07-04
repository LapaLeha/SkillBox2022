package main;

import main.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public List<User> list() {
        return Storage.getUserList();
    }

    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public User add (String name) {
        return Storage.addUser(name);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity get(int id) {
        User user = Storage.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public void deleteUser(int id) {
        Storage.deleteUser(id);
    }

    @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public void deleteUserAll() {
        Storage.deleteUserAll();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity put (int id,User userNew) {
        User user = Storage.putUser(id,userNew);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity patchName (int id,String nameNew ) {
        User user = Storage.patchNameUser(id,nameNew);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity patchAge (int id, int ageNew) {
        User user = Storage.patchAgeUser(id,ageNew);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }


}
