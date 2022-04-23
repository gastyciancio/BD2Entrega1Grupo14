package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShotCertificate {

    @Id
    @GeneratedValue
    private long id;

    private Date date;

    private int serie;

    public int getSerialNumber() {
        return serial_number;
    }
}
