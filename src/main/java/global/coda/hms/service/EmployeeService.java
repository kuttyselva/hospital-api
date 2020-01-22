package global.coda.hms.service;

import global.coda.hms.EmployeeRepo;
import global.coda.hms.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Employee service.
 */
@Service
public class EmployeeService {
    /**
     * The Employee repo.
     */
    @Autowired
    EmployeeRepo employeeRepo;

    /**
     * Create employee employee.
     *
     * @param employee the employee
     * @return the employee
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
}
