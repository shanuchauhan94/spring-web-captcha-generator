package org.spring.captcha.controller;


import cn.apiclub.captcha.Captcha;
import org.spring.captcha.captcha.CaptchaUtil;
import org.spring.captcha.model.Student;
import org.spring.captcha.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String registerStudent(Model model) {
        Student user = new Student();
        getCaptcha(user);
        model.addAttribute("student", user);
        return "registerStudent";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute Student user, Model model) {

        if (user.getCaptcha().equals(user.getHiddenCaptcha())) {
            String msg = service.createUser(user);
            model.addAttribute("message", msg + " Registered successfully!");
            return "redirect:allUsers";
        } else {
            model.addAttribute("message", "Invalid Captcha");
            getCaptcha(user);
            model.addAttribute("student", user);
        }
        return "registerStudent";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model) {
        List<Student> userList = service.getAllUsers();
        model.addAttribute("userList", userList);
        return "listUsers";
    }

    private void getCaptcha(Student user) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha(""); // value entered by the User
        user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));

    }

}
