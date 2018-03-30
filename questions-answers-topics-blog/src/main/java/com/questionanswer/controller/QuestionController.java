package com.questionanswer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.questionanswer.dto.QuestionDTO;
import com.questionanswer.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllQuestionsWithAnswers() {
		List<QuestionDTO> questionList = questionService.getAllQuestionsWithAnswers();
		if (questionList == null) {
			return new ResponseEntity<Error>(new Error("No data found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<QuestionDTO>>(questionList, HttpStatus.OK);
	}

	@RequestMapping(value="/saveQuestion",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveQuestionsWithAnswersTopics(@RequestBody QuestionDTO questionDto) {
		try {
			questionService.saveQuestionsWithAnswersTopics(questionDto);
		} catch (Exception e) {
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/saveAnswer/{id}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveAnswerWithQuestionId(@RequestBody QuestionDTO questionDto,@PathVariable("id") String str){
		Long id= Long.parseLong(str);
		try {
			questionService.saveAnswerWithQuestionId(questionDto,id);
		} catch (Exception e) {
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	class Error {
		private String message;

		public Error(String message) {
			super();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
