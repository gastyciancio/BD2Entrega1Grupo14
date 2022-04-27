package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.VaxService;
import ar.edu.unlp.info.bd2.repositories.VaxException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import ar.edu.unlp.info.bd2.config.HibernateConfiguration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.persistence.*;

public class VaxRepository  {

    @Autowired
    SessionFactory sessionFactory;

    public Patient savePatient (Patient newPatient) throws VaxException {
        try {
            long patientId = (long) sessionFactory.getCurrentSession().save(newPatient);
            return getPatientById(patientId);
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }
    }

    public Patient getPatientById(long id) {
        String query = "FROM Patient WHERE id = :idP";  //HQL
        return (Patient) sessionFactory.getCurrentSession().createQuery(query).setParameter("idP", id).uniqueResult();
    }

    public Optional<Patient> findPatientByEmail(String email){
        String query = "FROM Patient WHERE email = :emailPatient";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("emailPatient", email).uniqueResultOptional();
    }

    /**
     * Guardamos una vacuna (vaccine) en la base de datos, usando el método getVaccineById para
     * recuperar el id de esta.
     * Con getVaccineByName recuperamos el nombre de la vacuna.
     * **/

    }
     **/
}
