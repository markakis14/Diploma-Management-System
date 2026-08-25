package com.myy803.diplomas_mgt_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myy803.diplomas_mgt_app.model.Application;
import com.myy803.diplomas_mgt_app.model.Student;
import com.myy803.diplomas_mgt_app.model.Thesis;
import com.myy803.diplomas_mgt_app.service.ProfessorService;
import com.myy803.diplomas_mgt_app.service.StudentService;
import com.myy803.diplomas_mgt_app.service.UserService;

@Controller
public class StudentController {
	
	@Autowired
    private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService profService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

    @RequestMapping("/student/dashboard")
    public String getUserHome(){
        return "student/dashboard";
    }
    
    @RequestMapping("/student/edit_info")
    public String getEditInfo(Model model){
    	model.addAttribute("user_data", studentService.findById(userService.getCurrentUserId()));
        return "student/edit_info";
    }
    
    @RequestMapping("/student/thesis_list")
    public String seeThesisList(Model model){
    	List<Thesis> thesisList = studentService.findAll();
    	model.addAttribute("thesis_list", thesisList);
        return "student/thesis_list";
    }
    
    @RequestMapping("/student/view_thesis")
    public String viewThesis(@ModelAttribute("thesisId") int theId,
    		Model theModel) {
    	Thesis thesis = studentService.findThesisById(theId);
    	theModel.addAttribute("thesis",thesis);
    	theModel.addAttribute("profName", profService.findProfById(thesis.getProfessorId()).getFullName());
    	return "student/view_thesis";
    }
    
    @RequestMapping("/student/apply")
    public String applyToThesis(@ModelAttribute("thesisId") int theId,
    		Model theModel) {
    	Thesis thesis = studentService.findThesisById(theId);
    	Student student = studentService.findById(userService.getCurrentUserId());
    	if (!student.alreadyApplied(thesis.getId())) {
    	Application application = new Application();
    	
    	application.setApplicant(student);
    	application.setThesis(thesis);
    	
    	studentService.saveApplication(application);
    	studentService.saveThesis(thesis);
    	studentService.save(student);
    	}
    	
    	return seeThesisList(theModel);
    }
    
    @RequestMapping("/student/update_info")
    public String updateInfo(@RequestParam("fullName") String name,
    		@RequestParam("yearsOfStudies") int years,
    		@RequestParam("coursesRemaining") int courses,
    		@RequestParam("currentAvgGrade") float avgGrade) {
    	Student student = studentService.findById(userService.getCurrentUserId());
    	student.setFullName(name);
    	student.setCoursesRemaining(courses);
    	student.setCurrentAvgGrade(avgGrade);
    	student.setYearsOfStudies(years);
    	studentService.save(student);
    	return getUserHome();
    }
}
