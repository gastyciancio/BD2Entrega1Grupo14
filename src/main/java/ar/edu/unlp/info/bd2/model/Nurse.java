package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Nurse extends Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer experience;

    public Nurse(String fullname, String dni, Integer experience) {
        super(fullname, dni);
        this.experience = experience;
    }

    @OneToMany(mappedBy = "nurse")
    private List<Shot> shots = new ArrayList<Shot>();

    public Nurse() {

    }

    public Integer getExperience() {
        return experience;
    }
}
