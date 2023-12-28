package ma.ensa.sriapi.service;

import ma.ensa.sriapi.indexation.IndexationProcess;
import ma.ensa.sriapi.model.Indexation;
import ma.ensa.sriapi.model.PDFDocument;
import ma.ensa.sriapi.repository.IndexationRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.scheduling.annotation.Async;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;


@EnableAsync
@Service
public class IndexationServiceImp implements IndexationService{
    private final IndexationRepository indexationRepository ;
    private IndexationProcess process ;
    private DocumentServiceImp documentServiceImp ;

    public IndexationServiceImp(IndexationRepository indexationRepository, IndexationProcess process) {
        this.indexationRepository = indexationRepository;
        this.process = process;
    }

    @Autowired
    public void setDocumentServiceImp(DocumentServiceImp documentServiceImp) {
        this.documentServiceImp = documentServiceImp;
    }





    @Override
    public Indexation saveIndex(Indexation indexation) {
        return indexationRepository.save(indexation);
    }

    @Override
    public Indexation findIndexationByTerm(String term) {
        return indexationRepository.findByTerm(term);
    }

    @Override
    public boolean existsById(String id) {
        return indexationRepository.existsById(id);
    }
    @Override
    public void updateIndexation(String id, Indexation updatedIndex) {
        Optional<Indexation> existingIndexation = indexationRepository.findById(id);

        if (existingIndexation.isPresent()) {
            Indexation indexation = existingIndexation.get();
            indexation.setTerm(updatedIndex.getTerm());
            indexation.setDocuments(updatedIndex.getDocuments());
            indexationRepository.save(indexation);
        }
    }
    @Async
    public CompletableFuture<String> idexation(MultipartFile file) throws IOException {

        PDDocument document = PDDocument.load(file.getInputStream());

        List<String> documents = new ArrayList<>();
        documents.add(file.getOriginalFilename());

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        List<String> content = process.processText(text) ;
        Thread currentThread = Thread.currentThread();
        System.out.println("indexation thread : " + currentThread.getName());

        for (String term : content) {

            Indexation alreadyExists = findIndexationByTerm(term);

            if (alreadyExists != null && !alreadyExists.getTerm().isEmpty()) {

                List<String> docs = alreadyExists.getDocuments();
                if (!docs.contains(file.getOriginalFilename())) {
                    docs.add(file.getOriginalFilename());
                    alreadyExists.setDocuments(docs);
                    this.updateIndexation(alreadyExists.getId(), alreadyExists);
                }

            } else {
                Indexation newIndex = new Indexation();
                newIndex.setTerm(term);
                newIndex.setDocuments(documents);
                this.saveIndex(newIndex);
            }
        }

        document.close();
        return CompletableFuture.completedFuture("filePath") ;
    }

    @Override
    public List<Indexation> getAllIndex() {
        return indexationRepository.findAll();
    }

    @Override
    public Set<PDFDocument> searchDocsByElements(String level, String branch, String semestre, String request) throws IOException {
        List<String> terms = process.processText(request);
        Map<PDFDocument, Integer> documentOccurrences = new HashMap<>();

        for (int i = 0; i < terms.size(); i++) {
            String term = terms.get(i);
            Indexation index = findIndexationByTerm(term);
            if (index == null) {
                continue;
            }
            for (int j = 0; j < index.getDocuments().size(); j++) {
                String documentName = index.getDocuments().get(j);
                PDFDocument pdf = documentServiceImp.getDocByName(documentName);

                if (pdf.getLevel().equals(level) && pdf.getBranch().equals(branch)
                        && pdf.getSemestre().equals(semestre) ) {
                    // Increment the occurrence count for the document
                    documentOccurrences.put(pdf, documentOccurrences.getOrDefault(pdf, 0) + 1);
                }
            }
        }

        // Sort documents by occurrence count in descending order
        System.out.println(documentOccurrences);
        List<PDFDocument> sortedDocuments = new ArrayList<>(documentOccurrences.keySet());


        sortedDocuments.sort(Comparator.comparingInt(documentOccurrences::get).reversed());

        System.out.println(sortedDocuments);


        Set<PDFDocument> result = new LinkedHashSet<>(sortedDocuments);

        return result;

    }

    @Override
    public Set<PDFDocument> searchDocsByTerms(String request) throws IOException {
        List<String> terms = process.processText(request);
        Map<PDFDocument, Integer> documentOccurrences = new HashMap<>();

        for (String term : terms) {
            System.out.println(term);
            Indexation index = findIndexationByTerm(term);
            if(index==null){
                continue;
            }

            for (String documentName : index.getDocuments()) {
                PDFDocument pdf = documentServiceImp.getDocByName(documentName);

                documentOccurrences.put(pdf, documentOccurrences.getOrDefault(pdf, 0) + 1);
            }
        }
        System.out.println(documentOccurrences);
        List<PDFDocument> sortedDocuments = new ArrayList<>(documentOccurrences.keySet());


        sortedDocuments.sort(Comparator.comparingInt(documentOccurrences::get).reversed());

        System.out.println(sortedDocuments);


        Set<PDFDocument> result = new LinkedHashSet<>(sortedDocuments);

        return result;
    }
}
