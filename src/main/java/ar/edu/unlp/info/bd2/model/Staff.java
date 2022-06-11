package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullname;

    @Column(unique = true)
    private String dni;

    @ManyToMany(mappedBy = "staff")
    private Collection<Centre> centres = new ArrayList<>();

    public Staff() {
    }

    public Staff(String fullname, String dni) {
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
