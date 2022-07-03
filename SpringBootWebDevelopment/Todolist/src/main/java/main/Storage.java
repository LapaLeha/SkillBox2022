package main;
import response.TodoL;
import java.util.*;

public class Storage {
    private static int currenrId = 1;
    private static HashMap<Integer, TodoL> todoL = new HashMap();

    public static List<TodoL> getToDoL() {
        ArrayList<TodoL> todoLList = new ArrayList<>();
        todoLList.addAll(todoL.values());
        return todoLList;
    }

    public static TodoL addToDoL(String name) {
        int id = currenrId++;
        TodoL todoL1 = new TodoL();
        todoL1.setName(name);
        todoL1.setId(id);
        todoL1.setDate();
        todoL.put(id, todoL1);
        return todoL1;
    }

    public static TodoL getToDoL (int ToDoLId) {
        if (todoL.containsKey(ToDoLId)) {
            return todoL.get(ToDoLId);
        }
        return null;
    }

    public static TodoL putToDoL (int ToDoLId,TodoL todoLNew) {

        if (todoL.containsKey(ToDoLId)) {
            return todoL.replace(ToDoLId,todoLNew);
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
}
