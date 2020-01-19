package global.coda.hms.controller;

import global.coda.hms.model.DoctorRecord;
import global.coda.hms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Doctor controller.
 */
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    /**
     * The Doctor service.
     */
    @Autowired
    DoctorService doctorService;

    /**
     * Gets patients.
     *
     * @return the patients
     */
    @GetMapping("/all")
    public List<DoctorRecord> getPatients() {
        return doctorService.getDoctors();
    }

    /**
     * Create patient int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    @PostMapping("/create")
    public int createPatient(@RequestBody DoctorRecord doctorRecord) {
        return doctorService.createDoctor(doctorRecord);
    }

    /**
     * Gets doctor.
     *
     * @param id the id
     * @return the doctor
     */
    @GetMapping("/doctor/{id}")
    public DoctorRecord getDoctor(@PathVariable("id") int id) {
        return doctorService.getDoctor(id);
    }

    /**
     * Delete patient.
     *
     * @param id the id
     */
    @GetMapping("/doctor/delete/{id}")
    public void deletePatient(@PathVariable("id") int id) {
        doctorService.deleteDoctor(id);
    }

    /**
     * Patient update int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    @PostMapping("/doctor/update")
    public int patientUpdate(@RequestBody DoctorRecord doctorRecord) {
        return doctorService.updateDoctor(doctorRecord);
    }


    /**
     * Doctors patients list.
     *
     * @return the list
     */
    @GetMapping("/patients")
    public List<DoctorRecord> doctorsPatients() {
        return doctorService.getDoctorPatients();
    }

    @PostMapping("/patientEntry/{doctorID}/{patientID}")
    public int patientDoctorMap(@PathVariable("patientID") int patientID, @PathVariable("doctorID") int doctorID) {
        return doctorService.patientDoctorMap(patientID, doctorID);
    }


}
