package ma.ensa.sriapi.service;
import ma.ensa.sriapi.model.Role;
import ma.ensa.sriapi.repository.StudentsRepository;
import ma.ensa.sriapi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentsRepository studentsRepository ;

    @Override
    public List<Student> getStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Student getStudentById(String id) {

        return studentsRepository.findById(id).get();
    }
    @Override
    public Student addStudent(Student student) {
        studentsRepository.save(student);
        return student;
    }
    @Override
    public String authenticate(String email, String password) {
        Student student = studentsRepository.findByEmail(email);
        if (student == null) {
            return "incorrect combination email/password";
        }

        if (student.getPassword().equals(password)) {
            return "STUDENT";
        } else {
            return "incorrect combination email/password";
        }
    }

}
