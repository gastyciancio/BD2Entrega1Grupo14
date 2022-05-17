package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Nurse extends Employee{

    @Id
    @GeneratedValue
    private long id;

    private Integer experience;

    public Nurse(String fullname, String dni, Integer experience) {
        super(fullname, dni);
        this.experience = experience;
    }

    public Nurse() {

    }

    public Integer getExperience() {
        return experience;
    }
}
