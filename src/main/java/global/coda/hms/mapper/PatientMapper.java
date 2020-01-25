package global.coda.hms.mapper;

import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * The interface Patient mapper.
 */
@Mapper
public interface PatientMapper {
    /**
     * Gets patient.
     *
     * @return the patient
     */
    @Select("select t_user.pk_user_id as id, t_user.user_name as name ,  t_user.user_location as location, t_user.user_phone as phone, t_user.user_age as age ,t_patient.patient_disease as disease from t_user join t_patient on t_user.pk_user_id=t_patient.fk_user_id WHERE t_user.is_active = 1")
    List<PatientRecord> getPatients();

    /**
     * Create user.
     *
     * @param patientRecord the patient record
     */
    @Insert("insert into t_user (user_name,user_password,user_location,user_age,user_phone,fk_role_id) values (#{name},#{password},#{location},#{age},#{phone},1)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "pk_user_id")
    void createUser(PatientRecord patientRecord);

    /**
     * Create patient.
     *
     * @param patientRecord the patient record
     */
    @Insert("insert into t_patient (patient_disease,fk_user_id) values (#{disease},#{id})")
    void createPatient(PatientRecord patientRecord);

    /**
     * Gets patient.
     *
     * @param id the id
     * @return the patient
     */
    @Select("select t_user.pk_user_id as id, t_user.user_name as name ,  t_user.user_location as location, t_user.user_phone as phone, t_user.user_age as age ,t_patient.patient_disease as disease from t_user join t_patient on t_user.pk_user_id=t_patient.fk_user_id WHERE t_user.pk_user_id = #{id} AND t_user.is_active = 1")
    PatientRecord getPatient(int id);

    /**
     * Delete patient.
     *
     * @param id the id
     */
    @Insert("update t_user set is_active=0 where pk_user_id = #{id} AND fk_role_id = 1")
    void deletePatient(int id);


    /**
     * Update user.
     *
     * @param patientRecord the patient record
     */
    @Update("update t_user set user_location=#{location} , user_age= #{age} , user_phone= #{phone}  where pk_user_id=#{id} and is_active='1' and fk_role_id = '1'")
    void updateUser(PatientRecord patientRecord);

    /**
     * Create patient.
     *
     * @param patientRecord the patient record
     */
    @Update("update t_patient set patient_disease= #{disease} WHERE fk_user_id = #{id}")
    void updatePatient(PatientRecord patientRecord);

    /**
     * Gets all doctors.
     *
     * @param id the id
     * @return the all doctors
     */
    @Select("select user_name as name, doctor_speciality as speciality, user_location as location, user_phone as phone from t_user RIGHT JOIN t_doctor on t_doctor.fk_user_id = t_user.pk_user_id where pk_user_id in (select fk_user_id from t_doctor where pk_doctor_id in (select fk_doctor_id from t_patient_doctor_map where fk_patient_id in (select pk_patient_id from t_patient where fk_user_id = (select pk_user_id from t_user where pk_user_id = #{id} and is_active='1'))))")
    List<DoctorRecord> getAllDoctors(int id);

}

