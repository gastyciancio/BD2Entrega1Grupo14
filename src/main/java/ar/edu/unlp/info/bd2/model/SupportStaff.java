package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SupportStaff extends Employee{

    @Id
    @GeneratedValue
    private long id;

    private String area;




}
