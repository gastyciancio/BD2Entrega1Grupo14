package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class VaccineScheduler {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "vaccine_schedulers")
    private Collection<Vaccine> vaccines;

}
