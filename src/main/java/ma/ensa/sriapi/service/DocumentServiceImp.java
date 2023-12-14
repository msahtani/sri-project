package ma.ensa.sriapi.service;

import ma.ensa.sriapi.model.PDFDocument;
import ma.ensa.sriapi.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
public class DocumentServiceImp implements DocumentService{
    @Autowired
    private DocumentRepository documentRepository ;

    @Override
    public List<PDFDocument> getDocumentsByElements(String level, String branch, String semestre, String module) {
        return documentRepository.findByLevelAndBranchAndSemestreAndModule(level , branch , semestre , module);
    }

    @Override
    public List<PDFDocument> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public PDFDocument getDocumentByTerm(String term) {
        return null;
    }

    @Override
    public PDFDocument saveDocument(PDFDocument pdfDocument) {

        return documentRepository.save(pdfDocument);
    }

    @Override
    public PDFDocument getDocByName(String name) {
        return documentRepository.findByName(name);
    }

    @Override
    public boolean documentAlreadyExists(String filename) {
        PDFDocument doc = documentRepository.findByName(filename) ;
        return doc!=null;
    }

    @Override
    public String uploadDoc(MultipartFile file) throws IOException {
        String uploadDirectory = "C:/Users/saadf/Documents/sri/src/main/resources/docs";
        String filePath = Paths.get(uploadDirectory, file.getOriginalFilename()).toString();
        Files.write(Paths.get(filePath), file.getBytes());
        return filePath ;

    }
}
