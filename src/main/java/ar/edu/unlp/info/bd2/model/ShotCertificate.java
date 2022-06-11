package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShotCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    private int serial_number;

    public ShotCertificate() {
    }

    public ShotCertificate(Date date, int serial_number) {
        this.date = date;
        this.serial_number = serial_number;
    }

    public int getSerialNumber() {
        return serial_number;
    }
}
