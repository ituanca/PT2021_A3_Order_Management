package businessLayer.validators;

/**
 * Interface used for data validation
 * @author Anca
 */
public interface Validator<T>{
    /**
     * Method to validate input
     * @param t object transmitted as parameter
     * @return boolean
     */
    public boolean validate(T t);
}
