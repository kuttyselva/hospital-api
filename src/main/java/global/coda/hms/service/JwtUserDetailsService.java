package global.coda.hms.service;

import java.util.ArrayList;

import global.coda.hms.controller.PatientController;
import global.coda.hms.mapper.PatientMapper;
import global.coda.hms.mapper.UserMapper;
import global.coda.hms.model.PatientRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The type Jwt user details service.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    /**
     * The User mapper.
     */
    @Autowired
    UserMapper userMapper;
    private static final Logger LOGGER = LogManager.getLogger(JwtUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PatientRecord patientRecord = userMapper.getCredential(username);
        LOGGER.info(patientRecord);
        if (patientRecord.getPassword() != null) {
            return new User(patientRecord.getName(), patientRecord.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}