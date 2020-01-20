package global.coda.hms.mapper;

import global.coda.hms.model.DoctorRecord;
import global.coda.hms.model.PatientRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
     * Gets patient id.
     *
     * @param id the id
     * @return the patient id
     */
    @Select("select pk_patient_id from t_patient WHERE fk_user_id = #{id}")
    int getPatientId(int id);

    /**
     * Gets p doctor id.
     *
     * @param id the id
     * @return the p doctor id
     */
    @Select("select pk_doctor_id from t_doctor WHERE fk_user_id = #{id}")
    int getPDoctorId(int id);

    /**
     * Patient doctor map int.
     *
     * @param patientID the patient id
     * @param doctorID  the doctor id
     * @return the int
     */
    @Insert("insert into t_patient_doctor_map (fk_patient_id , fk_doctor_id) VALUES (#{patientID} , #{doctorID})")
    int patientDoctorMap(int patientID, int doctorID);


    /**
     * Gets patient under doctor.
     *
     * @param id the id
     * @return the patient under doctor
     */
    @Select("select user_name as name, patient_disease as disease, user_location as location, user_phone as phone from t_user RIGHT JOIN t_patient on t_patient.fk_user_id = t_user.pk_user_id where pk_user_id in (select fk_user_id from t_patient where pk_patient_id in (select fk_patient_id from t_patient_doctor_map where fk_doctor_id in (select pk_doctor_id from t_doctor where fk_user_id = (select pk_user_id from t_user where pk_user_id = #{id} and is_active='1'))))")
    List<PatientRecord> getPatientUnderDoctor(int id);


    /**
     * Gets all patient under all doctors.
     *
     * @return the all patient under all doctors
     */
    @Select("<script> select pk_user_id,user_name,doctor_speciality,user_age,user_location,user_phone from t_user join t_doctor on t_user.pk_user_id = t_doctor.fk_user_id  where t_user.is_active=1 and t_doctor.is_active=1 <if test='id!=0'> and pk_user_id= #{id} </if> </script>")
    @Results(value = {
            @Result(property = "id", column = "pk_user_id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "age", column = "user_age"),
            @Result(property = "location", column = "user_location"),
            @Result(property = "speciality", column = "doctor_speciality"),
            @Result(property = "phone", column = "user_phone"),
            @Result(property = "patientList", javaType = List.class, column = "pk_user_id",
                    many = @Many(select = "getPatientUnderDoctor"))
    })
    List<DoctorRecord> getAllPatientUnderAllDoctors(int id);


}
