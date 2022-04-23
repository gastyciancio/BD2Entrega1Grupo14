package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shot {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Patient patient;

    @OneToOne()
    private Vaccine vaccine;

    private Date date;

    @OneToOne
    private Centre centre;

    @OneToOne
    private Nurse nurse;

    @OneToOne(cascade = CascadeType.ALL)
    private ShotCertificate shotCertificate;

    public long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public Centre getCentre() {
        return centre;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public ShotCertificate getShotCertificate() {
        return shotCertificate;
    }
}