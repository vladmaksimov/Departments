package com.maksimov.models;

import com.maksimov.utils.validators.DepartmentNameCheck;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;

/**
 * Created on 7/19/2016.
 */
public class Department {

    private Long id;

    @NotEmpty
    @Length(max = 32, min = 5, message = "Incorrect name length")
    @CheckWith(value = DepartmentNameCheck.class, message = "Department with this name already exost")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
