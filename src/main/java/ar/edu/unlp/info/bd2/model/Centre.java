package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Centre {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "centre_employee",
            joinColumns = @JoinColumn(name = "centre_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Collection<Employee> employees;
}
