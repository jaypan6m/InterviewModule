package com.quest.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quest.admin.dto.QuestionDTO;
import com.quest.admin.entity.Question;
import com.quest.admin.service.QuestionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    
    private final QuestionService questionService;
    
    @Autowired
    public AdminController(QuestionService questionService) {
        this.questionService = questionService;
    }
    

    @GetMapping("/home")
    public String showLoginPage() {
        return  "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        	if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("loggedIn", true);
            return "redirect:dashboard"; // Redirect to dashboard after successful login
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // Return login.html with error message
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session) {
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
            return "login"; // Redirect to login page if not logged in
        }
        return "dashboard"; // Return dashboard.html
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate session
        return "redirect:home"; // Redirect to login page after logout
    }
    
    @GetMapping("/users")
    public String manageUsers() {
    	System.out.println("In users");
        return "users"; // Assuming you have a users.html template
    }

    @GetMapping("/questions")
    public String manageQuestions() {
        return "questions"; // Assuming you have a questions.html template
    }
    
    @GetMapping("/analytics")
    public String viewAnalytics() {
        return "analytics"; // Assuming you have an analytics.html template
    }

    @GetMapping("/settings")
    public String configureSettings() {
        return "settings"; // Assuming you have a settings.html template
    }
    
    @GetMapping("/createquestions")
    public String createQuestions() {
    	return "createquestions";
    }
    
    @PostMapping("/addquestion")
    public String addQuestion(@ModelAttribute("question") QuestionDTO questionDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
    	System.out.println("In addquestion");
        // Check for validation errors
        if (result.hasErrors()) {
            return "createquestions"; // Return to the form if there are errors
        }

        // Create a new Question entity
        Question question = new Question();

        // Set the attributes of the Question entity from the DTO
        question.setDifficulty(questionDTO.getDifficulty());
        question.setLanguage(questionDTO.getLanguage());
        question.setAnswerType(questionDTO.getAnswerType());
        question.setQuestion(questionDTO.getQuestion());

        // Set options and correct answers if required
        // For demonstration purposes, assuming you have optionA, optionB, optionC, optionD in your DTO
        System.out.println(questionDTO.getOptionA());
        System.out.println(questionDTO.getOptionB());
        System.out.println(questionDTO.getOptionC());
        System.out.println(questionDTO.getOptionD()) ;
        
        question.setOptionA(questionDTO.getOptionA());
        question.setOptionB(questionDTO.getOptionB());
        question.setOptionC(questionDTO.getOptionC());
        question.setOptionD(questionDTO.getOptionD());
        System.out.println(questionDTO.getCorrectAnswers());
       question.setCorrectAnswers(questionDTO.getCorrectAnswers());
        // Set correct answers if required
        // Make sure to adapt this based on your actual logic and data model

        // Save the question
        questionService.saveQuestion(question);

        // Add success message
        redirectAttributes.addFlashAttribute("message", "Question added successfully!");

        // Redirect to the createquestions page
        return "redirect:/admin/createquestions";
    }


}
