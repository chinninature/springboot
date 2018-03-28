package com.questionanswer.service;

import java.util.List;

import com.questionanswer.dto.QuestionDTO;

public interface QuestionService {

	List<QuestionDTO> getAllQuestionsWithAnswers();
	

}
