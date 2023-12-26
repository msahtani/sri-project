package ma.ensa.sriapi.controller;
import ma.ensa.sriapi.model.Teacher;
import ma.ensa.sriapi.service.TeacherServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherServiceImp teacherServiceImp ;
    public TeacherController(TeacherServiceImp teacherServiceImp) {
        this.teacherServiceImp = teacherServiceImp;
    }
    @GetMapping ("/")
    public List<Teacher> getTeachers(){
        return teacherServiceImp.getTeachers();
    }
   @GetMapping("/{email}")
    public Teacher getTeacherByEmail(@PathVariable String email){
        return teacherServiceImp.getTeacherByEmail(email);
    }
    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher){
        return teacherServiceImp.addTeacher(teacher);
    }


}
