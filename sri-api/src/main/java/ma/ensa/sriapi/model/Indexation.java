package ma.ensa.sriapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class Indexation {
    private String id ;
    private String term ;
    private List<String> documents;
    public Indexation(){}
    public Indexation(String term ){
        this.term=term ;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
