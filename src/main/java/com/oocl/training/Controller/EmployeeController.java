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


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/gender")
    public List<Employee> getEmployeeByGender(@RequestParam String gender) {
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
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        boolean response = employeeService.deleteEmployee(id);
        if (response) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        boolean response = employeeService.updateEmployee(id, employee);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
