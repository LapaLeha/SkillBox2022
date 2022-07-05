package main;
import main.model.User;
import main.model.TodoL;

import java.util.*;

public class Storage {
    private static int currentToDoListId = 1;
    private static int currentUserId = 1;
    private static HashMap<Integer, TodoL> todoL = new HashMap();
    private static HashMap<Integer, User> userL = new HashMap();

    ///////////Методы для листа-дел TodoL///////////////////
    public static List<TodoL> getToDoL() {
        ArrayList<TodoL> todoLList = new ArrayList<>();
        todoLList.addAll(todoL.values());
        return todoLList;
    }

    public static TodoL addToDoL(String name) {
        int id = currentToDoListId++;
        TodoL todoL1 = new TodoL();
        todoL1.setName(name);
        todoL1.setId(id);
        //todoL1.setDate();
        todoL.put(id, todoL1);
        return todoL1;
    }

    public static TodoL getToDoL (int ToDoLId) {
        if (todoL.containsKey(ToDoLId)) {
            return todoL.get(ToDoLId);
        }
        return null;
    }

    public static TodoL putToDoL (int toDoLId,TodoL todoLNew) {

        if (todoL.containsKey(toDoLId)) {
            return todoL.replace(toDoLId,todoLNew);
        }
        return null;
    }

    public static TodoL patchNameToDoL (int toDoLId,String nameNew) {
        if (todoL.containsKey(toDoLId)) {
            todoL.get(toDoLId).setName(nameNew);
            return todoL.get(toDoLId);
        }
        return null;
    }

    public static TodoL patchDateToDoL (int toDoLId) {
        if (todoL.containsKey(toDoLId)) {
            //todoL.get(toDoLId).setDate();
            return todoL.get(toDoLId);
        }
        return null;
    }

    public static void deleteToDoL(int ToDoLId){
        if (todoL.containsKey(ToDoLId)) {
            todoL.remove(ToDoLId);
        }
        System.out.println("It is id not found");
    }

    public static void deleteToDoLAll () {
        todoL.clear();
    }
///////////Методы для пользователя User///////////////////
    public static List<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(userL.values());
        return userList;
    }

    public static User addUser(String name) {
        int id = currentUserId++;
        User user1 = new User();
        user1.setName(name);
        user1.setId(id);
        userL.put(id, user1);
        return user1;
    }

    public static User getUser (int UserId) {
        if (userL.containsKey(UserId)) {
            return userL.get(UserId);
        }
        return null;
    }

    public static void deleteUser(int UserId){
        if (userL.containsKey(UserId)) {
            userL.remove(UserId);
        }
        System.out.println("It is id not found");
    }

    public static void deleteUserAll () {
        userL.clear();
    }

    public static User putUser (int userId,User userNew) {

        if (userL.containsKey(userId)) {
            return userL.replace(userId,userNew);
        }
        return null;
    }

    public static User patchNameUser (int userId,String nameNew) {
        if (userL.containsKey(userId)) {
            userL.get(userId).setName(nameNew);
            return userL.get(userId);
        }
        return null;
    }

    public static User patchAgeUser (int userId,int ageNew) {
        if (userL.containsKey(userId)) {
            userL.get(userId).setAge(ageNew);
            return userL.get(userId);
        }
        return null;
    }

}
