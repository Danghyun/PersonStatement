package com.example.personalstatement.controller.page;

import com.example.personalstatement.controller.session.SessionController;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QualificationsController {

    @GetMapping("/qualifications_page")
    public String qualificationsPage(HttpSession session, Model model) {

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);

        return "qualifications_page";
    }
}
