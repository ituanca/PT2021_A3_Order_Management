package businessLayer.validators;

import model.Student;

public class StudentAgeValidator implements Validator<Student>{
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 30;

    public boolean validate(Student t) {
        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            return false;
            //throw new IllegalArgumentException("The Student Age limit is not respected!");
        }
        return true;
    }
}
