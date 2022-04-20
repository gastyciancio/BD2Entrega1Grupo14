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

    @ManyToOne()
    private Vaccine vaccine;

    private Date date;

    @OneToOne
    private Centre centre;

    private Nurse nurse;

    @OneToOne(cascade = CascadeType.ALL)
    private ShotCertificate shotCertificate;






}