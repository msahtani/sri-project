package ma.ensa.sriapi.repository;

import ma.ensa.sriapi.model.PDFDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends MongoRepository<PDFDocument, String > {
    PDFDocument findByName(String name ) ;
    List<PDFDocument> findByLevelAndBranchAndSemestreAndModule(String level , String branch , String semestre , String module) ;
}
