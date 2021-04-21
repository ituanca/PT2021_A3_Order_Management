package businessLayer;

import businessLayer.validators.EmailValidator;
import businessLayer.validators.StudentAgeValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.StudentDAO;
import model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentBLL {

    private List<Validator<Student>> validators;
    private StudentDAO studentDAO;

    public StudentBLL() {
        validators = new ArrayList<Validator<Student>>();
        validators.add(new EmailValidator());
        validators.add(new StudentAgeValidator());

        studentDAO = new StudentDAO();
    }

    public Student findStudentById(int id) throws SQLException {
        Student student = studentDAO.findById(id);
        if (student == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return student;
    }

    public int insertStudent(Student student) throws SQLException {
        for (Validator<Student> v : validators) {
            v.validate(student);
        }
        return StudentDAO.insert(student);
    }

}
