package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.VaxService;
import ar.edu.unlp.info.bd2.repositories.VaxException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import ar.edu.unlp.info.bd2.config.HibernateConfiguration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.persistence.*;

public class VaxRepository  {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Guardamos un paciente (patient) en la base de datos, usando el método getPatientById para
     * recuperar el id de este.
     * **/
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

    public Vaccine saveVaccine(Vaccine newVaccine) throws VaxException {
        try {
            long vaccineId = (long) sessionFactory.getCurrentSession().save(newVaccine);
            return getVaccineById(vaccineId);
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }
    }

    public Vaccine getVaccineById(long id) {
        String query = "FROM Vaccine WHERE id = :idV";  //HQL
        return (Vaccine) sessionFactory.getCurrentSession().createQuery(query).setParameter("idV", id).uniqueResult();
    }

    public Optional<Vaccine> getVaccineByName(String name) {
        String query = "FROM Vaccine WHERE name = :nameVaccine";  //HQL
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("nameVaccine", name).uniqueResultOptional();
    }

    /**
     * Guardamos una aplicación (shot) en la base de datos, usando el método getShotById para
     * recuperar el id de este.
     * **/

    public Shot saveShot(Shot newShot) throws VaxException {
        try {
            ShotCertificate newCertificate = createCertificate();
            sessionFactory.getCurrentSession().save(newCertificate);

            newShot.setShotCertificate(newCertificate);
            long ShotId = (long) sessionFactory.getCurrentSession().save(newShot);

            addShotToPatient(newShot);

            return getShotById(ShotId);
        }
        catch (PersistenceException e) {

            throw new VaxException("Constraint Violation");
        }
    }

    public Shot getShotById(long id) {
        String query = "FROM Shot WHERE id = :idS";  //HQL
        return (Shot) sessionFactory.getCurrentSession().createQuery(query).setParameter("idS", id).uniqueResult();
    }

    private void addShotToPatient(Shot newShot) {
        Patient patient = newShot.getPatient();
        patient.addShot(newShot);
        sessionFactory.getCurrentSession().update(patient);
    }

    public ShotCertificate createCertificate(){
        Random r = new Random();
        ShotCertificate newCertificate = new ShotCertificate(DateTime.now().toDate(),r.nextInt(Integer.MAX_VALUE));
        return newCertificate;
    }

    /**
     * Guardamos un enfermero (nurse) en la base de datos, usando el método getNurseById para
     * recuperar el id de este.
     * **/

    public Nurse saveNurse(Nurse newNurse) throws VaxException {
        try {
            long NurseId = (long) sessionFactory.getCurrentSession().save(newNurse);
            return getNurseById(NurseId);
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }

    }

    public Nurse getNurseById(long id) {
        String query = "FROM Nurse WHERE id = :idN";  //HQL
        return (Nurse) sessionFactory.getCurrentSession().createQuery(query).setParameter("idN", id).uniqueResult();
    }

    /**
     * Guardamos un centro (centre) en la base de datos, usando el método getCentreById para
     * recuperar el id de este.
     * **/

    public Centre saveCentre(Centre newCentre) throws VaxException{
        try {
            long CentreId = (long) sessionFactory.getCurrentSession().save(newCentre);
            return getCentreById(CentreId);
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }
    }
    public Centre getCentreById(long id) {
        String query = "FROM Centre WHERE id = :idC";  //HQL
        return (Centre) sessionFactory.getCurrentSession().createQuery(query).setParameter("idC", id).uniqueResult();
    }

    public Optional<Centre> getCentreByName(String name) throws VaxException{
        String query = "FROM Centre WHERE name = :nameC";  //HQL
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("nameC", name).uniqueResultOptional();
    }


}
