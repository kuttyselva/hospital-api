package global.coda.hms.model;

import javax.persistence.*;

/**
 * The type Employee.
 */
@Entity
@Table(name = "t_employee")
public class Employee {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    /**
     * The First name.
     */
    @Column(name = "first_name")
    String firstName;
    /**
     * The Last name.
     */
    @Column(name = "last_name")
    String lastName;

    /**
     * Instantiates a new Employee.
     */
    public Employee() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
