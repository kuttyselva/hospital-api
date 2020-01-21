package global.coda.hms.controller;

import global.coda.hms.constants.ResponseStatus;
import global.coda.hms.model.CustomResponse;
import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
import global.coda.hms.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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

    private static final Logger LOGGER = LogManager.getLogger(PatientController.class);


    /**
     * Gets patient.
     *
     * @return the patient
     */
    @GetMapping("/all")
    public CustomResponse<List<PatientRecord>> getPatients(HttpServletRequest httpServletRequest) {
        LOGGER.traceEntry();
        CustomResponse<List<PatientRecord>> patientsResponse = new CustomResponse<>();
        List<PatientRecord> patientRecords = patientService.getPatients();
        patientsResponse.setStatus(ResponseStatus.OK);
        patientsResponse.setSuccess(true);
        patientsResponse.setRequestID((Integer) httpServletRequest.getAttribute("requestID"));
        patientsResponse.setObject(patientRecords);
        LOGGER.traceExit(patientsResponse);
        return patientsResponse;

    }

    /**
     * Create patient int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    @PostMapping("/create")
    public CustomResponse<Integer> createPatient(@RequestBody PatientRecord patientRecord) {
        LOGGER.trace(patientRecord);
        CustomResponse<Integer> createResponse = new CustomResponse<>();
        int id = patientService.createPatient(patientRecord);
        createResponse.setStatus(ResponseStatus.OK);
        createResponse.setSuccess(true);
        createResponse.setObject(id);
        LOGGER.traceExit(createResponse);
        return createResponse;
    }


    /**
     * Gets patient.
     *
     * @param id the id
     * @return the patient
     */
    @GetMapping("/patient/{id}")
    public CustomResponse<PatientRecord> getPatient(@PathVariable("id") int id) {
        LOGGER.trace(id);
        CustomResponse<PatientRecord> patientRecordCustomResponse = new CustomResponse<>();
        PatientRecord patientRecord = patientService.getPatient(id);
        patientRecordCustomResponse.setObject(patientRecord);
        patientRecordCustomResponse.setStatus(ResponseStatus.OK);
        patientRecordCustomResponse.setSuccess(true);
        LOGGER.traceExit(patientRecordCustomResponse);
        return patientRecordCustomResponse;
    }

    /**
     * Delete patient.
     *
     * @param id the id
     * @return the custom response
     */
    @GetMapping("/patient/delete/{id}")
    public CustomResponse<Integer> deletePatient(@PathVariable("id") int id) {
        LOGGER.trace(id);
        CustomResponse<Integer> customResponse = new CustomResponse<>();
        customResponse.setSuccess(true);
        customResponse.setStatus(ResponseStatus.SUCCESS_NO_CONTENT);
        customResponse.setObject(patientService.deletePatient(id));
        LOGGER.traceExit(customResponse);
        return customResponse;
    }

    /**
     * Patient update int.
     *
     * @param patientRecord the patient record
     * @return the int
     */
    @PostMapping("/patient/update")
    public CustomResponse<Integer> patientUpdate(@RequestBody PatientRecord patientRecord) {
        LOGGER.trace(patientRecord);
        CustomResponse<Integer> updateResponse = new CustomResponse<>();
        updateResponse.setStatus(ResponseStatus.OK);
        updateResponse.setSuccess(true);
        updateResponse.setObject(patientService.updatePatient(patientRecord));
        LOGGER.traceExit(updateResponse);
        return updateResponse;
    }

    /**
     * Gets all doctors.
     *
     * @param patientId the patient id
     * @return the all doctors
     */
    @GetMapping("/patient/{id}/getDoctors")
    public CustomResponse<List<DoctorRecord>> getAllDoctors(@PathVariable("id") int patientId) {
        LOGGER.trace(patientId);
        CustomResponse<List<DoctorRecord>> doctorsResponse = new CustomResponse<>();
        doctorsResponse.setStatus(ResponseStatus.OK);
        doctorsResponse.setSuccess(true);
        doctorsResponse.setObject(patientService.getAllDoctors(patientId));
        LOGGER.traceExit(doctorsResponse);
        return doctorsResponse;
    }
}
