package ma.ensa.sriapi.repository;
import ma.ensa.sriapi.model.PDFDocument;
import ma.ensa.sriapi.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsRepository extends MongoRepository<Student, String > {
    Student findByEmail(String email);

}
