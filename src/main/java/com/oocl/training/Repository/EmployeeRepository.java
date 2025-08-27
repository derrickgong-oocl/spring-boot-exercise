package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {

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

    public boolean deleteEmployee(Integer id) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            employee.setActive(false);
            employeeDB.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateEmployee(Integer id, Employee employee) {
        Employee toUpdate = employeeDB.get(id);
        if (toUpdate != null){
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
