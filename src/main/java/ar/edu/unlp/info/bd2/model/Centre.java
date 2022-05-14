package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Collection<Employee> employees = new ArrayList<>();

    public Centre() {
    }

    public Centre(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Employee> getStaffs() {
        return employees;
    }

    public void addStaff(Employee staff){
        this.employees.add(staff);
        staff.getCentres().add(this);
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }
}
