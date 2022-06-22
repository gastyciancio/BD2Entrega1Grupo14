package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
public class SpringDataVaxService implements VaxService {

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ShotCertificateRepository shotCertificateRepository;

    @Autowired
    private ShotRepository shotRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SupportStaffRepository supportStaffRepository;

    @Autowired
    private VaccinationSchedulerRepository vaccinationSchedulerRepository;

    @Autowired
    private VaccineRepository vaccineRepository;


    public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException {
        Patient newPatient = new Patient(email, fullname, password, dayOfBirth);

        try {
            return patientRepository.save(newPatient);
        }
        //IllegalArgumentException
        catch (Exception e) {
            throw new VaxException("Constraint Violation");
        }
    }

    public Vaccine createVaccine(String name) throws VaxException {
        Vaccine newVaccine = new Vaccine(name);

        try {
            return vaccineRepository.save(newVaccine);
        }
        //IllegalArgumentException
        catch (Exception e) {
            throw new VaxException("Constraint Violation");
        }


    }

    public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException {
        Shot newShot = new Shot(patient, vaccine, date, centre, nurse);
        ShotCertificate newShotCertificate = createCertificate(newShot.getDate());

        try {
            shotCertificateRepository.save(newShotCertificate);
            newShot.setShotCertificate(newShotCertificate);

            newShot = shotRepository.save(newShot);


            Patient patientToUpdate = addShotToPatient(newShot);

            //calling save() to update an object inside a transactional method is not mandatory
            patientRepository.save(patientToUpdate);

            return newShot;
        }
        //IllegalArgumentException
        catch (Exception e) {
            throw new VaxException("Constraint Violation");
        }
    }

    //Helper de createShot. ESTA BIEN ACA?
    private ShotCertificate createCertificate(Date date){
        Random r = new Random();
        ShotCertificate newCertificate = new ShotCertificate(date,r.nextInt(Integer.MAX_VALUE));
        return newCertificate;
    }

    //Helper de createShot. ESTA BIEN ACA?
    private Patient addShotToPatient(Shot newShot) {
        Patient patient = newShot.getPatient();
        patient.addShot(newShot);
        return patient;
    }

    public Optional<Patient> getPatientByEmail(String email){
        return patientRepository.findByEmail(email);
    }

    public Optional<Vaccine> getVaccineByName(String name) {
        return vaccineRepository.findByName(name);
    }

    public Centre createCentre(String name) throws VaxException {

        Centre newCentre = new Centre(name);
        try{
            return centreRepository.save(newCentre);
        }
        catch (Exception e){
            throw new VaxException("Constraint Violation");
        }
    }

    public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException {
        Nurse newNurse = new Nurse(fullName,dni,experience);
        try{
            return nurseRepository.save(newNurse);
        }
        catch (Exception e){
            throw new VaxException("Constraint Violation");
        }
    }

    public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException {
        SupportStaff newSupportStaff = new SupportStaff(dni, fullName, area);
        try{
            return supportStaffRepository.save(newSupportStaff);
        }
        catch (Exception e){
            throw new VaxException("Constraint Violation");
        }
    }

    public VaccinationSchedule createVaccinationSchedule() throws VaxException {
        VaccinationSchedule newVaccinationSchedule = new VaccinationSchedule();
        try{
            return vaccinationSchedulerRepository.save(newVaccinationSchedule);
        }
        catch (Exception e){
            throw new VaxException("Constraint Violation");
        }
    }

    public VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException {

        try {
            Optional<VaccinationSchedule> vs = vaccinationSchedulerRepository.findById(id);
            return vs.get();
        }
        //IllegalArgumentException - if id is null. | Entra si id == null o si no habia un vs con ese id?
        catch (Exception e) {
            throw new VaxException("Constraint Violation");
        }
    }

    public Optional<Centre> getCentreByName(String name) throws VaxException {
        try {
            return centreRepository.findByName(name);
        }
        catch (Exception e) {
            throw new VaxException("Constraint Violation");
        }

    }

    public SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException {
        try {
            return supportStaffRepository.save(staff);
        }
        catch (Exception e) {
            throw new VaxException("Constraint Violation");
        }
    }

    public Centre updateCentre(Centre centre) {
            return centreRepository.save(centre);
    }

    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        return supportStaffRepository.findByDni(dni);
    }

    public VaccinationSchedule updateVaccinationSchedule(VaccinationSchedule schedule) throws VaxException {
       return vaccinationSchedulerRepository.save(schedule);

    }

    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
        return nurseRepository.getNurseWithMoreThanNYearsExperience(years);
    }

    public List<Centre> getCentresTopNStaff(int n) {
        Pageable pageable = PageRequest.of(1,n);
        return centreRepository.getCentresTopNStaff();
    }

    public Centre getTopShotCentre(){
        Pageable pageable = PageRequest.of(1,1);
        return centreRepository.getTopShotCentre();
    }

    public List<Nurse> getNurseNotShot() {
        return nurseRepository.getNurseNotShot();
    }

    public String getLessEmployeesSupportStaffArea() {
        Pageable pageable = PageRequest.of(1,1);
        return staffRepository.getLessEmployeesSupportStaffArea();
    }

    public List<Staff> getStaffWithName(String name) {
        return staffRepository.getStaffWithName(name);
    }

    public List<Vaccine> getUnappliedVaccines() {
        return vaccineRepository.getUnappliedVaccines();
    }

    public List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate) {
        return shotCertificateRepository.getShotCertificatesBetweenDates(startDate, endDate);
    }
}
