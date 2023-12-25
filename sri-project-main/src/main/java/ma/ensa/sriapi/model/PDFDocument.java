package ma.ensa.sriapi.model;

import org.springframework.data.annotation.Id;

import  org.springframework.data.mongodb.core.mapping.Document ;

import java.util.Objects;


@Document(collection = "pdfDocuments")
public class PDFDocument {
    @Id
    private String id ;
    private String name;
    private byte[] content;
    private String path ;
    private String semestre  ;
    private String module ;
    private String level ;
    private String branch ;
    public PDFDocument(){}
    public PDFDocument( String level , String branch , String semestre ,String module){

        this.level=level ;
        this.branch= branch ;
        this.semestre = semestre ;
        this.module = module ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PDFDocument that = (PDFDocument) obj;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
