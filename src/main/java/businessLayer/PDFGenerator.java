package businessLayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.Customer;
import model.Order;
import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PDFGenerator {
    Document document = new Document();

    public void generatePDF(Order order, Customer customer, Product product){
        try {
            String FILE = "src\\main\\java\\pdf\\orderDetails.pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            document.add(new Paragraph("Order: " + order.getId()));
            document.add(new Paragraph("Customer details: "));
            document.add(new Paragraph("    Id: " + customer.getId()));
            document.add(new Paragraph("    Name: " + customer.getName()));
            document.add(new Paragraph("    Address: " + customer.getAddress()));
            document.add(new Paragraph("    City: " + customer.getCity()));
            document.add(new Paragraph("    Phone: " + customer.getPhone()));
            document.add(new Paragraph("    Email: " + customer.getEmail()));
            document.add(new Paragraph("Product details: "));
            document.add(new Paragraph("    Id: " + product.getId()));
            document.add(new Paragraph("    Name: " + product.getName()));
            document.add(new Paragraph("    Price per unit: " + product.getUnitPrice()));
            document.add(new Paragraph("Selected quantity: " + order.getQuantity()));
            document.add(new Paragraph("Total price: " + order.getPrice()));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }





}
