package com.oocl.training.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Todo;
import com.oocl.training.Repository.EmployeeDbRepository;
import com.oocl.training.Repository.TodoDbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private TodoDbRepository todoDbRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void should_return_todo_when_get_all_todos_exist() throws Exception {
        List<Todo> givenTodos = todoDbRepository.getAll();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/todos"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenTodos.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(givenTodos.get(0).getStatus()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(givenTodos.get(0).getTitle()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenTodos.get(1).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenTodos.get(2).getId()));
    }

    @Test
    public void should_return_todo_when_get_id_todo_exist() throws Exception {
        List<Todo> givenTodos = todoDbRepository.getAll();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/todos/1"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenTodos.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenTodos.get(0).getStatus()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(givenTodos.get(0).getTitle()));
    }

    @Test
    public void post_todos_should_create_successfully() throws Exception {
        List<Todo> givenTodos = todoDbRepository.getAll();

        Todo newTodo = new Todo("milk", "active");
        givenTodos.add(newTodo);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions perform = client.perform(MockMvcRequestBuilders
                .post("/api/v1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTodo)));
        perform.andExpect(MockMvcResultMatchers.status().isCreated());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/api/v1/todos/4"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(givenTodos.get(3).getTitle()));
    }

    @Test
    public void delete_todos_by_id_successful() throws Exception {
        ResultActions perform = client.perform(MockMvcRequestBuilders.delete("/api/v1/todos/1"));

        perform.andExpect(MockMvcResultMatchers.status().isNoContent());
        Todo todo = todoDbRepository.get(1);
        assertEquals(todo.getStatus(), "inactive");
    }

    @Test
    public void put_todos_by_id_successful() throws Exception {
        Todo newTodo = new Todo(1,"milk", "active");

        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions perform = client.perform(MockMvcRequestBuilders
                .put("/api/v1/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTodo)));

        perform.andExpect(MockMvcResultMatchers.status().isOk());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/api/v1/todos/1"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("milk"));
    }

}
