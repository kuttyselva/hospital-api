package global.coda.hms.service;

import global.coda.hms.mapper.PatientMapper;
import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);


    /**
     * Gets patient.
     *
     * @return the patient
     */
    public List<PatientRecord> getPatients() {
        LOGGER.traceEntry();
        List<PatientRecord> patientRecordList = patientMapper.getPatients();
        LOGGER.traceExit(patientRecordList);
        return patientRecordList;
    }

    /**
     * Create patient int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    public int createPatient(PatientRecord patientRecord) {
        LOGGER.trace(patientRecord);
        String password = patientRecord.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String hashedPassword = passwordEncoder.encode(password);
        patientRecord.setPassword(hashedPassword);
        patientMapper.createUser(patientRecord);
        patientMapper.createPatient(patientRecord);
        int id = patientRecord.getId();
        LOGGER.traceExit(id);
        return id;
    }

    /**
     * Gets patient.
     *
     * @param id the id
     * @return the patient
     */
    public PatientRecord getPatient(int id) {
        LOGGER.trace(id);
        PatientRecord patientRecord = patientMapper.getPatient(id);
        LOGGER.traceExit(patientRecord);
        return patientRecord;
    }

    /**
     * Update patient int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    public int updatePatient(PatientRecord patientRecord) {
        LOGGER.trace(patientRecord);
        patientMapper.updateUser(patientRecord);
        patientMapper.updatePatient(patientRecord);
        int id = patientRecord.getId();
        LOGGER.traceExit(id);
        return id;
    }


    /**
     * Delete patient.
     *
     * @param id the id
     * @return the int
     */
    public int deletePatient(int id) {
        LOGGER.traceEntry();
        patientMapper.deletePatient(id);
        LOGGER.traceExit(id);
        return id;
    }

    /**
     * Gets all doctors.
     *
     * @param patientId the patient id
     * @return the all doctors
     */
    public PatientRecord getAllDoctors(int patientId) {
        LOGGER.trace(patientId);
        PatientRecord patientRecord = getPatient(patientId);
        List<DoctorRecord> doctorList = patientMapper.getAllDoctors(patientId);
        patientRecord.setDoctorRecordList(doctorList);
        LOGGER.traceExit(patientRecord);
        return patientRecord;
    }
}
