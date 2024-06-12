package com.quest.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.admin.entity.Question;
import com.quest.admin.repository.QuestionRepository;

@Service
public class QuestionService {
	private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    public Question findById(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        return questionOptional.orElse(null);
    }
}
