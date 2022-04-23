package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class VaccinationSchedule {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "vaccination_schedule")
    private Collection<Vaccine> vaccines;

    public Collection<Vaccine> getVaccines() {
        return vaccines;
    }

    public long getId() {
        return id;
    }
}
