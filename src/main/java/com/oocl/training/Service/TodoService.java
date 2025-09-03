package com.oocl.training.Service;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Entitiy.Todo;
import com.oocl.training.Repository.TodoDbRepository;
import com.oocl.training.Repository.TodoRepository;
import com.oocl.training.exception.InvalidUpdateException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoDbRepository todoDbRepository) {
        this.todoRepository = todoDbRepository;
    }

    public Todo getTodoById(Integer id) {
        return todoRepository.get(id);
    }

    public List<Todo> getAll() {
        return todoRepository.getAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.addTodo(todo);
    }

    public Page<Todo> getTodoByPage(Integer page, Integer size) {
        List<Todo> allTodos = getAll();
        allTodos =  allTodos.stream().filter(todo -> todo.getStatus().equals("active")).collect(Collectors.toList());
        int totalCount = allTodos.size();
        int startIndex = (page - 1) * size;
        if (startIndex >= totalCount) {
            return new Page<>(page, size, totalCount, Collections.emptyList());
        }

        int endIndex = Math.min(startIndex + size, totalCount);
        List<Todo> pageTodos = new ArrayList<>(allTodos.subList(startIndex, endIndex));
        return new Page<>(page, size, totalCount, pageTodos);
    }

    public Boolean deleteTodo(Integer id) {
        List<Todo> allTodos = todoRepository.getAll();
        for (Todo todo : allTodos) {
            if (todo.getId().equals(id)) {
                todo.setStatus("inactive");
                todoRepository.updateTodo(todo.getId(), todo);
                return true;
            }
        }
        return false;
    }

    public Boolean updateTodo(Integer id, Todo todo) {
        if (todo.getStatus().equals("inactive")) {
            throw new InvalidUpdateException("Inactive todo can not be updated");
        }
        Todo toUpdate = todoRepository.get(id);
        if (toUpdate != null) {
            toUpdate.setId(todo.getId());
            toUpdate.setTitle(todo.getTitle());
            toUpdate.setStatus(todo.getStatus());
            todoRepository.updateTodo(id, toUpdate);
            return true;
        }
        return false;
    }


}
