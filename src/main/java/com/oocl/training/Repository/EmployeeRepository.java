package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {

    private Integer employee_total = 3;
    public EmployeeRepository () {

    }
    private final Map<Integer, Employee> employeeDB = new HashMap<>(Map.of(1, new Employee(1, "John Smith", 32, "Male", 5000.0, true),
            2, new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
            3, new Employee(3, "David Williams", 35, "Male", 5500.0, true)
    ));


    public Employee get(Integer id) {
        return employeeDB.get(id);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employeeDB.values());
    }



    public Employee addEmployee(Employee employee) {
        employee_total += 1;
        employee.setId(employee_total);
        employeeDB.put(employee_total, employee);
        return employee;
    }



    public boolean updateEmployee(Integer id, Employee employee) {
        Employee toUpdate = employeeDB.get(id);
        if (toUpdate != null) {
            toUpdate.setGender(employee.getGender());
            toUpdate.setAge(employee.getAge());
            toUpdate.setName(employee.getName());
            toUpdate.setSalary(employee.getSalary());
            employeeDB.put(id, toUpdate);
            return true;
        }
        return false;
    }


}
