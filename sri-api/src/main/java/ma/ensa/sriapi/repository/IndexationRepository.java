package ma.ensa.sriapi.repository;

import ma.ensa.sriapi.model.Indexation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexationRepository extends MongoRepository<Indexation, String> {
    Indexation findByTerm(String term);
    boolean existsById(String id);
}
