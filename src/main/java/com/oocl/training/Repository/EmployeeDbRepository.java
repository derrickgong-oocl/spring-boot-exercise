package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDbRepository implements EmployeeRepository {

    JpaEmployeeRepository repository;

    public EmployeeDbRepository(JpaEmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee get(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void updateEmployee(Integer id, Employee employee) {
        Employee toupdate = repository.getReferenceById(id);
        toupdate.setId(employee.getId());
        toupdate.setAge(employee.getAge());
        toupdate.setSalary(employee.getSalary());
        toupdate.setName(employee.getName());
        toupdate.setGender(employee.getGender());
        toupdate.setActive(employee.isActive());
        repository.save(toupdate);
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return repository.getEmployeesByGender(gender);
    }

    @Override
    public void deleteEmployee(Integer id) {
        repository.delete(repository.getReferenceById(id));
    }


}
