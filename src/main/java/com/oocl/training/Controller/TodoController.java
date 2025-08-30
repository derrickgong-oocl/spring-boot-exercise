package com.oocl.training.Controller;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Todo;
import com.oocl.training.Service.TodoService;
import com.oocl.training.dto.EmployeeRequest;
import com.oocl.training.dto.TodoRequest;
import com.oocl.training.mapper.TodoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// post http://localhost:8080/api/v1/todos
@RestController
@RequestMapping("/api/v1/todos")

public class TodoController {

    private final TodoMapper todoMapper;
    private final TodoService todoService;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
        this.todoService = todoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTodo(@Valid @RequestBody TodoRequest todoRequest) {
        todoService.addTodo(todoMapper.toResponse(todoRequest));
    }

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getAll();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Integer id) {
        return todoService.getTodoById(id);
    }

    @PutMapping
    public ResponseEntity<Void> updataTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        boolean response = todoService.updateTodo(id, todo);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        boolean response = todoService.deleteTodo(id);
        if (response) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
