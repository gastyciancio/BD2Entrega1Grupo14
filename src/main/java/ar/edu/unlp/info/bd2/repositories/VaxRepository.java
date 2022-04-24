package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.VaxService;
import org.hibernate.Transaction;

import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;
import java.util.Optional;
import ar.edu.unlp.info.bd2.config.HibernateConfiguration;

import javax.persistence.*;

public class VaxRepository  {

    public void savePatient (Patient newPatient) {
        // HibernateConfiguration hc = new HibernateConfiguration();
        // PlatformTransactionManager pt= hc.hibernateTransactionManager().getTransaction();
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("grupo14");
        EntityManager em= emf.createEntityManager();
        EntityTransaction t= em.getTransaction();
        try {
            t.begin();
            em.persist(newPatient);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
    }

    public Optional<Patient> findPatientByEmail(String email){

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("bd2grupo14");
        EntityManager em= emf.createEntityManager();

        try {

           Query consulta= (Query) em.createQuery("select p from Patient where p.email ="+email);
           return (Optional<Patient>) consulta.getSingleResult();

        } catch (Exception e) {
            return null;
        }

    }

    /**
    public void saveVaccine(Vaccine newVaccine) {

    }
     **/
}
