package com.oocl.training;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


// post http://localhost:8080/api/v1/employees

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final Map<Integer, Employee> employeeDB = new HashMap<>();

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeDB.get(id);
    }

    @GetMapping
    public List<Employee> getEmployeeList(@PathVariable("id") int id) {
        return new ArrayList<>(employeeDB.values());
    }

    @GetMapping
    public List<Employee> getEmployeeByGender(@PathVariable String gender) {
        List<Employee> employees = new ArrayList<>(employeeDB.values());
        List<Employee> result = new ArrayList<>();
        if (gender != null) {
            for (Employee employee : employees) {
                if (gender.equals(employee.getGender())) {
                    result.add(employee);
                }
            }
        }
        return result;
    }

    @GetMapping("/page")
    public Page<Employee> getEmployeesByPage(@RequestParam int page, @RequestParam int size) {
        List<Employee> allEmployees = new ArrayList<>(employeeDB.values());
        int totalCount = allEmployees.size();
        int startIndex = (page - 1) * size;
        if (startIndex >= totalCount) {
            return new Page<>(page, size, totalCount, Collections.emptyList());
        }

        int endIndex = Math.min(startIndex + size, totalCount);
        List<Employee> pageEmployees = new ArrayList<>(allEmployees.subList(startIndex, endIndex));
        return new Page<>(page, size, totalCount, pageEmployees);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employee.setId(employeeDB.size() + 1);
        employeeDB.put(employeeDB.size() + 1, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeDB.remove(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee toUpdate = employeeDB.get(id);
        if(toUpdate != null){
            toUpdate.setGender(employee.getGender());
            toUpdate.setAge(employee.getAge());
            toUpdate.setName(employee.getName());
            toUpdate.setSalary(employee.getSalary());
            employeeDB.put(id, toUpdate);
        }
        return toUpdate;
    }


}
