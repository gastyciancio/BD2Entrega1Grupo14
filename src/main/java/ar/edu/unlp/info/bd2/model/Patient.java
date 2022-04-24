package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String password;

    private String fullname;

    private Date dayOfBirth;

    @OneToMany(mappedBy = "patient")
    private Collection<Shot> shots;

    public Patient() {
    }

    public Patient(String email, String fullname, String password, Date dayOfBirth) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public Collection<Shot> getShots() {
        return shots;
    }


}

