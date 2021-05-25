package be.ehb.employees.controller;

import be.ehb.employees.model.dao.EmployeeDAO;
import be.ehb.employees.model.dao.TeamDAO;
import be.ehb.employees.model.entities.Employee;
import be.ehb.employees.model.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teams")
public class TeamController {

    TeamDAO mTeamDAO;
    EmployeeDAO mEmployeeDAO;

    @Autowired
    public TeamController(TeamDAO mTeamDAO, EmployeeDAO mEmployeeDAO) {
        this.mTeamDAO = mTeamDAO;
        this.mEmployeeDAO = mEmployeeDAO;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Team> getAllTeams(){
        return mTeamDAO.findAll();
    }

    @PostMapping(value = "/addemployee")
    @ResponseBody
    public HttpStatus addEmployeeToTeam(@RequestParam(value = "team_name") String team,
                                        @RequestParam(value = "employee_id") int id){
        if(mTeamDAO.findById(team).isPresent()){
            Team tempTeam = mTeamDAO.findById(team).get();
            if(mEmployeeDAO.findById(id).isPresent()){
                Employee tempEmployee = mEmployeeDAO.findById(id).get();
                tempEmployee.setTeam(tempTeam);
                mEmployeeDAO.save(tempEmployee);
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }
}
