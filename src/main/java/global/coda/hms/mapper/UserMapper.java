package global.coda.hms.mapper;

import global.coda.hms.model.PatientRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into t_user (user_name,user_password,user_location,user_age,user_phone,fk_role_id) values (#{name},#{password},#{location},#{age},#{phone},#{roleId})")
    PatientRecord insert(String name, String password, String location, int age, String phone, int roleId);

    @Select("select t_user.user_name as name ,  t_user.user_location as location, t_user.user_phone as phone, t_user.user_age as age ,t_patient.patient_disease as disease from t_user join t_patient on t_user.pk_user_id=t_patient.fk_user_id where t_user.pk_user_id=3")
    PatientRecord getPatient();

    @Select("select user_name as name , user_password as password from t_user where user_name = #{username} and is_active=1")
    PatientRecord getCredential(String username);
}
