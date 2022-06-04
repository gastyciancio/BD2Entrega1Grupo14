package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Vaccine {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(unique=true)
        private String name;

        @OneToMany(mappedBy = "vaccine")
        private List<Shot> shots = new ArrayList<Shot>();

        @ManyToMany
        @JoinTable(
                name = "vaccine_vaccination_schedule",
                joinColumns = @JoinColumn(name = "vaccine_id"),
                inverseJoinColumns = @JoinColumn(name = "vaccination_schedule_id")
        )
        private Collection<VaccinationSchedule> vaccinationSchedules = new ArrayList<>();

        public Vaccine() {
        }

        public Vaccine(String name) {
                this.name = name;
        }

        public long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public Collection<VaccinationSchedule> getVaccinationSchedules() {
                return vaccinationSchedules;
        }
}
