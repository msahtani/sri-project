package ma.ensa.sriapi.service;
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
    public String authenticate(Student student) {
        Student student1 = studentsRepository.findByEmail(student.getEmail());
        if(student1!=null){
            if(student1.getPassword().equals(student.getPassword())){
                return "success";
            }
            else{
                return "wrong password";
            }
        }
        else{
            return "student not found";
        }
    }

}
