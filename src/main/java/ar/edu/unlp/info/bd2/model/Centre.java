package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "centre_staff",
            joinColumns = @JoinColumn(name = "centre_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private Collection<Staff> staff = new ArrayList<>();

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

    public Collection<Staff> getStaffs() {
        return staff;
    }

    public void addStaff(Staff staff){
        this.staff.add(staff);
        staff.getCentres().add(this);
    }

}
