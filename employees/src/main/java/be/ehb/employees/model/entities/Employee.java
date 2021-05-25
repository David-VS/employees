package be.ehb.employees.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity //mapt op een tabel in databank
public class Employee {

    @Id // = primary key in tabel
    @GeneratedValue(strategy = GenerationType.IDENTITY) //werkt enkel voor gehele getallen int, long, short
    private int id;
    private String name, phonenr, email;
    @ManyToOne
    @JoinColumn(name = "team_name", nullable = true)
    private Team team;
    //POJO -> Plain old Java Object
    //default constructor, getters en setters
    public Employee() {
    }

    public Employee(String name, String phonenr, String email) {
        this.name = name;
        this.phonenr = phonenr;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
