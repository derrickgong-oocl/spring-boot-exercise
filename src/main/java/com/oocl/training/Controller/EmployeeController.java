package com.oocl.training.Controller;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Service.EmployeeService;
import com.oocl.training.dto.EmployeeRequest;
import com.oocl.training.dto.EmployeeResponse;
import com.oocl.training.mapper.EmployeeMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


// post http://localhost:8080/api/v1/employees

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public List<EmployeeResponse> getEmployeeList() {
        return employeeMapper.toResponse(employeeService.getEmployeeList());
    }

    @GetMapping("/gender")
    public List<EmployeeResponse> getEmployeeByGender(@RequestParam String gender) {
        return employeeMapper.toResponse(employeeService.getEmployeeByGender(gender));
    }

    @GetMapping("/page")
    public List<EmployeeResponse> getEmployeesByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return employeeMapper.toResponse(employeeService.getEmployeesByPage(page, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toRequest(employeeRequest);
        return employeeMapper.toResponse(employeeService.addEmployee(employee));
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
    public ResponseEntity<Void> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toRequest(employeeRequest);
        boolean response = employeeService.updateEmployee(id, employee);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
