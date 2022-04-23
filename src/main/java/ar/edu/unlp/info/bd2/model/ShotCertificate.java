package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShotCertificate {

    @Id
    @GeneratedValue
    private long id;

    private Date date;

    private int serial_number;

    public int getSerialNumber() {
        return serial_number;
    }
}
