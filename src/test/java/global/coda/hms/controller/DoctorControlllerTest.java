package global.coda.hms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.*;
@SpringBootTest
@AutoConfigureMockMvc
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    public DoctorController doctorController;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @Test
    public void viewDoctorRecordTest() throws Exception {
        this.mockMvc.perform(
                get("/doctors/all"))
                .andExpect(status().isOk());
    }
//    @Test
//    public void deleteDoctorRecordTest() throws Exception {
//        this.mockMvc.perform(
//                put("/doctors/delete/15"))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void createDoctorRecordTest() throws Exception {
//        DoctorRecord doctor=new DoctorRecord();
//        doctor.setName("selva");
//        doctor.setPassword("1234");
//        doctor.setSpeciality("dentist");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson=ow.writeValueAsString(doctor );
//        mockMvc.perform(post("/doctors/create").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void updateDoctorRecordTest() throws Exception {
//        DoctorRecord doctor=new DoctorRecord();
//        doctor.setName("kutty");
//        doctor.setPassword("1234");
//        doctor.setSpeciality("dentist");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson=ow.writeValueAsString(doctor );
//        mockMvc.perform(put("/doctors/update").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(status().isOk());
//    }
}
