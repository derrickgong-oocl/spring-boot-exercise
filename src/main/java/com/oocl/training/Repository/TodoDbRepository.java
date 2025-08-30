package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDbRepository implements TodoRepository {

    @Autowired
    JpaTodoRepository repository;

    public TodoDbRepository(JpaTodoRepository jpaTodoRepository) {
        this.repository = jpaTodoRepository;
    }

    @Override
    public Todo addTodo(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @Override
    public Todo get(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteTodo(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void updateTodo(Integer id, Todo todo) {
        Todo toupdate = get(id);
        toupdate.setId(todo.getId());
        toupdate.setStatus(todo.getStatus());
        toupdate.setTitle(todo.getTitle());
        repository.save(toupdate);
    }
}
