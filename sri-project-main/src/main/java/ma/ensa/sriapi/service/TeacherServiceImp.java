package ma.ensa.sriapi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.sriapi.model.Teacher;
import ma.ensa.sriapi.repository.TeacherRepository;


import java.util.List;


@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    @Override
   public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
    @Override
    public String authenticate(String email, String password) {
        Teacher teacher = teacherRepository.findByEmail(email);
        if (teacher == null) {
            return "incorrect combination email/password";
        }

        if (teacher.getPassword().equals(password)) {
            return "TEACHER";
        } else {
            return "incorrect combination email/password";
        }
    }

}
