package global.coda.hms.service;

import global.coda.hms.mapper.DoctorMapper;
import global.coda.hms.mapper.PatientMapper;
import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
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


    /**
     * Gets doctors.
     *
     * @return the doctors
     */
    public List<DoctorRecord> getDoctors() {
        return doctorMapper.getDoctors();
    }

    /**
     * Create doctor int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    public int createDoctor(DoctorRecord doctorRecord) {
        doctorMapper.createUser(doctorRecord);
        doctorMapper.createDoctor(doctorRecord);
        return doctorRecord.getId();
    }

    /**
     * Gets doctor.
     *
     * @param id the id
     * @return the doctor
     */
    public DoctorRecord getDoctor(int id) {
        return doctorMapper.getDoctor(id);
    }

    /**
     * Delete doctor.
     *
     * @param id the id
     */
    public void deleteDoctor(int id) {
        doctorMapper.deleteDoctor(id);
    }

    /**
     * Update doctor int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    public int updateDoctor(DoctorRecord doctorRecord) {
        doctorMapper.updateUser(doctorRecord);
        doctorMapper.updateDoctor(doctorRecord);
        return doctorRecord.getId();
    }

    /**
     * Gets doctor patients.
     *
     * @return the doctor patients
     */
    public List<DoctorRecord> getDoctorPatients() {
        List<DoctorRecord> doctorRecords = doctorMapper.getDoctors();
        for (DoctorRecord doctorRecord : doctorRecords) {
            doctorRecord.setPatientList(doctorMapper.getAllPatients(doctorRecord.getId()));
        }
        return doctorRecords;
    }


}
