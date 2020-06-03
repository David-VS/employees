package be.ehb.employees.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//overerven van CrudRepository<type Entity, type van primary key>
//bevat al methoden voor insert, delete en select all, select by id
public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

    //je kan uitbreiden met eigen methoden
    Iterable<Employee> findAllByName (String name);

    @Query("SELECT e FROM Employee e WHERE e.name like %:input%")
    Iterable<Employee> findByNameContains(String input);
}
