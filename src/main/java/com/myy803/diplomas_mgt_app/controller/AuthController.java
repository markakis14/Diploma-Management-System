package com.myy803.diplomas_mgt_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myy803.diplomas_mgt_app.model.Professor;
import com.myy803.diplomas_mgt_app.model.Role;
import com.myy803.diplomas_mgt_app.model.Student;
import com.myy803.diplomas_mgt_app.model.User;
import com.myy803.diplomas_mgt_app.service.ProfessorService;
import com.myy803.diplomas_mgt_app.service.StudentService;
import com.myy803.diplomas_mgt_app.service.UserService;



@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    ProfessorService professorService;

	@RequestMapping("/login")
    public String login(){
        return "auth/signin"; 
    }
	
	@RequestMapping("/register")
	public String register(Model model){
		model.addAttribute("user", new User());
		return "auth/signup";
	}

	@RequestMapping("/save")
	public String registerUser(@ModelAttribute("user") User user, Model model){
	       
		if(userService.isUserPresent(user)){
			model.addAttribute("successMessage", "User already registered!");
				return "auth/signin";
		}

		userService.saveUser(user);
		if (user.getRole() == Role.PROFESSOR)
			professorService.saveProf(new Professor(user.getId()));
		else
			studentService.save(new Student(user.getId()));
		
		model.addAttribute("successMessage", "User registered successfully!");

		return "auth/signin";
	}
}
