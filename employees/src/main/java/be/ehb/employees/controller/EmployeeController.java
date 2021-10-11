package be.ehb.employees.controller;

import be.ehb.employees.model.entities.Employee;
import be.ehb.employees.model.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    EmployeeDAO dao;

    @Autowired
    public EmployeeController(EmployeeDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Employee> findAll(){
        return dao.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Employee> findById(@PathVariable(value = "id") int id){
        return dao.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Iterable<Employee> findAllByName(@RequestParam(value = "name") String name){
        return dao.findAllByName(name);
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public Iterable<Employee> findByPartOfName(@RequestParam(value = "name") String part){
        return dao.findByNameContains(part);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity addOne(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "phonenr") String phonenr,
                                 @RequestParam(value = "email") String email){
        Employee temp = new Employee(name, phonenr, email);
        dao.save(temp);

        return new ResponseEntity(HttpStatus.OK);
    }
}

