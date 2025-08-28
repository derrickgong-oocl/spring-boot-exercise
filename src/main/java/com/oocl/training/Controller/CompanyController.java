package com.oocl.training.Controller;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// post http://localhost:8080/api/v1/companies

@RestController
@RequestMapping("/api/v1/companies")

public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping("/page")
    public Page<Company> getCompaniesByPages(@RequestParam Integer page, @RequestParam Integer size) {
        return companyService.getCompaniesByPages(page, size);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id){
        return companyService.getCompanyById(id);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId){
        return companyService.getCompanyEmployees(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCompanyName(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        boolean response = companyService.updateCompanyName(id, request);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        boolean response = companyService.deleteCompany(id);
        if (response) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
