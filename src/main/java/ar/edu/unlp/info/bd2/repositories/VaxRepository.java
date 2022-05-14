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
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Guardamos un supportStaff en la base de datos, usando el método getSupportStaffById para
     * recuperar el id de este.
     * **/

    public SupportStaff saveSupportStaff(SupportStaff newSupportStaff) throws VaxException{
        try{
            long supportStaffId = (long) sessionFactory.getCurrentSession().save(newSupportStaff);
            return getSupportStaffById(supportStaffId);
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }
    }

    private SupportStaff getSupportStaffById(long id) {
        String query = "FROM SupportStaff WHERE id = :idS";  //HQL
        return (SupportStaff) sessionFactory.getCurrentSession().createQuery(query).setParameter("idS", id).uniqueResult();
    }

    public Centre updateCentre(Centre centre){
        sessionFactory.getCurrentSession().update(centre);
        return getCentreById(centre.getId());
    }

    public Optional<SupportStaff> getSupportStaffByDni(String dniS) {
        String query = "FROM SupportStaff WHERE dni = :dniS";  //HQL
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("dniS", dniS).uniqueResultOptional();
    }

    /**
     * Guardamos un vaccinationSchedule en la base de datos, usando el método getVaccinationScheduleById para
     * recuperar el id de este.
     * **/

    public VaccinationSchedule saveVaccinationSchedule(VaccinationSchedule newVaccinationSchedule) throws VaxException{
        try{
            long vaccinationScheduleId = (long) sessionFactory.getCurrentSession().save(newVaccinationSchedule);
            return getVaccinationScheduleById(vaccinationScheduleId);
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }
    }

    public VaccinationSchedule getVaccinationScheduleById(long id) throws VaxException{
        String query = "FROM VaccinationSchedule WHERE id = :idVS";  //HQL
        return (VaccinationSchedule) sessionFactory.getCurrentSession().createQuery(query).setParameter("idVS", id).uniqueResult();
    }

    public VaccinationSchedule updateVaccinationSchedule(VaccinationSchedule vaccinationSchedule) throws VaxException{
        sessionFactory.getCurrentSession().update(vaccinationSchedule);
        try {
            return getVaccinationScheduleById(vaccinationSchedule.getId());
        }
        catch (PersistenceException e) {
            throw new VaxException("Constraint Violation");
        }
    }

    // -------------------------------------------------- Entrega2 ------------------------------------------------------

    public List<Patient> getAllPatients() {
        String query = "FROM Patient";  //HQL
        return  sessionFactory.getCurrentSession().createQuery(query).list();
    }

    public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
        String query = "FROM Nurse WHERE experience > :years";  //HQL
        return  sessionFactory.getCurrentSession().createQuery(query).setParameter("years", years).list();
    }

    public List<Centre> getCentresTopNStaff(int n) {
        String query = "FROM Centre c order by size(c.employees)";
        return  sessionFactory.getCurrentSession().createQuery(query).setMaxResults(n).list();


    }

}
