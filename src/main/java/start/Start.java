package start;

import businessLayer.ProductBLL;
import businessLayer.StudentBLL;
import model.Product;
import model.Student;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {
        Student student = new Student("Liam Neeson", "str.Wellington,nr.23", "liamneeson@gmail.com", 18);
        Product product = new Product("Biscuits", 10.56, 50);

        StudentBLL studentBll = new StudentBLL();
        ProductBLL productBLL = new ProductBLL();

        int idStudent = studentBll.insertStudent(student);     // vrem sa inseram un student in tabel => apelam StudentBLL
        if (idStudent > 0) {
            studentBll.findStudentById(idStudent);
        }
        try{
            studentBll.findStudentById(1);
        }catch(Exception ex){
            LOGGER.log(Level.INFO, ex.getMessage());
        }

        int idProduct = productBLL.insertProduct(product);
        try {
            if (idProduct > 0) {
                productBLL.findProductById(idProduct);
            }
        }catch(Exception e){
            LOGGER.log(Level.INFO, e.getMessage());
        }

        //obtain field-value pairs for object through reflection
        ReflectionExample.retrieveProperties(student);
        ReflectionExample.retrieveProperties(product);
    }

}
