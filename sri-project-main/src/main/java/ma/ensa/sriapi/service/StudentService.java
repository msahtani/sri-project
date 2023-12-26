package ma.ensa.sriapi.service;
import ma.ensa.sriapi.model.Role;
import ma.ensa.sriapi.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudents() ;
    public Student getStudentById(String id) ;
    Student addStudent(Student student);

    public String authenticate(String email, String password);
}
