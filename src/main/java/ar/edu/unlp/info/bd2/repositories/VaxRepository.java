package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.persistence.*;
import javax.transaction.Transactional;
@Repository
public class VaxRepository  {



    public void savePatient (Patient newPatient) throws VaxException {




    }

    public Optional<Patient> findPatientByEmail(String email){
       return null;






    }

    /**
    public void saveVaccine(Vaccine newVaccine) {

    }
     **/
}
