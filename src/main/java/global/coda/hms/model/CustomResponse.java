package global.coda.hms.model;


/**
 * The type Custom response.
 *
 * @param <T> the type parameter
 */
public class CustomResponse<T> {
    Integer requestID;

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    /**
     * Gets success.
     *
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets object.
     *
     * @return the object
     */
    public T getObject() {
        return object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(T object) {
        this.object = object;
    }

    /**
     * The Success.
     */
    Boolean success;
    /**
     * The Status.
     */
    String status;
    /**
     * The Object.
     */
    T object;


}
