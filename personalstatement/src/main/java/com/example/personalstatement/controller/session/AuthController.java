package com.example.personalstatement.controller.session;

import jakarta.servlet.http.HttpSession;
import com.example.personalstatement.entity.User;
import com.example.personalstatement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 사용자 인증을 처리하는 컨트롤러.
// 주요 기능으로는 회원가입 페이지(/signup), 로그인 페이지(/login)이 있음.

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "signup";
        }
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User loginUser, Model model, HttpSession session) {
        User user = userRepository.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            // 로그인 성공 시 세션에 사용자 이름과 생년월일 저장
            session.setAttribute("username", user.getUsername());
            session.setAttribute("realname", user.getName());
            session.setAttribute("birthdate", user.getBirthdate());
            return "redirect:/intro_page";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
