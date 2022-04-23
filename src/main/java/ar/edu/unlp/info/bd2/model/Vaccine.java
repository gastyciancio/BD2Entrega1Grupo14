package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Vaccine {

        @Id
        @GeneratedValue
        private long id;

        private String name;

        @ManyToMany
        @JoinTable(
                name = "vaccine_vaccineshceduler",
                joinColumns = @JoinColumn(name = "vaccine_id"),
                inverseJoinColumns = @JoinColumn(name = "vacinnescheduluder_id"))
        private Collection<VaccineScheduler> vaccine_schedulers;


}
