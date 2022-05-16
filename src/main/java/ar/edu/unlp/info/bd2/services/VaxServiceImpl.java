package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.VaxException;
import ar.edu.unlp.info.bd2.repositories.VaxRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VaxServiceImpl implements VaxService{

    private VaxRepository vax_repo;
    public VaxServiceImpl(VaxRepository repo){
        this.vax_repo=repo;
    }
//--------------------------------------------- Entrega 1 -----------------------------------------------------
    /**
     *
     * @param email email del usuario con el cual ingresa al sitio
     * @param fullname nombre y apellido del usuario
     * @param password clave con la que el usuario ingresa al sitio
     * @param dayOfBirth fecha de nacimiento del usuario
     * @return el usuario creado
     * @throws VaxException
     */
    public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException{
        Patient newPatient = new Patient(email, fullname, password, dayOfBirth);

        try {
            return vax_repo.savePatient(newPatient);
        }
        catch (VaxException e) {
            throw e;
        }
    };

    /**
     *
     * @param name nombre de la vacuna
     * @return la vacuna creada
     * @throws VaxException
     */
    public Vaccine createVaccine(String name) throws VaxException {
        Vaccine newVaccine = new Vaccine(name);

        try {
            return vax_repo.saveVaccine(newVaccine);
        }
        catch (VaxException e) {
            throw e;
        }
    }

    /**
     *
     * @param patient paciente vacunado
     * @param vaccine vacuna aplicada
     * @param date fecha de aplicación
     * @param centre el centro de vacunación donde se aplicó
     * @param nurse enfermero/a que aplico la vacuna
     * @return el usuario creado
     * @throws VaxException
     */

    public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException{
        Shot newShot = new Shot(patient, vaccine, date, centre, nurse);

        try{
            return vax_repo.saveShot(newShot);
        }
        catch (VaxException e) {
            throw e;
        }
    }


    /**
     *
     * @param email email del usuario
     * @return
     */

    public Optional<Patient> getPatientByEmail(String email) {
        return vax_repo.findPatientByEmail(email);
    }

    /**
     *
     * @param name nombre de la vacuna
     * @return
     */

    public Optional<Vaccine> getVaccineByName(String name) {
        return vax_repo.getVaccineByName(name);
    }

    /**
     *
     * @param name nombre del centro de vacunación
     * @return el centro de vacunación nuevo
     * @throws VaxException
     */

    public Centre createCentre(String name) throws VaxException{
        Centre newCentre = new Centre(name);
        try{
            return vax_repo.saveCentre(newCentre);
        }
        catch (VaxException e){
            throw e;
        }
    }


    /**
     * @param dni el dni
     * @param fullName nombre del/la enfermero/a
     * @param experience experiencia en años
     * @return el enfermero creado
     * @throws VaxException
     */

   public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException{

       Nurse newNurse = new Nurse(fullName,dni,experience);
       try{
           return vax_repo.saveNurse(newNurse);
       }
       catch (VaxException e){
           throw e;
       }

   };

    /**
     * @param dni el dni
     * @param fullName nombre completo
     * @param area el area o areas de trabajo
     * @return el personal de apoyo creado
     * @throws VaxException
     * */

    public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException {
        SupportStaff newSupportStaff = new SupportStaff(dni, fullName, area);
        try{
            return vax_repo.saveSupportStaff(newSupportStaff);
        }
        catch (VaxException e){
            throw e;
        }
    }

    /**
     * @return el esquema nueva vacío
     * @throws VaxException
     * */


    public VaccinationSchedule createVaccinationSchedule() throws VaxException{
        VaccinationSchedule newVaccinationSchedule = new VaccinationSchedule();
        try{
            return vax_repo.saveVaccinationSchedule(newVaccinationSchedule);
        }
        catch (VaxException e){
            throw e;
        }
    }

    /**
     * @param id el id del esquema
     * @return el esquema de vacunación
     * */

    public VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException {
        try {
            return vax_repo.getVaccinationScheduleById(id);
        }
        catch (VaxException e){
            throw e;
        }
    }

    /**
     * @param name el nombre del centro a buscar
     * @return el centro
     * */

    public Optional<Centre> getCentreByName(String name) throws VaxException{
        try {
            return vax_repo.getCentreByName(name);
        }
        catch (VaxException e){
            throw e;
        }
    };

    /**
     * @param staff el staff a actualizar
     * @return el staff
     * @throws VaxException
     */
    /**
    SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException;
**/

    /**
     * @param centre el centre a actualizar
     * @return el centre
     * @throws VaxException
     */
    public Centre updateCentre(Centre centre){
        return vax_repo.updateCentre(centre);
    }


    /**
     * @param dni el dni del SupportStaff a buscar
     * @return el SupportStaff
     * */
    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        return vax_repo.getSupportStaffByDni(dni);
    };

    public VaccinationSchedule updateVaccinationSchedule(VaccinationSchedule vaccinationSchedule) throws VaxException {
        try {
            return vax_repo.updateVaccinationSchedule(vaccinationSchedule);
        }
        catch (VaxException e) {
            throw e;
        }
    }

    // -------------------------------------------------- Entrega2 ------------------------------------------------------


    public List<Patient> getAllPatients(){
        return vax_repo.getAllPatients();
    }

    public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
        return vax_repo.getNurseWithMoreThanNYearsExperience(years);
    }

    public List<Centre> getCentresTopNStaff(int n) {
        return vax_repo.getCentresTopNStaff(n);
    }

    public Centre getTopShotCentre() {
        return vax_repo.getTopShotCentre();
    }

}
