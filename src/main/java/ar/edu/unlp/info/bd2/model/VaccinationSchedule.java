package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class VaccinationSchedule {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "vaccination_schedule")
    private List<Vaccine> vaccines;

    public VaccinationSchedule() {
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public long getId() {
        return id;
    }

    public void addVaccine(Vaccine v){
        this.vaccines.add(v);
    }
}
