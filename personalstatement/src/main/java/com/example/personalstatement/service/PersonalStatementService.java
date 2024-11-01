package com.example.personalstatement.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalStatementService {

    private final OpenAIService openAIService;

    @Autowired
    public PersonalStatementService(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    // 자기소개
    public String generateIntro(HttpSession session, String intro, String tone) {
        return openAIService.generateIntro(session, intro, tone);
    }

    // 보유중인 자격증
    public String generateQualifications(String qualifications, String tone) {
        return openAIService.generateQualifications(qualifications, tone);
    }

    // 지원 동기
    public String generateMotivationStatement(String motivationStatement, String tone) {
        return openAIService.generateMotivationStatement(motivationStatement, tone);
    }

    // 관련 경력
    public String generateExperience(String experience, String tone) {
        return openAIService.generateExperience(experience, tone);
    }

    public String generateFinalPersonStatement(HttpSession session) {
        return openAIService.generateFinalPersonStatement(session);
    }

}
