package global.coda.hms.controller;

import global.coda.hms.constants.ResponseStatus;
import global.coda.hms.model.CustomResponse;
import global.coda.hms.model.DoctorRecord;
import global.coda.hms.service.DoctorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * The type Doctor controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    /**
     * The Doctor service.
     */
    @Autowired
    DoctorService doctorService;
    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);

    /**
     * Gets patients.
     *
     * @return the patients
     */
    @GetMapping("/all")
    public CustomResponse<List<DoctorRecord>> getPatients() {
        CustomResponse<List<DoctorRecord>> getPatientsResponse = new CustomResponse<>();
        LOGGER.traceEntry();
        List<DoctorRecord> doctorRecordList = doctorService.getDoctors();
        getPatientsResponse.setStatus(ResponseStatus.OK);
        getPatientsResponse.setSuccess(true);
        getPatientsResponse.setObject(doctorRecordList);
        LOGGER.traceExit(getPatientsResponse);
        return getPatientsResponse;
    }

    /**
     * Create patient int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    @PostMapping("/create")
    public CustomResponse<Integer> createPatient(@RequestBody DoctorRecord doctorRecord) {
        CustomResponse<Integer> createResponse = new CustomResponse<>();
        LOGGER.trace(doctorRecord);
        int id = doctorService.createDoctor(doctorRecord);
        createResponse.setStatus(ResponseStatus.OK);
        createResponse.setSuccess(true);
        createResponse.setObject(id);
        LOGGER.traceExit(createResponse);
        return createResponse;
    }

    /**
     * Gets doctor.
     *
     * @param id the id
     * @return the doctor
     */
    @GetMapping("/doctor/{id}")
    public CustomResponse<DoctorRecord> getDoctor(@PathVariable("id") int id) {
        CustomResponse<DoctorRecord> getDoctorResponse = new CustomResponse<>();
        LOGGER.trace(id);
        DoctorRecord doctorRecord = doctorService.getDoctor(id);
        getDoctorResponse.setStatus(ResponseStatus.OK);
        getDoctorResponse.setSuccess(true);
        getDoctorResponse.setObject(doctorRecord);
        LOGGER.traceExit(getDoctorResponse);
        return getDoctorResponse;
    }

    /**
     * Delete patient.
     *
     * @param id the id
     * @return the custom response
     */
    @GetMapping("/doctor/delete/{id}")
    public CustomResponse<String> deletePatient(@PathVariable("id") int id) {
        CustomResponse<String> deleteResponse = new CustomResponse<>();
        LOGGER.trace(id);
        doctorService.deleteDoctor(id);
        deleteResponse.setStatus(ResponseStatus.SUCCESS_NO_CONTENT);
        deleteResponse.setSuccess(true);
        deleteResponse.setObject(ResponseStatus.DELETE_DOC);
        LOGGER.traceExit();
        return deleteResponse;
    }

    /**
     * Patient update int.
     *
     * @param doctorRecord the doctor record
     * @return the int
     */
    @PostMapping("/doctor/update")
    public CustomResponse<Integer> patientUpdate(@RequestBody DoctorRecord doctorRecord) {
        CustomResponse<Integer> updateResponse = new CustomResponse<>();
        LOGGER.trace(doctorRecord);
        int id = doctorService.updateDoctor(doctorRecord);
        updateResponse.setStatus(ResponseStatus.OK);
        updateResponse.setSuccess(true);
        updateResponse.setObject(id);
        return updateResponse;
    }


    /**
     * Gets all doctors.
     *
     * @param doctorID the doctor id
     * @return the all doctors
     */
    @GetMapping("/patients")
    public CustomResponse<List<DoctorRecord>> getAllDoctors(@PathParam("doctorID") Integer doctorID) {
        CustomResponse<List<DoctorRecord>> docResponse = new CustomResponse<>();
        LOGGER.trace(doctorID);
        List<DoctorRecord> doctorRecordList;
        if (doctorID == null) {
            doctorID = 0;
        }
        doctorRecordList = doctorService.getDoctorPatients(doctorID);
        docResponse.setObject(doctorRecordList);
        docResponse.setSuccess(true);
        docResponse.setStatus(ResponseStatus.OK);
        LOGGER.traceExit(docResponse);
        return docResponse;
    }


    /**
     * Gets all doctors.
     *
     * @param doctorID the doctor id
     * @return the all doctors
     */
//    @GetMapping("/doctor/{id}/getPatients")
//    public List<DoctorRecord> getAllDoctors(@PathVariable("id") int doctorID) {
//        CustomResponse<List<DoctorRecord>> docResponse = new CustomResponse<>();
//        LOGGER.trace(doctorID);
//        List<DoctorRecord> doctorRecordList = doctorService.getDoctorPatients(doctorID);
//        docResponse.setObject(doctorRecordList);
//        docResponse.setSuccess(true);
//        docResponse.setStatus(ResponseStatus.OK);
//        LOGGER.traceExit(docResponse);
//        return doctorRecordList;
//    }

    /**
     * Patient doctor map int.
     *
     * @param patientID the patient id
     * @param doctorID  the doctor id
     * @return the int
     */
    @PostMapping("/patientEntry/{doctorID}/{patientID}")
    public CustomResponse<Integer> patientDoctorMap(@PathVariable("patientID") int patientID, @PathVariable("doctorID") int doctorID) {
        CustomResponse<Integer> entryResponse = new CustomResponse<>();
        LOGGER.trace(patientID + " " + doctorID);
        int id = doctorService.patientDoctorMap(patientID, doctorID);
        entryResponse.setStatus(ResponseStatus.OK);
        entryResponse.setSuccess(true);
        entryResponse.setObject(id);
        LOGGER.traceExit(entryResponse);
        return entryResponse;
    }


}
