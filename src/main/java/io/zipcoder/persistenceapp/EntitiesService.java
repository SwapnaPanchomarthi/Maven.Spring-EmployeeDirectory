package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.Department;
import io.zipcoder.persistenceapp.Employee;
import io.zipcoder.persistenceapp.DeptRepo;
import io.zipcoder.persistenceapp.EntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EntitiesService {

    private EntityRepo entityRepo;
    private DeptRepo deptRepo;

    @Autowired
    public EntitiesService(EntityRepo entityRepo, DeptRepo deptRepo)
    {
        this.entityRepo=entityRepo;
        this.deptRepo=deptRepo;

    }

    public EntitiesService(EntityRepo mockRepo) {
    }

    public Employee create(Employee employee)
    {
     return entityRepo.save(employee);
    }




    public void setManager(int employeeId, Employee manager)
    {
        Employee employee=entityRepo.findOne(employeeId);
        employee.setManager(manager);
        entityRepo.save(employee);
    }

    public Employee updateEmployee(int empId, String fname)
    {
        Employee employee1=entityRepo.findOne(empId);
            employee1.setFirstName(fname);
            return entityRepo.save(employee1);
    }


    public Employee getEmployee(int empId)
    {
        return entityRepo.findOne(empId);
    }

    public Iterable<Employee> findAllEmp()
    {
        return entityRepo.findAll();
    }

    //Get the list of employees under a particular manager
    public Iterable<Employee> getEmployeesByManager(int manager) {
        return entityRepo.findByManager(manager);
    }

    public Iterable<Employee> getNullManager()
    {
        return entityRepo.findByManager(null);
    }

    public List<Employee> findEmpByDept(int deptId)
    {
        return entityRepo.findByDepartment(deptRepo.findOne(deptId));
    }


    //Get the entire reporting hierarchy for an employee (their manager + manager's manager etc.)
//    public List<Employee> findAllManager(int empId)
//    {
//        List<Employee> employees = new ArrayList<>();
//        while (entityRepo.findOne(entityRepo.findOne(empId).getManager()) != null)
//        {
//            Employee manager = entityRepo.findOne(entityRepo.findOne(empId).getManager());
//            employees.add(manager);
//            empId=manager.getID();
//        }
//        return employees;
//    }

    /*

Remove all employees from a particular department
Remove all employees under a particular manager (Including indirect reports)
Remove all direct reports to a manager. Any employees previously managed by the deleted employees should now be managed by the next manager up the hierarchy.
Get the department, title, or other attributes of a particular employee.
Merge departments (given two department names eg: A and B, move the manager of B to report to the manager of A, and update all other employees to be members of department A)
     */

    public boolean removeEmployee(int empId)
    {
        entityRepo.delete(empId);
        return true;
    }

    public void removeEmployee(Employee...employees)
    {
        entityRepo.delete(Arrays.asList(employees));
    }

    public void removeEmpByDept(int deptId)
    {
        entityRepo.delete(deptId);
    }

//    public void removeAllEmpUnderManager(int empId)
//    {
//        //while(entityRepo.findOne(entityRepo.findOne(empId).getManager())!=null)
//        entityRepo.delete(entityRepo.findOne(entityRepo.findOne(empId).getManager()));
//    }

    /*
    Remove all direct reports to a manager. Any employees previously managed by the deleted employees should now
     be managed by the next manager up the hierarchy.
     */

    public Department getEmpAttributes(int empId)
    {
       return entityRepo.findOne(empId).getDepartmentNumber();

    }

    public Department create(Department department)
    {
        return deptRepo.save(department);
    }

    public Department getDept(int deptId){
        return deptRepo.findOne(deptId);
    }


    public Iterable<Department> getAllDept(){

        return deptRepo.findAll();
    }

    public Department updateDeptManager(int deptId, Employee manager)
    {
        Department department=deptRepo.findOne(deptId);
        department.setDepartmentManager(manager);
        return deptRepo.save(department);
    }

    public Department updateDeptName(int deptId, String deptName){
        Department department=deptRepo.findOne(deptId);
        department.setDepartName(deptName);
        return deptRepo.save(department);
    }

    /*
    Merge departments (given two department names eg: A and B, move the manager of B to report to the manager of A,
     and update all other employees to be members of department A)
     */

//Get all employees of a particular department

    public Employee setEmpByDept( int empId,  int deptId)
    {
        Employee employee=entityRepo.findOne(empId);
       // Department department=entityRepo.findOne(deptId);
         employee.setDepartment(deptRepo.findOne(deptId));
        return  entityRepo.save(employee);
    }




}

