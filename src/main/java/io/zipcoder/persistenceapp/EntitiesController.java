package io.zipcoder.persistenceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping(path="/API")
    public class EntitiesController {

    private EntitiesService entitiesService;

    @Autowired
    public EntitiesController(EntitiesService entitiesService)
    {
        this.entitiesService=entitiesService;
    }



    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        return new ResponseEntity<>(entitiesService.create(employee), HttpStatus.OK);

    }

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {

            return new ResponseEntity<Department>(entitiesService.create(department), HttpStatus.OK);

    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>>getAllEmployees(){
        return new ResponseEntity<>(entitiesService.findAllEmp(), HttpStatus.OK);
    }


    @GetMapping("/department/all")
    public ResponseEntity<Iterable<Department>> getAllDept()
    {
        return new ResponseEntity<>(entitiesService.getAllDept(), HttpStatus.OK);

    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<Department> getDepartment(@PathVariable int deptId)
    {
        return new ResponseEntity<Department>(entitiesService.getDept(deptId), HttpStatus.OK);
    }

    @PutMapping("/employees/updatemanager/{empId}")
    public void setManager(@PathVariable int empId, Employee manager)
    {
        entitiesService.setManager(empId, manager);
    }

    @PutMapping("/employees/{empId}")
    public Employee updateEmployee(@PathVariable int empId, @RequestBody String fname)
    {
        return (Employee) entitiesService.updateEmployee(empId, fname);
    }

    @PutMapping("/department/updatemanager/{deptId}/{manager}")
    public ResponseEntity<Department>updateDeptManager(@PathVariable int deptId,  Employee manager)
    {
        return new ResponseEntity<>(entitiesService.updateDeptManager(deptId, manager), HttpStatus.OK);
    }


    @PutMapping("/department/updateDepartmentName/{deptId}")
    public ResponseEntity<Department> updateDepartmentName(@PathVariable int deptId, @RequestBody String deptName){

        return new ResponseEntity<> (entitiesService.updateDeptName(deptId, deptName), HttpStatus.OK);
    }

    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee>getEmploee(@PathVariable int empId)
    {
        return new ResponseEntity<Employee>(entitiesService.getEmployee(empId), HttpStatus.OK);
    }

    @GetMapping("/employees/byManager/{manager}")
    public ResponseEntity<Iterable<Employee>>getEmpByManager(@PathVariable int manager)
    {
        return new ResponseEntity<>(entitiesService.getEmployeesByManager(manager), HttpStatus.OK);

    }

    @GetMapping("/employees/null")
    public ResponseEntity<Iterable<Employee>> getNullMgr()
    {
        return new ResponseEntity<>(entitiesService.getNullManager(), HttpStatus.OK);
    }

    @PutMapping("employees/{empId}/{deptId}")
    public ResponseEntity<Employee> setDeptManager(@PathVariable int empId, @PathVariable int deptId)
    {
        return new ResponseEntity<>(entitiesService.setEmpByDept(empId, deptId), HttpStatus.OK);
    }

    }
