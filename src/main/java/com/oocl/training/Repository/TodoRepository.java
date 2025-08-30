package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Todo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {

    Todo addTodo(Todo todo);

    List<Todo> getAll();

    Todo get(Integer id);

    void deleteTodo(Integer id);

    void updateTodo(Integer id, Todo todo);


}
