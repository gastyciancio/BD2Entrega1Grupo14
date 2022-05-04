package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    private String fullname;

    @Column(unique = true)
    private String dni;

    @ManyToMany(mappedBy = "employees")
    private Collection<Centre> centres = new ArrayList<>();

    public Employee() {
    }

    public Employee(String fullname, String dni) {
        this.fullname = fullname;
        this.dni = dni;
    }

    public String getFullName() {
        return fullname;
    }

    public String getDni() {
        return dni;
    }

    public Collection<Centre> getCentres() {
        return centres;
    }


}
