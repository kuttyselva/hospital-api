package global.coda.hms.controller;

import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
import global.coda.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Patient controller.
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

    /**
     * The Patient service.
     */
    @Autowired
    PatientService patientService;


    /**
     * Gets patient.
     *
     * @return the patient
     */
    @GetMapping("/all")
    public List<PatientRecord> getPatients() {
        return patientService.getPatients();
    }

    /**
     * Create patient int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    @PostMapping("/create")
    public int createPatient(@RequestBody PatientRecord patientRecord) {
        return patientService.createPatient(patientRecord);
    }


    /**
     * Gets patient.
     *
     * @param id the id
     * @return the patient
     */
    @GetMapping("/patient/{id}")
    public PatientRecord getPatient(@PathVariable("id") int id) {
        return patientService.getPatient(id);
    }

    /**
     * Delete patient.
     *
     * @param id the id
     */
    @GetMapping("/patient/delete/{id}")
    public void deletePatient(@PathVariable("id") int id) {
        patientService.deletePatient(id);
    }

    /**
     * Patient update int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    @PostMapping("/patient/update")
    public int patientUpdate(@RequestBody PatientRecord patientRecord) {
        return patientService.updatePatient(patientRecord);
    }

    /**
     * Gets all doctors.
     *
     * @param patientId the patient id
     * @return the all doctors
     */
    @GetMapping("/patient/{id}/getDoctors")
    public List<DoctorRecord> getAllDoctors(@PathVariable("id") int patientId) {
        return patientService.getAllDoctors(patientId);
    }
}
