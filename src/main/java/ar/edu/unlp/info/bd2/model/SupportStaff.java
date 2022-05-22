package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SupportStaff extends Staff {
    @Id
    @GeneratedValue
    private long id;

    private String area;

    public SupportStaff(){

    }

    public SupportStaff(String dni, String fullname, String area) {
        super(fullname, dni);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public long getId() {
        return id;
    }
}
