package com.oocl.training.Repository;


import com.oocl.training.Entitiy.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeRepository {
    Employee get(Integer id);

    List<Employee> getAll();

    Employee addEmployee(Employee employee);

    void updateEmployee(Integer id, Employee employee);

    void deleteEmployee(Integer id);
}
