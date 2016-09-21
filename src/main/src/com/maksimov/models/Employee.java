package com.maksimov.models;

import com.maksimov.utils.validators.EmployeeEmailCheck;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created on 7/19/2016.
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department")
    @NotNull(message = "Department id can't be null")
    private Department department;

    @Column(name = "name", length = 250)
    @NotEmpty
    @Length(max = 32, min = 5, message = "Incorrect name length")
    private String name;

    @Column(name = "birthday")
    @NotNull(message = "Incorrect date format")
    @NotEmpty(message = "Field date is empty")
    private Date birthday;

    @Column(name = "email", length = 250, unique = true)
    @NotNull(message = "Field email is empty")
    @NotEmpty(message = "Field email is empty")
    @Email(message = "incorrect email format")
    @Length(max = 100, min = 5, message = "Incorrect email length")
    @CheckWith(value = EmployeeEmailCheck.class, message = "Employee with this email already exist")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!getDepartment().equals(employee.getDepartment())) return false;
        if (!getName().equals(employee.getName())) return false;
        if (!getBirthday().equals(employee.getBirthday())) return false;
        return getEmail().equals(employee.getEmail());

    }

    @Override
    public int hashCode() {
        int result = getDepartment().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getBirthday().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }
}
