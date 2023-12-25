package ma.ensa.sriapi.service;

import ma.ensa.sriapi.model.Indexation;
import ma.ensa.sriapi.model.PDFDocument;
import org.apache.tika.metadata.PDF;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
@Service
public interface IndexationService {
    public Indexation saveIndex(Indexation indexation);
   Indexation findIndexationByTerm(String term ) ;
   boolean existsById(String id ) ;
   void updateIndexation(String id , Indexation indexation) ;

   CompletableFuture<String> idexation(MultipartFile file) throws IOException;

   public List<Indexation> getAllIndex() ;

   public Set<PDFDocument> searchDocsByElements(String level ,  String branch ,  String semestre ,  String module , String request) throws IOException;
   public Set<PDFDocument> searchDocsByTerms(String request) throws IOException;
}
