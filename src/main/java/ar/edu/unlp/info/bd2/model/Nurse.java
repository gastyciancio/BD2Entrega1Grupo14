package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Nurse extends Employee{

    @Id
    @GeneratedValue
    private long id;

    private String dni;

    private String fullname;

    private Integer experience;

}
