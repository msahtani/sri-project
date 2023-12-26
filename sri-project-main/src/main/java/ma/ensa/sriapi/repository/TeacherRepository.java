package ma.ensa.sriapi.repository;
import ma.ensa.sriapi.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, String > {
 Teacher findByEmail(String email);

}
