package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Vaccine {

        @Id
        @GeneratedValue
        private long id;

        private String name;

        @OneToMany(mappedBy = "vaccine")
        private Collection<Shot> shots;

}
