package com.example.personalstatement.controller.profile;

import jakarta.servlet.http.HttpSession;
import com.example.personalstatement.entity.User;
import com.example.personalstatement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    // /profile 경로에 대한 Get 요청 처리
    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        // 로그인된 사용자의 정보를 가져옵니다
        // 여기서는 하드코딩된 사용자 이름으로 사용자를 가져오고 있으므로, 실제 로그인 시스템을 적용할 때 변경 필요
        String username = (String) session.getAttribute("username");  // 실제 로그인된 사용자 이름으로 대체
        User user = userRepository.findByUsername(username);

        // 만약 사용자가 null일 경우, 에러 메시지를 모델에 추가하고 템플릿으로 이동
        if (user == null) {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "profile";  // 에러 메시지와 함께 profile.html로 이동
        }

        // 사용자를 모델에 추가하여 Thymeleaf 템플릿으로 전달
        model.addAttribute("user", user);
        return "profile";  // profile.html 템플릿으로 이동
    }
}
