package ma.ensa.sriapi.service;
import ma.ensa.sriapi.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeacherService {
    public Teacher getTeacherByEmail(String email) ;
    public List<Teacher> getTeachers() ;
    public Teacher addTeacher(Teacher teacher) ;
    public String authenticate(Teacher teacher);

}
