package global.coda.hms.service;

import global.coda.hms.mapper.DoctorMapper;
import global.coda.hms.model.DoctorRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Doctor service.
 */
@Service
public class DoctorService {
    /**
     * The Doctor mapper.
     */
    @Autowired
    DoctorMapper doctorMapper;

    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);


    /**
     * Gets doctors.
     *
     * @return the doctors
     */
    public List<DoctorRecord> getDoctors() {
        LOGGER.traceEntry();
        List<DoctorRecord> doctorRecordList = doctorMapper.getDoctors();
        LOGGER.traceExit(doctorRecordList);
        return doctorRecordList;
    }

    /**
     * Create doctor int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    public int createDoctor(DoctorRecord doctorRecord) {
        LOGGER.trace(doctorRecord);
        doctorMapper.createUser(doctorRecord);
        doctorMapper.createDoctor(doctorRecord);
        int id = doctorRecord.getId();
        LOGGER.traceExit(id);
        return id;
    }

    /**
     * Gets doctor.
     *
     * @param id the id
     * @return the doctor
     */
    public DoctorRecord getDoctor(int id) {
        LOGGER.trace(id);
        DoctorRecord doctorRecord = doctorMapper.getDoctor(id);
        LOGGER.traceExit(doctorRecord);
        return doctorRecord;
    }

    /**
     * Delete doctor.
     *
     * @param id the id
     */
    public void deleteDoctor(int id) {
        LOGGER.trace(id);
        doctorMapper.deleteDoctor(id);
        LOGGER.traceExit();
    }

    /**
     * Update doctor int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    public int updateDoctor(DoctorRecord doctorRecord) {
        LOGGER.trace(doctorRecord);
        doctorMapper.updateUser(doctorRecord);
        doctorMapper.updateDoctor(doctorRecord);
        int id = doctorRecord.getId();
        LOGGER.traceExit(id);
        return id;
    }


    /**
     * Gets doctor patients.
     *
     * @param doctorID the doctor id
     * @return the doctor patients
     */
    public List<DoctorRecord> getDoctorPatients(int doctorID) {
        LOGGER.trace(doctorID);
        List<DoctorRecord> doctorRecordList = doctorMapper.getAllPatientUnderAllDoctors(doctorID);
        LOGGER.traceExit(doctorRecordList);
        return doctorRecordList;
    }

    /**
     * Patient doctor map int.
     *
     * @param patientID the patient id
     * @param doctorID  the doctor id
     * @return the int
     */
    public int patientDoctorMap(int patientID, int doctorID) {
        LOGGER.trace(patientID + " " + doctorID);
        patientID = doctorMapper.getPatientId(patientID);
        doctorID = doctorMapper.getPDoctorId(doctorID);
        int mapID = doctorMapper.patientDoctorMap(patientID, doctorID);
        LOGGER.traceExit(mapID);
        return mapID;
    }

//    /**
//     * Gets all patients.
//     *
//     * @param doctorID the doctor id
//     * @return the all patients
//     */
//    public List<PatientRecord> getAllPatients(int doctorID) {
//        return doctorMapper.getPatientUnderDoctor(doctorID);
//    }


}
