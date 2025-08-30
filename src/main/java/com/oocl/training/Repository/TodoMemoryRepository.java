package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoMemoryRepository implements TodoRepository{

    public TodoMemoryRepository() {

    }
    private Integer todoTotal = 3;

    private final Map<Integer, Todo> TodoDB = new HashMap<>(Map.of(1, new Todo(1, "A", "active"),
            2, new Todo(2, "B", "active"),
            3, new Todo(3, "C", "active")
    ));

    @Override
    public Todo addTodo(Todo todo) {
        todoTotal += 1;
        todo.setId(todoTotal);
        TodoDB.put(todoTotal, todo);
        return todo;
    }

    @Override
    public List<Todo> getAll() {
        return new ArrayList<>(TodoDB.values());
    }

    @Override
    public Todo get(Integer id) {
        return TodoDB.get(id);
    }

    @Override
    public void deleteTodo(Integer id) {
        Todo todo = TodoDB.get(id);
        todo.setStatus("inactive");
    }

    @Override
    public void updateTodo(Integer id, Todo todo) {
        TodoDB.put(id, todo);
    }
}
