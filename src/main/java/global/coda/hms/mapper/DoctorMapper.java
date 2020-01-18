package global.coda.hms.mapper;

import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * The interface Doctor mapper.
 */
@Mapper
public interface DoctorMapper {
    /**
     * Gets doctors.
     *
     * @return the doctors
     */
    @Select("select t_user.pk_user_id as id, t_user.user_name as name ,  t_user.user_location as location, t_user.user_phone as phone, t_user.user_age as age ,t_doctor.doctor_speciality as speciality from t_user join t_doctor on t_user.pk_user_id=t_doctor.fk_user_id WHERE t_user.is_active = 1")
    List<DoctorRecord> getDoctors();

    /**
     * Create user.
     *
     * @param doctorRecord the doctor record
     */
    @Insert("insert into t_user (user_name,user_password,user_location,user_age,user_phone,fk_role_id) values (#{name},#{password},#{location},#{age},#{phone},2)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "pk_user_id")
    void createUser(DoctorRecord doctorRecord);

    /**
     * Create doctor.
     *
     * @param doctorRecord the doctor record
     */
    @Insert("insert into t_doctor (doctor_speciality,fk_user_id) values (#{speciality},#{id})")
    void createDoctor(DoctorRecord doctorRecord);

    /**
     * Gets doctor.
     *
     * @param id the id
     * @return the doctor
     */
    @Select("select t_user.pk_user_id as id, t_user.user_name as name ,  t_user.user_location as location, t_user.user_phone as phone, t_user.user_age as age ,t_doctor.doctor_speciality as speciality from t_user join t_doctor on t_user.pk_user_id=t_doctor.fk_user_id WHERE t_user.pk_user_id = #{id} AND t_user.is_active = 1")
    DoctorRecord getDoctor(int id);

    /**
     * Delete doctor.
     *
     * @param id the id
     */
    @Insert("update t_user set is_active=0 where pk_user_id = #{id} AND fk_role_id = 2")
    void deleteDoctor(int id);

    /**
     * Update user.
     *
     * @param doctorRecord the doctor record
     */
    @Update("update t_user set user_location=#{location} , user_age= #{age} , user_phone= #{phone}  where pk_user_id=#{id} and is_active='1' and fk_role_id = '2'")
    void updateUser(DoctorRecord doctorRecord);

    /**
     * Update doctor.
     *
     * @param doctorRecord the doctor record
     */
    @Update("update t_doctor set doctor_speciality= #{speciality} WHERE fk_user_id = #{id}")
    void updateDoctor(DoctorRecord doctorRecord);

    /**
     * Gets all patients.
     *
     * @param id the id
     * @return the all patients
     */
    @Select("select pk_user_id as id, user_name as name, patient_disease as disease, user_location as location, user_phone as phone from t_user RIGHT JOIN t_patient on t_patient.fk_user_id = t_user.pk_user_id where pk_user_id in (select fk_user_id from t_patient where pk_patient_id in (select fk_patient_id from t_patient_doctor_map where fk_doctor_id in (select pk_doctor_id from t_doctor where fk_user_id = (select pk_user_id from t_user where pk_user_id = #{id} and is_active='1'))))")
    List<PatientRecord> getAllPatients(int id);


}
