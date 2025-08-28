package com.oocl.training.Service;


import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Repository.EmployeeDbRepository;
import com.oocl.training.Repository.EmployeeMemoryRepository;
import com.oocl.training.Repository.EmployeeRepository;
import com.oocl.training.exception.InvalidEmployeeException;
import com.oocl.training.exception.InvalidUpdateException;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeDbRepository employeeDbRepository) {
        this.employeeRepository = employeeDbRepository;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.get(id);
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository.getAll();
    }

    public List<Employee> getEmployeeByGender(String gender) {
        List<Employee> employees = employeeRepository.getAll();
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
        List<Employee> allEmployees = getEmployeeList();
        int totalCount = allEmployees.size();
        int startIndex = (page - 1) * size;
        if (startIndex >= totalCount) {
            return new Page<>(page, size, totalCount, Collections.emptyList());
        }

        int endIndex = Math.min(startIndex + size, totalCount);
        List<Employee> pageEmployees = new ArrayList<>(allEmployees.subList(startIndex, endIndex));
        return new Page<>(page, size, totalCount, pageEmployees);
    }

    public Employee addEmployee(Employee employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new InvalidEmployeeException("Employee age must be between 18 and 65.");
        }

        if (employee.getAge() >= 30 && employee.getSalary() < 20000) {
            throw new InvalidEmployeeException("Employees over 30 must have salary >= 20000.");
        }
        employee.setActive(true);

        return employeeRepository.addEmployee(employee);
    }

    public boolean deleteEmployee(Integer id) {
        List<Employee> allEmployees = employeeRepository.getAll();
        for (Employee employee : allEmployees) {
            if (employee.getId() == id) {
                employee.setActive(false);
                return true;
            }
        }
        return false;
    }

    public boolean updateEmployee(Integer id, Employee employee) {
        if (!employee.isActive()) {
            throw new InvalidUpdateException("Inactive Employee can not be updated");
        }

        Employee toUpdate = employeeRepository.get(id);
        if (toUpdate != null) {
            toUpdate.setGender(employee.getGender());
            toUpdate.setAge(employee.getAge());
            toUpdate.setName(employee.getName());
            toUpdate.setSalary(employee.getSalary());
            employeeRepository.updateEmployee(id, toUpdate);
            return true;
        }
        return false;
    }

}
