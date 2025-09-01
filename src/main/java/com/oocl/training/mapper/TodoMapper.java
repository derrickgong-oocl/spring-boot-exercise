package com.oocl.training.mapper;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Entitiy.Todo;
import com.oocl.training.dto.EmployeeRequest;
import com.oocl.training.dto.EmployeeResponse;
import com.oocl.training.dto.TodoRequest;
import com.oocl.training.dto.TodoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TodoMapper {
    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public Todo toRequest(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }

    public List<TodoResponse> toResponse(Page<Todo> todos) {
        return todos.getContent().stream().map(this::toResponse).toList();
    }

    public List<TodoResponse> toResponse(List<Todo> todos) {
        return todos.stream().map(this::toResponse).toList();
    }


}
