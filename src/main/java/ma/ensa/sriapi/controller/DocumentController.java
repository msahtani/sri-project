package ma.ensa.sriapi.controller;

import jakarta.servlet.http.HttpServletResponse;
import ma.ensa.sriapi.model.Indexation;
import ma.ensa.sriapi.model.PDFDocument;
import ma.ensa.sriapi.service.DocumentServiceImp;
import ma.ensa.sriapi.service.IndexationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentServiceImp documentServiceImp ;
    private final IndexationServiceImp indexationServiceImp;

    public DocumentController(DocumentServiceImp documentServiceImp, IndexationServiceImp indexationServiceImp) {
        this.documentServiceImp = documentServiceImp;
        this.indexationServiceImp = indexationServiceImp;
    }

    @GetMapping
    public List<PDFDocument> getDocuments(){
        return documentServiceImp.getDocuments() ;
    }

    @GetMapping("/allCondition")
    public Set<PDFDocument> getDocsByElements(@RequestParam String level , @RequestParam String branch , @RequestParam String semestre , @RequestParam String request) throws IOException {


        return indexationServiceImp.searchDocsByElements(level , branch , semestre , request);
    }
    @GetMapping("/all")
    public Set<PDFDocument> getDocsByTerms(  @RequestParam String request) throws IOException {


        return indexationServiceImp.searchDocsByTerms(request);
    }
    @GetMapping("/checkDocument")
    public boolean checkDocExistence(@RequestPart MultipartFile file){
        return documentServiceImp.documentAlreadyExists(file.getOriginalFilename());
    }
    @GetMapping("/pdf/{filename}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable String filename) throws IOException {
        File file = new File("C:/Users/saadf/Downloads/sri-prj/sri-project/sri-ui/public" + filename);

        // Vérifier si le fichier existe
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Lire le contenu du fichier
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Configurer le type de contenu de la réponse
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);

        // Retourner le contenu du fichier dans la réponse
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

    @GetMapping("/terms")
    public List<Indexation> getAllTerms(){
        return indexationServiceImp.getAllIndex() ;
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadPDFDocument(@RequestPart MultipartFile file, @RequestPart PDFDocument pdfDocument) throws IOException {
        Thread currentThread = Thread.currentThread();
        System.out.println("controller thread : " + currentThread.getName());

        String filePath = documentServiceImp.uploadDoc(file) ;
        indexationServiceImp.idexation(file);

        System.out.println("controller2 thread : " + currentThread.getName());

        pdfDocument.setName(file.getOriginalFilename());
        pdfDocument.setContent(file.getBytes());
        pdfDocument.setPath(filePath);
        documentServiceImp.saveDocument(pdfDocument);

        return ResponseEntity.ok("OK");
    }


}
