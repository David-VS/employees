package be.ehb.employees.model.dao;

import be.ehb.employees.model.entities.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamDAO extends CrudRepository<Team, String> {
}
