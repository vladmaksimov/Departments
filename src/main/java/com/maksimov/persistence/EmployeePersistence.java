package com.maksimov.persistence;

import com.maksimov.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * JPA specific extension of {@link org.springframework.data.jpa.repository.JpaRepository}.
 *
 * @author Vladislav maksimov
 */
@Repository
public interface EmployeePersistence extends JpaRepository<Employee, Long> {

    /**
     * Gets the {@link Page} object with the list of  {@link Employee} objects from the database
     * by received {@link Long} id of the {@link com.maksimov.models.Department} object with pagination
     * or empty list if the rows with this id doesn't exist.
     *
     * @param id       value of the department.
     * @param pageable the {@link Pageable} object, needed to get departments list with pagination.
     * @return the {@link Page} object with list of {@link Employee} objects.
     */
    @Query("select e from Employee e where e.department.id = ?1")
    Page<Employee> getEmployees(Long id, Pageable pageable);


    /**
     * Return the {@link Page} object with {@link Employee} objects from database and {@link Pageable} object,
     * filtered by search value.
     *
     * @param id       value of the department id.
     * @param search   the {@link String} value to search employees.
     * @param pageable the {@link Pageable} object, needed to get departments list with pagination.
     * @return the {@link Page} object with list of {@link Employee} objects get .
     */
    @Query("select e from Employee e where e.department.id = ?1 and (e.name like ?2 or e.email like ?2)")
    Page<Employee> findEmployees(Long id, String search, Pageable pageable);

    /**
     * Gets the {@link Employee} object from database by it email or null if row with this email is not exist.
     *
     * @param email of the employee we want to get.
     * @return {@link Long} object with {@link Employee} id.
     */
    @Query("select e.id from Employee e where e.email = ?1")
    Long getIdByEmail(String email);

}
