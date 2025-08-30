package com.oocl.training.mapper;

import com.oocl.training.Entitiy.Todo;
import com.oocl.training.dto.TodoRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class TodoMapper {
    public Todo toResponse(TodoRequest todo) {
        Todo todoRequest = new Todo();
        BeanUtils.copyProperties(todo, todoRequest);
        return todoRequest;
    }


}
