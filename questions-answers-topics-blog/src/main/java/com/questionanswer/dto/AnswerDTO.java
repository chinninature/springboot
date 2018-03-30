package com.questionanswer.dto;

import java.util.List;

public class AnswerDTO {

	private Long ansId;
	private String ansString;
	private List<QuestionDTO> dtoQuestions;

	public AnswerDTO() {
		super();
	}
	
	

	public AnswerDTO(String ansString) {
		super();
		this.ansString = ansString;
	}



	public AnswerDTO(Long ansId, String ansString) {
		super();
		this.ansId = ansId;
		this.ansString = ansString;
	}

	public AnswerDTO(Long ansId, String ansString, List<QuestionDTO> dtoQuestions) {
		super();
		this.ansId = ansId;
		this.ansString = ansString;
		this.dtoQuestions = dtoQuestions;
	}

	public Long getAnsId() {
		return ansId;
	}

	public void setAnsId(Long ansId) {
		this.ansId = ansId;
	}

	public String getAnsString() {
		return ansString;
	}

	public void setAnsString(String ansString) {
		this.ansString = ansString;
	}

	public List<QuestionDTO> getQuestions() {
		return dtoQuestions;
	}

	public void setQuestions(List<QuestionDTO> dtoQuestions) {
		this.dtoQuestions = dtoQuestions;
	}

	@Override
	public String toString() {
		return "AnswerDTO [ansId=" + ansId + ", ansString=" + ansString + ", questions=" + dtoQuestions + "]";
	}

}
