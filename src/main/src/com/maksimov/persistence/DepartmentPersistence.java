package com.maksimov.persistence;

import com.maksimov.models.Department;
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
public interface DepartmentPersistence extends JpaRepository<Department, Long> {

    /**
     * Return the {@link Page} object with {@link Department} objects from database and {@link Pageable} object,
     * filtered by search value.
     *
     * @param name     the {@link String} value to search departments.
     * @param pageable the {@link Pageable} object, needed to get departments list with pagination.
     * @return the {@link Page} object with list of {@link Department} objects.
     */
    @Query("select d from Department d where d.name like ?1")
    Page<Department> searchDepartments(String name, Pageable pageable);

    /**
     * Receives the {@link String} object with name of the {@link Department} object.
     * Return the department object with received name or null if this name not contains in database.
     *
     * @param name of the department.
     * @return {@link Long} object with {@link Department} id.
     */
    @Query("select d.id from Department d where d.name = ?1")
    Long getIdByName(String name);

}
