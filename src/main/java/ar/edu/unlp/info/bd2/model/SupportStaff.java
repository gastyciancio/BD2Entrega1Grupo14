package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class SupportStaff extends Employee{
    @Id
    @GeneratedValue
    private long id;

    private String area;

    public SupportStaff(){

    }

    public SupportStaff(String fullname, String dni, String area) {
        super(fullname, dni);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

}
