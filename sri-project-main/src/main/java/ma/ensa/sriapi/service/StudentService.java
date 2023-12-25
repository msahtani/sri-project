package ma.ensa.sriapi.service;
import ma.ensa.sriapi.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudents() ;
    public Student getStudentById(String id) ;
    Student addStudent(Student student);

    String authenticate(Student student);
}
