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
                name = "vaccine_vaccination_schedule",
                joinColumns = @JoinColumn(name = "vaccine_id"),
                inverseJoinColumns = @JoinColumn(name = "vaccination_schedule_id"))
        private Collection<VaccinationSchedule> vaccination_schedule;


        public long getId() {
                return id;
        }

        public String getName() {
                return name;
        }
}
