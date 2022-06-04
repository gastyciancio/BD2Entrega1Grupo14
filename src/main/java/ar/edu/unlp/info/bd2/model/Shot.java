package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Vaccine vaccine;

    private Date date;

    @ManyToOne
    private Centre centre;

    @ManyToOne
    private Nurse nurse;

    @OneToOne(cascade = CascadeType.ALL)
    private ShotCertificate shotCertificate;

    public Shot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) {
        this.patient = patient;
        this.vaccine = vaccine;
        this.date = date;
        this.centre = centre;
        this.nurse = nurse;

    }

    public Shot() {
    }

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

    public Date getDate() {
        return date;
    }

    public void setShotCertificate(ShotCertificate shotCertificate) {
        this.shotCertificate = shotCertificate;
    }
}