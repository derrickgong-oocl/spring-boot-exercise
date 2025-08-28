package com.oocl.training.Service;

import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Repository.EmployeeDbRepository;
import com.oocl.training.Repository.EmployeeMemoryRepository;
import com.oocl.training.exception.InvalidEmployeeException;
import com.oocl.training.exception.InvalidUpdateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDbRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_add_employee_successful() {
        Employee employee = new Employee(1, "oocl", 22, "Male", 5000, true);
        Employee mockEmployee = new Employee(1, employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary(), true);
        Mockito.when(employeeRepository.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        Employee saved_employee = employeeService.addEmployee(employee);

        assertEquals(employee.getAge(), saved_employee.getAge());
        assertEquals(employee.getSalary(), saved_employee.getSalary());
        assertEquals(employee.isActive(), saved_employee.isActive());
        assertNotNull(saved_employee.getId());

    }


    @Test
    void should_throw_exception_when_create_employee_given_age_below_18() {
        Employee employee = new Employee(1, "oocl", 10, "Male", 5000, true);

        InvalidEmployeeException invalidEmployeeException = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("Employee age must be between 18 and 65.", invalidEmployeeException.getMessage());
    }

    @Test
    void should_throw_exception_when_create_employee_over_30_salary_20000() {
        Employee employee = new Employee(1, "oocl", 40, "Male", 5000, true);

        InvalidEmployeeException invalidEmployeeException = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("Employees over 30 must have salary >= 20000.", invalidEmployeeException.getMessage());
    }

    @Test
    void should_throw_exception_when_update_inactivate_employee() {
        Employee employee = new Employee(1, "oocl", 40, "Male", 5000, false);
        Employee updateEmployee = new Employee(2, "oocl", 41, "Male", 50000, false);

        InvalidUpdateException invalidUpdateException = assertThrows(InvalidUpdateException.class, () -> employeeService.updateEmployee(1, updateEmployee));
        assertEquals("Inactive Employee can not be updated", invalidUpdateException.getMessage());
    }

    @Test
    void is_ok_when_update_successful() {
        Employee employee = new Employee(1, "oocl", 20, "Male", 5000, true);
        Employee mockEmployee = new Employee(1, "John Smith", 32, "Male", 5000.0, true);

        Mockito.when(employeeRepository.get(1)).thenReturn(mockEmployee);


        employeeService.updateEmployee(1, employee);

        assertEquals(mockEmployee.getAge(), employee.getAge());
        assertEquals(mockEmployee.getSalary(), employee.getSalary());
        assertEquals(mockEmployee.isActive(), employee.isActive());
        assertNotNull(employee.getId());

    }

    @Test
    void shoud_delete_employee_by_id_successfully() {
        Employee employee = new Employee(1, "John Smith", 32, "Male", 5000.0, true);
        Mockito.when(employeeRepository.getAll()).thenReturn(List.of(employee,
                new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
                new Employee(3, "David Williams", 35, "Male", 5500.0, true)));

        employeeService.deleteEmployee(1);

        assertFalse(employee.isActive());
        verify(employeeRepository, times(1)).getAll();

    }
    @Test
    void get_employ_by_id_successfully() {
        Employee employee = new Employee(1, "John Smith", 32, "Male", 5000.0, true);
        Employee mockEmployee = new Employee(1, "John Smith", 32, "Male", 5000.0, true);
        Mockito.when(employeeRepository.get(1)).thenReturn(mockEmployee);

        Employee employeeTest = employeeService.getEmployeeById(1);

        assertEquals(employeeTest.getName(), employee.getName());
        assertEquals(employeeTest.getAge(), employee.getAge());
        assertEquals(employeeTest.getSalary(), employee.getSalary());
        assertEquals(employeeTest.isActive(), employee.isActive());
        assertEquals(employeeTest.getGender(), employee.getGender());

   }


    @Test
    void get_employ_by_gender_successfully() {
        Employee employee = new Employee(1, "John Smith", 32, "Male", 5000.0, true);


        Mockito.when(employeeRepository.getAll()).thenReturn(List.of(employee,
                new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true),
                new Employee(3, "David Williams", 35, "Male", 5500.0, true)));

        List<Employee> genderList = employeeService.getEmployeeByGender("Male");

        assertEquals(genderList.size(), 2);
        assertEquals(genderList.get(0).getGender(), "Male");
        assertEquals(genderList.get(1).getGender(), "Male");


    }




}