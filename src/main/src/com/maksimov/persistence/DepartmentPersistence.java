package com.maksimov.persistence;

import com.maksimov.models.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created on 26.09.16.
 */
@Repository
public interface DepartmentPersistence extends JpaRepository<Department, Long> {

    @Query("select d from Department d where d.name like ?1")
    Page<Department> searchDepartments(String name, Pageable pageable);

    @Query("select count (d) from Department d where d.name like ?1")
    Long getCountBySearchValue(String search);

    @Query("select d.id from Department d where d.name = ?1")
    Long getIdByName(String name);

}
