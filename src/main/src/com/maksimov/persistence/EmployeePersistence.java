package com.maksimov.persistence;

import com.maksimov.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created on 26.09.16.
 */
@Repository
public interface EmployeePersistence extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.department.id = ?1")
    Page<Employee> getEmployees(Long id, Pageable pageable);

    @Query("select e from Employee e where e.department.id = ?1 and (e.name like ?2 or e.email like ?2)")
    Page<Employee> findEmployees(Long id, String search, Pageable pageable);

    @Query("select e.id from Employee e where e.email = ?1")
    Long getIdByEmail(String email);

}
