package ma.ensa.sriapi.controller;
import ma.ensa.sriapi.model.Indexation;
import ma.ensa.sriapi.model.PDFDocument;
import ma.ensa.sriapi.model.Student;
import ma.ensa.sriapi.service.IndexationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ma.ensa.sriapi.service.StudentServiceImp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceImp studentServiceImp ;
    private final IndexationServiceImp indexationServiceImp;
    public StudentController(StudentServiceImp studentServiceImp, IndexationServiceImp indexationServiceImp) {
        this.studentServiceImp = studentServiceImp;
        this.indexationServiceImp = indexationServiceImp;
    }

   @GetMapping("/")
    public List<Student> getStudents(){
        return studentServiceImp.getStudents() ;
    }

    @GetMapping("/id")
    public Student getStudentById(@RequestBody String id){
        return studentServiceImp.getStudentById(id) ;
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentServiceImp.addStudent(student) ;
    }
    @PostMapping("/login")
    public String authenticate(@RequestBody Student student){
        return studentServiceImp.authenticate(student);
    }
}
