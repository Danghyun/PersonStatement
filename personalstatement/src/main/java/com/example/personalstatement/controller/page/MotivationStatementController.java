package com.example.personalstatement.controller.page;

import com.example.personalstatement.controller.session.SessionController;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MotivationStatementController {

    @GetMapping("/motivationStatement_page")
    public String motivationStatementPage(HttpSession session, Model model) {

        SessionController.Getrealname(session, model);
        SessionController.Getbirthdate(session, model);

        return "motivationStatement_page";
    }



}
