package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    private String fullname;

    private String dni;

    @ManyToMany(mappedBy = "employees")
    private Collection<Centre> centres;

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
