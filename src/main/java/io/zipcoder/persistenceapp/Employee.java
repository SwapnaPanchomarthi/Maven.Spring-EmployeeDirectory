package io.zipcoder.persistenceapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Employee {
    @Id
    private int ID;
    private String firstName;
    private String lastName;
    private String title;
    private Integer phoneNumber;
    private String email;
    private Date hireDate;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "manger")
    private Employee manager;
    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees_emp;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department")
    private Department department;

    @JsonIgnore
    public Set<Employee> getEmployees_emp() {
        return employees_emp;
    }

    public void setEmployees_emp(Set<Employee> employees_emp) {
        this.employees_emp = employees_emp;
    }



    public Employee()
    {

    }

    public Employee(int id){
        this.ID=id;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", manager='" + manager + '\'' +
                ", departmentNumber=" + department +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @JsonIgnore
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartmentNumber() {
        return department;
    }

    @JsonIgnore
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDepartmentNumber(Department departmentNumber) {
        this.department = departmentNumber;
    }
}

