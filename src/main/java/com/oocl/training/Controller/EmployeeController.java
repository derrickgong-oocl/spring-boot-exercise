package com.oocl.training.Controller;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


// post http://localhost:8080/api/v1/employees

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    EmployeeService employeeService = new EmployeeService();

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/gender")
    public List<Employee> getEmployeeByGender(@PathVariable String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @GetMapping("/page")
    public Page<Employee> getEmployeesByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return employeeService.getEmployeesByPage(page, size);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return  employeeService.updateEmployee(id, employee);
    }


}
