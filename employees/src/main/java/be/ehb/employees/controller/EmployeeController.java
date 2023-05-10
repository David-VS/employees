package be.ehb.employees.controller;

import be.ehb.employees.model.entities.Employee;
import be.ehb.employees.model.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeDAO dao;

    @Autowired
    public EmployeeController(EmployeeDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public Iterable<Employee> findAll(){
        return dao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Employee> findById(@PathVariable(value = "id") int id){
/*
        Optional<Employee> temp = dao.findById(id);
        if(temp.isPresent()){
            return temp.get();
        }else{
            return null;
        }
 */
        return dao.findById(id);
    }

    @PostMapping("/name")
    public Iterable<Employee> findAllByName(@RequestParam(value = "name") String name){
        return dao.findAllByName(name);
    }

    @PostMapping(value = "/search")
    public Iterable<Employee> findByPartOfName(@RequestParam(value = "name") String part){
        return dao.findByNameContains(part);
    }

    @PostMapping("/add")
    public ResponseEntity addOne(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "phonenr") String phonenr,
                                 @RequestParam(value = "email") String email){
        Employee temp = new Employee(name, phonenr, email);
        dao.save(temp);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateOne(@RequestParam(value = "id") int id,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "phonenr") String phonenr,
                                  @RequestParam(value = "email") String email){

        if(dao.existsById(id)){
            Employee temp = new Employee(id, name, phonenr, email);
            dao.save(temp);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteOne(@RequestParam int id){

        if(dao.existsById(id)){
            dao.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}

