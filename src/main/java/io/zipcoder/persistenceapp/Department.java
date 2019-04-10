package io.zipcoder.persistenceapp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Department {
    @Id
    private  int departmentNumber;
    private String departName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true, name = "departmentManagerId")
    private Employee departmentManagerId;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    public Department(){
        this.departmentNumber=0;
        this.departName="";
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


    public int getDeptId() {
        return departmentNumber;
    }

    public void setDeptId(int deptId) {
        this.departmentNumber = deptId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Employee getDepartmentManager() {
        return departmentManagerId;
    }

    public void setDepartmentManager(Employee departmentManager) {
        this.departmentManagerId = departmentManager;
    }

//    public void addEmployees(Employee employee){
//        employees.add(employee);
//    }



}
