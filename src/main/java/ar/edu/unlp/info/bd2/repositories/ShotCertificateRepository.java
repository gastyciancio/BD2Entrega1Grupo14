package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.ShotCertificate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ShotCertificateRepository extends CrudRepository<ShotCertificate, Long> {

    @Query("FROM ShotCertificate sc WHERE date BETWEEN :startDate AND :endDate")
    List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate);

}
