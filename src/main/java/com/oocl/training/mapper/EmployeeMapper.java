package com.oocl.training.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.dto.EmployeeRequest;
import com.oocl.training.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

    public List<EmployeeResponse> toResponse(List<Employee> employees) {
        return employees.stream().map(this::toResponse).toList();
    }

    public List<EmployeeResponse> toResponse(Page<Employee> employees) {
        return employees.getContent().stream().map(this::toResponse).toList();
    }

    public Employee toRequest(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }



}
