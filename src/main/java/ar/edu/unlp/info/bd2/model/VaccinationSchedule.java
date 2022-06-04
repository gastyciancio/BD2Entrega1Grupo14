package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VaccinationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "vaccination_schedules")
    private List<Vaccine> vaccines = new ArrayList<>();

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
