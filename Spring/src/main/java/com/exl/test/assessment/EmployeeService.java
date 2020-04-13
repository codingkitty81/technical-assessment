package com.exl.test.assessment;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public void save(Employee employee) {
        repo.save(employee);
    }

    public List<Employee> listAll() {
        return repo.findAll();
    }

    public List<Employee> findByNameContaining(String name) {
        return repo.findByName(name);
    }

    public List<Employee> findByDate(Date startDate, Date endDate) {
        return repo.findByDate(startDate, endDate);
    }

    public List<Employee> findByAllFilters(String name, Date startDate, Date endDate) {
        return repo.findByAllFilters(name, startDate, endDate);
    }
}