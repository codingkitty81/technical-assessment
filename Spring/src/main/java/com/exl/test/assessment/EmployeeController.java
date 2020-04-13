package com.exl.test.assessment;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employee-list")
    public List<Employee> viewEmployees() {
        List<Employee> listEmployees = service.listAll();
        return listEmployees;
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@RequestBody Employee employee) {
        service.save(employee);
        return "save-employee";
    }

    @PostMapping("/employee-list")
    public List<Employee> filterEmployees(@RequestBody SearchInfo searchInfo) {
        List<Employee> listEmployees;
        if(searchInfo.getName() == null) {
            if(searchInfo.getStartDate() == null) {
                if(searchInfo.getEndDate() == null) {
                    listEmployees = service.listAll();
                } else {
                    searchInfo.setStartDate(searchInfo.getEndDate());
                    listEmployees = service.findByDate(searchInfo.getStartDate(), searchInfo.getEndDate());
                }
            } else {
                if(searchInfo.getEndDate() == null) {
                    Date today = new Date(System.currentTimeMillis());
                    searchInfo.setEndDate(today);
                }
                listEmployees = service.findByDate(searchInfo.getStartDate(), searchInfo.getEndDate());
            }
        } else {
            if(searchInfo.getStartDate() == null) {
                if(searchInfo.getEndDate() == null) {
                    listEmployees = service.findByNameContaining(searchInfo.getName().toUpperCase());
                } else {
                    searchInfo.setStartDate(searchInfo.getEndDate());
                    listEmployees = service.findByDate(searchInfo.getStartDate(), searchInfo.getEndDate());
                }
            } else {
                if(searchInfo.getEndDate() == null) {
                    Date today = new Date(System.currentTimeMillis());
                    searchInfo.setEndDate(today);
                }
                listEmployees = service.findByAllFilters(searchInfo.getName().toUpperCase(), searchInfo.getStartDate(), searchInfo.getEndDate());
            }
        }
        return listEmployees;
    }
}