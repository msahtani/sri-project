package ma.ensa.sriapi.controller;
import ma.ensa.sriapi.model.Role;
import ma.ensa.sriapi.service.StudentServiceImp;
import ma.ensa.sriapi.service.TeacherServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthentificationController {

    @Autowired
    private StudentServiceImp studentServiceImp ;
    private TeacherServiceImp teacherServiceImp ;
    public AuthentificationController(StudentServiceImp studentServiceImp, TeacherServiceImp teacherServiceImp) {
        this.studentServiceImp = studentServiceImp;
        this.teacherServiceImp = teacherServiceImp;
    }
    //generate authentification method for student and teacher that returns role of the user
    @PostMapping("/auth")
    public String authenticate(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String studentAuthenticationResult = studentServiceImp.authenticate(email, password);
        if (studentAuthenticationResult != null) {
            return studentAuthenticationResult;
        }

        String teacherAuthenticationResult = teacherServiceImp.authenticate(email, password);
        if (teacherAuthenticationResult != null) {
            return teacherAuthenticationResult;
        }

        return null;
    }

}
