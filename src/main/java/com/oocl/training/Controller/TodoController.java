package com.oocl.training.Controller;

import com.oocl.training.Entitiy.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// post http://localhost:8080/api/v1/todos
@RestController
@RequestMapping("/api/v1/todos")

public class TodoController {
    private final Map<Integer, Todo> db = new HashMap<>();


    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTodo(@RequestBody Todo todo) {
        db.put(db.size() + 1, todo);
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return new ArrayList<>(db.values());
    }
}
