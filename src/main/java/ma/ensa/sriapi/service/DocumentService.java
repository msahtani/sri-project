package ma.ensa.sriapi.service;

import ma.ensa.sriapi.model.PDFDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    public List<PDFDocument> getDocumentsByElements(String level , String branch , String semestre , String module) ;
    public List<PDFDocument> getDocuments() ;
    public PDFDocument getDocumentByTerm(String term) ;
    public PDFDocument saveDocument(PDFDocument pdfDocument) ;
    PDFDocument getDocByName(String name ) ;

    boolean documentAlreadyExists(String filename) ;
    String uploadDoc(MultipartFile file) throws IOException;
}
