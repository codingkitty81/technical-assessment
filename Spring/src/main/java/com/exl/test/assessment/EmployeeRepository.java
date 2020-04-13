package com.exl.test.assessment;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employee e where upper(e.first_name) like %:name% or upper(e.last_name) like %:name%", nativeQuery = true)
    public List<Employee> findByName(@Param("name") String name);

    @Query(value = "select * from employee e where (e.start_date between :startDate and :endDate or e.end_date between :startDate and :endDate) " +
                   "or (e.start_date < :startDate and (e.end_date > :endDate or e.end_date = null))", nativeQuery = true)
    public List<Employee> findByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select * from employee e where (upper(e.first_name) like %:name% or upper(e.last_name) like %:name%) " +
           "and (e.start_date between :startDate and :endDate or e.end_date between :startDate and :endDate) " +
           "or (e.start_date < :startDate and (e.end_date > :endDate or e.end_date = null))", nativeQuery = true)
    public List<Employee> findByAllFilters(@Param("name") String name, 
                                           @Param("startDate") Date startDate, 
                                           @Param("endDate") Date endDate);
}
