package com.myy803.diplomas_mgt_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myy803.diplomas_mgt_app.model.AssignedThesis;
import com.myy803.diplomas_mgt_app.model.BestApplicantStrategyFactory;
import com.myy803.diplomas_mgt_app.model.Professor;
import com.myy803.diplomas_mgt_app.model.Student;
import com.myy803.diplomas_mgt_app.model.Thesis;
import com.myy803.diplomas_mgt_app.service.ProfessorService;
import com.myy803.diplomas_mgt_app.service.StudentService;
import com.myy803.diplomas_mgt_app.service.UserService;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;
	
	private BestApplicantStrategyFactory factory = new BestApplicantStrategyFactory();
	
	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	
    @RequestMapping("/professor/dashboard")
    public String getUserHome(){
        return "professor/dashboard";
    }

    @RequestMapping("/professor/edit_info")
    public String getEditInfo(Model model){
    	model.addAttribute("user_data", professorService.findProfById(userService.getCurrentUserId()));
        return "professor/edit_info";
    }
    
    @RequestMapping("/professor/thesis_list")
    public String seeThesisList(Model model){
    	List<Thesis> thesisList = professorService.findAll();
    	int myId = userService.getCurrentUserId();
    	List<Thesis> myThesis = new ArrayList<>();
    	for (int i=0; i< thesisList.size(); i++) {
    		if (thesisList.get(i).getProfessorId() == myId)
    			myThesis.add(thesisList.get(i));
    	}
    	model.addAttribute("thesis_list", myThesis);
        return "professor/thesis_list";
    }
    
    @RequestMapping("/professor/assigned_list")
    public String seeAssignedList(Model theModel) {
    	List<AssignedThesis> assignedList = professorService.findAllAssigned();
    	int myId = userService.getCurrentUserId();
    	List<AssignedThesis> myThesis = new ArrayList<>();
    	for (int i=0; i< assignedList.size(); i++) {
    		if (assignedList.get(i).getProfessorId() == myId)
    			myThesis.add(assignedList.get(i));
    	}
    	theModel.addAttribute("assigned_list", myThesis);
        return "professor/assigned_list";
    }
    
    @RequestMapping("/professor/update_info")
    public String updateInfo(@ModelAttribute("professor") Professor prof) {
    	Professor professor = professorService.findProfById(userService.getCurrentUserId());
    	professor.setFullName(prof.getFullName());
    	professor.setSpecialty(prof.getSpecialty());
    	professorService.saveProf(professor);
    	return getUserHome();
    }
    
    @RequestMapping("/professor/add_thesis")
    public String addThesis(Model theModel) {
    	Thesis thesis = new Thesis(userService.getCurrentUserId());
    	theModel.addAttribute("thesis", thesis);
    	return "professor/add_thesis";
    }
    
    @RequestMapping("/professor/save")
    public String saveThesis(@ModelAttribute("thesis") Thesis thesis,
    			Model theModel) {
    	thesis.setProfessorId(userService.getCurrentUserId());
    	professorService.save(thesis);
    	return "redirect:/professor/thesis_list";
    }
    
    
	@RequestMapping("/professor/delete")
	public String delete(@RequestParam("thesisId") int theId) {
		professorService.deleteThesisById(theId);
		return "redirect:/professor/thesis_list";
	}
	
	@RequestMapping("/professor/edit_thesis")
	public String edit(@RequestParam("thesisId") int theId, Model theModel) {
		theModel.addAttribute("thesis", professorService.findById(theId));
		return "professor/edit_thesis";
	}
	
	private String assignThesis(int theId, String strategy, Model theModel) {
		Thesis thesis = professorService.findById(theId);
		if (thesis.getApplications().size()>0) {
			Student applicant = thesis.getBestApplicant(
					factory.createStrategy(strategy)
				);
			AssignedThesis assigned = new AssignedThesis();
			assigned.getThesisInfo(thesis);
			assigned.setStudentName(applicant.getFullName());
			professorService.deleteThesisById(theId);
			professorService.saveAssigned(assigned);
			return "redirect:/professor/thesis_list";
		}
		theModel.addAttribute("thesis", thesis);
		return "professor/edit_thesis";
	}
	
	@RequestMapping("/professor/assgn_grade")
	public String assignGrade(@RequestParam("thesisId") int theId, Model theModel) {
		return assignThesis(theId,"bestGrade",theModel);
	}
	
	@RequestMapping("/professor/assgn_courses")
	public String assignCourses(@RequestParam("thesisId") int theId,Model theModel) {
		return assignThesis(theId,"fewestCourses", theModel);
	}
	
	@RequestMapping("/professor/assgn_random")
	public String assignRand(@RequestParam("thesisId") int theId,Model theModel) {
		return assignThesis(theId,"random", theModel);
	}
	
	@RequestMapping("/professor/assgn_student")
	public String assignCourses(@RequestParam("thesisId") int theId, @RequestParam int studentId) {
		Thesis thesis = professorService.findById(theId);
		Student applicant = studentService.findById(studentId);
		AssignedThesis assigned = new AssignedThesis();
		assigned.getThesisInfo(thesis);
		assigned.setStudentName(applicant.getFullName());
		professorService.deleteThesisById(theId);
		professorService.saveAssigned(assigned);
		return "redirect:/professor/thesis_list";
	}
	
	@RequestMapping("/professor/grade")
	public String grade(@RequestParam("thesisId") int theId, Model theModel) {
		theModel.addAttribute("thesis", professorService.findAssignedById(theId));
		return "professor/grade";
	}
	
	
    @RequestMapping("/professor/set_grades")
    public String setGrades(@ModelAttribute("thesis") AssignedThesis thesis,
    			@RequestParam("thesisId") int theId,
    			Model theModel) {
    	AssignedThesis assigned = professorService.findAssignedById(theId);
    	assigned.copyGrades(thesis);
    	professorService.saveAssigned(assigned);
    	return grade(assigned.getId(), theModel);
    }
}
