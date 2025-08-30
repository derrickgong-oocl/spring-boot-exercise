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
    public Todo toResponse(TodoRequest todo) {
        Todo todoRequest = new Todo();
        BeanUtils.copyProperties(todo, todoRequest);
        return todoRequest;
    }


}
