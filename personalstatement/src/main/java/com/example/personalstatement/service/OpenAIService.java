package com.example.personalstatement.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class OpenAIService {

    private final OpenAiService openAiService;

    // OpenAI API 키를 주입받음
    public OpenAIService(@Value("${spring.ai.openai.api-key}") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("OpenAI API key must be set.");
        }
        this.openAiService = new OpenAiService(apiKey);
    }

    // Intro(소개) 생성 메서드
    public String generateIntro (HttpSession session, String intro, String tone) {
        String realname = (String) session.getAttribute("realname");
        String prompt = String.format(
                "자기소개서를 작성하려고 합니다. 자기소개서의 자기소개 파트를 작성해주세요." +
                "모든 문장은 한국어로 작성되며, 외국어 단어나 한자가 포함되지 않도록 해주세요. " +
                "300자 이내로 작성하며, 문장을 끝맺음 없이 자연스럽게 이어질 수 있도록 작성해 주세요. " +
                "마무리 인사나 '감사합니다'와 같은 표현을 포함하지 마세요. " +
                "이름: %s," +
                "소개: %s, " +
                "어조: %s, ",
                realname, intro, tone
        );
        return generateTextFromPrompt(prompt);
    }

    // Qualifications(자격증) 생성 메서드
    public String generateQualifications (String qualifications, String tone) {
        String prompt = String.format(
                "자기소개서를 작성하려고 합니다. 자기소개서의 자격증 파트를 작성해주세요." +
                "모든 문장은 한국어로 작성되며, 외국어 단어나 한자가 포함되지 않도록 해주세요. " +
                "300자 이내로 작성하며, 문장을 끝맺음 없이 자연스럽게 이어질 수 있도록 작성해 주세요. " +
                "마무리 인사나 '감사합니다'와 같은 표현을 포함하지 마세요. " +
                "자격증: %s, " +
                "어조: %s, ",
                qualifications, tone
        );
        return generateTextFromPrompt(prompt);
    }

    // Company 생성 매서드
    public String generateMotivationStatement (String motivationStatement, String tone) {
        String prompt = String.format(
                "자기소개서를 작성하려고 합니다. 자기소개서의 지원동기 파트를 작성해주세요." +
                "모든 문장은 한국어로 작성되며, 외국어 단어나 한자가 포함되지 않도록 해주세요. " +
                "300자 이내로 작성하며, 문장을 끝맺음 없이 자연스럽게 이어질 수 있도록 작성해 주세요. " +
                "마무리 인사나 '감사합니다'와 같은 표현을 포함하지 마세요. " +
                "회사 지원동기: %s, " +
                "어조: %s, ",
                motivationStatement, tone
        );
        return generateTextFromPrompt(prompt);
    }

    public String generateExperience (String experience, String tone) {
        String prompt = String.format(
                "자기소개서를 작성하려고 합니다. 자기소개서의 경력사항 파트를 작성해주세요." +
                "모든 문장은 한국어로 작성되며, 외국어 단어나 한자가 포함되지 않도록 해주세요. 300자 이내로 작성해 주세요." +
                "자격증: %s, " +
                "어조: %s, ",
                experience, tone
        );
        return generateTextFromPrompt(prompt);
    }
    public String generateFinalPersonStatement(HttpSession session) {
        String generatedIntro = (String) session.getAttribute("generatedIntro");
        String generatedQualifications = (String) session.getAttribute("generatedQualifications");
        String generatedMotivationStatement = (String) session.getAttribute("generatedMotivationStatement");
        String generatedExperienceStatement = (String) session.getAttribute("generatedExperienceStatement");
        System.out.println(generatedIntro);
        System.out.println(generatedQualifications);
        System.out.println(generatedMotivationStatement);
        System.out.println(generatedExperienceStatement);

        String prompt = String.format(
                "다음 내용들을 요악하지 않고 자연스럽게 연결하여 하나의 자기소개서로 작성해 주세요. " +
                "내용이 줄어들지 않도록 주어진 내용을 그대로 유지해 주세요. " +
                "모든 문장은 한국어로 작성되며, 외국어 단어나 한자가 포함되지 않도록 해주세요. " +
                "소개: %s, " +
                "자격증: %s, " +
                "지원동기: %s, " +
                "경력사항: %s, ",
                generatedIntro, generatedQualifications, generatedMotivationStatement, generatedExperienceStatement
        );
        return generateTextFromPrompt(prompt);
    }

    // 공통 메서드 : OpenAI API 호출
    public String generateTextFromPrompt (String prompt) {

        // ChatGPT API 호출을 위한 ChatMessage 객체 생성
        ChatMessage message = new ChatMessage("user", prompt);

        // ChatCompletionRequest 객체 생성
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Collections.singletonList(message))
                .maxTokens(1500)
                .build();

        // OpenAI API 호출
        ChatCompletionResult result = openAiService.createChatCompletion(chatCompletionRequest);

        // 결과에서 첫 번째 선택지의 메시지 가져오기
        ChatCompletionChoice choice = result.getChoices().get(0);
        return choice.getMessage().getContent().trim();
    }
}
