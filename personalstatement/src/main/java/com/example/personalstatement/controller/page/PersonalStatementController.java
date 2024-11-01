package com.example.personalstatement.controller.page;

import com.example.personalstatement.controller.session.SessionController;
import com.example.personalstatement.service.PersonalStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


// 개인 자소서 생성을 처리하는 컨트롤러
// 자소서 생성 페이지(/generate-personal-statement)에 접근할 때 세션에서 사용자 정보를 불러와 화면에 표시하고, 자소서 생성 요청을 처리하여 PersonalStatementService와 연동.

@Controller
public class PersonalStatementController {

    private final PersonalStatementService personalStatementService;

    @Autowired
    public PersonalStatementController(PersonalStatementService personalStatementService) {
        this.personalStatementService = personalStatementService;
    }

    // Intro 생성 페이지
    @PostMapping("/generate-intro")
    public String generateIntro(
            @RequestParam("intro") String intro,
            @RequestParam("tone") String tone,
            HttpSession session,
            Model model) {
        String generatedIntro = personalStatementService.generateIntro(session, intro, tone);
        model.addAttribute("generatedIntro", generatedIntro);

        model.addAttribute("intro", intro);
        model.addAttribute("tone", tone);

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);
        session.setAttribute("generatedIntro", generatedIntro);
        return "intro_page";
    }

    // Qualifications 생성 페이지
    @PostMapping("/generate-qualifications")
    public String generateQualifications(
            @RequestParam("qualifications") String qualifications,
            @RequestParam("tone") String tone,
            HttpSession session,
            Model model) {
        String generatedQualifications = personalStatementService.generateQualifications(qualifications, tone);
        model.addAttribute("generatedQualifications", generatedQualifications);

        model.addAttribute("qualifications", qualifications);
        model.addAttribute("tone", tone);

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);
        session.setAttribute("generatedQualifications", generatedQualifications);
        return "qualifications_page";
    }

    // MotivationStatement 생성 페이지
    @PostMapping("/generate-motivationStatement")
    public String generateMotivationStatement(
            @RequestParam("motivationStatement") String motivationStatement,
            @RequestParam("tone") String tone,
            HttpSession session,
            Model model) {
        String generatedMotivationStatement = personalStatementService.generateMotivationStatement(motivationStatement, tone);
        model.addAttribute("generatedMotivationStatement", generatedMotivationStatement);

        model.addAttribute("motivationStatement", motivationStatement);
        model.addAttribute("tone", tone);

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);
        session.setAttribute("generatedMotivationStatement", generatedMotivationStatement);
        return "motivationStatement_page";
    }

    // Experience 생성 페이지
    @PostMapping("/generate-experienceStatement")
    public String generateExperienceStatement(
            @RequestParam("experienceStatement") String experienceStatement,
            @RequestParam("tone") String tone,
            HttpSession session,
            Model model) {
        String generatedExperienceStatement = personalStatementService.generateExperience(experienceStatement, tone);
        model.addAttribute("generatedExperienceStatement", generatedExperienceStatement);

        model.addAttribute("experienceStatement", experienceStatement);
        model.addAttribute("tone", tone);

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);
        session.setAttribute("generatedExperienceStatement", generatedExperienceStatement);
        return "experienceStatement_page";
    }

    @GetMapping("/finalPersonStatement_page")
    public String generateFinalPersonStatement(HttpSession session, Model model) {

        String generatedFinalPersonStatement = personalStatementService.generateFinalPersonStatement(session);
        model.addAttribute("generatedFinalPersonStatement", generatedFinalPersonStatement);

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);
        return "finalPersonStatement_page";
    }
}
