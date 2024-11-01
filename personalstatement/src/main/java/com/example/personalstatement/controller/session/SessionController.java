package com.example.personalstatement.controller.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class SessionController {

    public static String Getrealname(HttpSession session, Model model) {
        // 세션에서 사용자 이름과 생년월일을 가져옴
        String realname = (String) session.getAttribute("realname");

        // 세션에 사용자 정보가 없으면 로그인 페이지로 리디렉션
        if (realname == null) {
            return "redirect:/login";
        }

        // 모델에 사용자 정보 추가하여 템플릿으로 전달
        model.addAttribute("realname", realname);
        return realname;
    }

    public static String Getbirthdate(HttpSession session, Model model) {
        String birthdate = (String) session.getAttribute("birthdate");
        if (birthdate == null) {
            return "redirect:/login";
        }

        model.addAttribute("birthdate", birthdate);
        return birthdate;
    }
}
