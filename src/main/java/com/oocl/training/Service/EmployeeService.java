package com.oocl.training.Service;


import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Repository.EmployeeRepository;
import com.oocl.training.exception.InvalidEmployeeException;
import com.oocl.training.exception.InvalidUpdateException;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.get(id);
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository.getAll();
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.getEmployeeByGender(gender);
    }


    public Page<Employee> getEmployeesByPage(Integer page, Integer size) {
        return employeeRepository.getEmployeesByPage(page, size);
    }

    public void addEmployee(Employee employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new InvalidEmployeeException("Employee age must be between 18 and 65.");
        }

        if (employee.getAge() >= 30 && employee.getSalary() < 20000) {
            throw new InvalidEmployeeException("Employees over 30 must have salary >= 20000.");
        }
        employee.setActive(true);
        employeeRepository.addEmployee(employee);
    }

    public boolean deleteEmployee(Integer id) {
        return employeeRepository.deleteEmployee(id);
    }

    public boolean updateEmployee(Integer id, Employee employee) {
        if (!employee.isActive()) {
            throw new InvalidUpdateException("Inactive Employee can not be updated");
        }
        return employeeRepository.updateEmployee(id, employee);
    }

}
