package global.coda.hms.service;

import global.coda.hms.mapper.PatientMapper;
import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Patient service.
 */
@Service
public class PatientService {
    /**
     * The Patient mapper.
     */
    @Autowired
    PatientMapper patientMapper;


    /**
     * Gets patient.
     *
     * @return the patient
     */
    public List<PatientRecord> getPatients() {
        return patientMapper.getPatients();
    }

    /**
     * Create patient int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    public int createPatient(PatientRecord patientRecord) {
        patientMapper.createUser(patientRecord);
        patientMapper.createPatient(patientRecord);
        return patientRecord.getId();
    }

    /**
     * Gets patient.
     *
     * @param id the id
     * @return the patient
     */
    public PatientRecord getPatient(int id) {
        return patientMapper.getPatient(id);
    }

    /**
     * Update patient int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    public int updatePatient(PatientRecord patientRecord) {
        patientMapper.updateUser(patientRecord);
        patientMapper.updatePatient(patientRecord);
        return patientRecord.getId();
    }


    /**
     * Delete patient.
     *
     * @param id the id
     */
    public void deletePatient(int id) {
        patientMapper.deletePatient(id);
    }

    /**
     * Gets all doctors.
     *
     * @param patientId the patient id
     * @return the all doctors
     */
    public List<DoctorRecord> getAllDoctors(int patientId) {
        return patientMapper.getAllDoctors(patientId);
    }
}
