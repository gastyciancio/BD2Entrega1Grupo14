package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Patient;
import ar.edu.unlp.info.bd2.model.SupportStaff;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupportStaffRepository extends CrudRepository<SupportStaff, Long> {

    Optional<SupportStaff> findByDni(String dni);
}
