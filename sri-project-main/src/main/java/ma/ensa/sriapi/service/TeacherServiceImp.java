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
    public String authenticate(Teacher teacher) {
        Teacher teacher1 = teacherRepository.findByEmail(teacher.getEmail());
        if(teacher1 == null){
            return "Teacher not found";
        }
        if(teacher1.getPassword().equals(teacher.getPassword())){
            return "Teacher authenticated";
        }
        return "Wrong password";
    }
}
