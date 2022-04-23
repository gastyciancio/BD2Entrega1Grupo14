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
            name = "centre_support_staffs",
            joinColumns = @JoinColumn(name = "centre_id"),
            inverseJoinColumns = @JoinColumn(name = "support_staffs_id"))
    private Collection<SupportStaff> support_staffs;

    @ManyToMany
    @JoinTable(
            name = "centre_nurses",
            joinColumns = @JoinColumn(name = "centre_id"),
            inverseJoinColumns = @JoinColumn(name = "nurses_id"))
    private Collection<Nurse> nurses;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<SupportStaff> getStaffs() {
        return support_staffs;
    }
}
