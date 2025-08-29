package com.oocl.training.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Repository.EmployeeDbRepository;
import com.oocl.training.exception.InvalidEmployeeException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeDbRepository employeeRepository;

    @BeforeEach
    public void setup() {

//        employeeRepository.addEmployee(new Employee("Emily Brown", 23, "Female", 4500.0));
//        employeeRepository.addEmployee(new Employee("Michael", 40, "Male", 7000.0));
    }


//    @Test
//    public void should_throw_exception_when_add_under_eighteen() throws Exception {
//        List<Employee> givenEmployees = employeeRepository.getAll();
//        Employee newEmployee = new Employee("Michael", 10, "Male", 7000.0);
//        givenEmployees.add(newEmployee);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        assertThrows(InvalidEmployeeException.class, () -> client.perform(MockMvcRequestBuilders
//                .post("/api/v1/employees")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(newEmployee))));
//    }

    @Test
    public void should_return_employees_when_get_all_employees_exist() throws Exception {
        List<Employee> givenEmployees = employeeRepository.getAll();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenEmployees.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id").value(givenEmployees.get(3).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].id").value(givenEmployees.get(4).getId()));
    }


    @Test
    public void should_return_employee_when_get_id_employee_exist() throws Exception {
        List<Employee> givenEmployees = employeeRepository.getAll();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"));


        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenEmployees.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenEmployees.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(givenEmployees.get(0).getGender()));


    }

    @Test
    public void should_return_employees_when_get_gender_employees_exist() throws Exception {
        List<Employee> givenEmployees = employeeRepository.getAll();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees/gender").param("gender","Male"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].gender").value(givenEmployees.get(2).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].gender").value(givenEmployees.get(4).getGender()));

    }


    @Test
    public void post_employees_should_create_successfully() throws Exception {
        List<Employee> givenEmployees = employeeRepository.getAll();
        Employee newEmployee = new Employee("Michael", 40, "Male", 70000.0);
        givenEmployees.add(newEmployee);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions perform = client.perform(MockMvcRequestBuilders
                        .post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)));
        perform.andExpect(MockMvcResultMatchers.status().isCreated());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/api/v1/employees/6"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenEmployees.get(5).getName()));
    }


    @Test
    public void delete_employees_by_id_successful() throws Exception {
        ResultActions perform = client.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1"));

        perform.andExpect(MockMvcResultMatchers.status().isNoContent());
        Employee employee = employeeRepository.get(1);
        assertEquals(employee.isActive(), Boolean.FALSE);
    }

    @Test
    public void put_employees_by_id_successful() throws Exception {
        Employee newEmployee = new Employee(1,"ABC", 40, "Male", 70000.0, true);

        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions perform = client.perform(MockMvcRequestBuilders
                .put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployee)));

        perform.andExpect(MockMvcResultMatchers.status().isOk());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ABC"));
    }








}
