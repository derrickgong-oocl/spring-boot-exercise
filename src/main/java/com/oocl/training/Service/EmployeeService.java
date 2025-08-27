package com.oocl.training.Service;


import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employeeDB = new HashMap<>();

    public Employee getEmployeeById(Integer id) {
        return employeeDB.get(id);
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeDB.values());
    }

    public List<Employee> getEmployeeByGender(String gender) {
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

    public Page<Employee> getEmployeesByPage(Integer page, Integer size) {
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

    public void addEmployee(Employee employee) {
        employee.setId(employeeDB.size() + 1);
        employeeDB.put(employeeDB.size() + 1, employee);
    }

    public ResponseEntity deleteEmployee(Integer id) {
        if (employeeDB.containsKey(id)) {
            employeeDB.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateEmployee(Integer id, Employee employee) {
        Employee toUpdate = employeeDB.get(id);
        if(toUpdate != null){
            toUpdate.setGender(employee.getGender());
            toUpdate.setAge(employee.getAge());
            toUpdate.setName(employee.getName());
            toUpdate.setSalary(employee.getSalary());
            employeeDB.put(id, toUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
