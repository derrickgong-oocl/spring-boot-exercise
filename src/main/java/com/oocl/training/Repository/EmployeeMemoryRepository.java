package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeMemoryRepository implements EmployeeRepository{

    private Integer employee_total = 3;
    public EmployeeMemoryRepository() {

    }
    private final Map<Integer, Employee> employeeDB = new HashMap<>(Map.of(1, new Employee(1, "John Smith", 32, "Male", 5000.0, true, 1),
            2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true, 1),
            3, new Employee(3, "David Williams", 35, "Male", 5500.0, true, 1)
    ));

    @Override
    public Employee get(Integer id) {
        return employeeDB.get(id);
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employeeDB.values());
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee_total += 1;
        employee.setId(employee_total);
        employeeDB.put(employee_total, employee);
        return employee;
    }

    @Override
    public void updateEmployee(Integer id, Employee employee) {
        employeeDB.put(id, employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = employeeDB.get(id);
        employee.setActive(false);
    }


}
