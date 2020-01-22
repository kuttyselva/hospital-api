package global.coda.hms;

import global.coda.hms.model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Employee repo.
 */
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {


}
