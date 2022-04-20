package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private long id;

    private String password;

    private String fullname;

    private Date dayOfBirth;


}

