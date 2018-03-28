package com.questionanswer.dto;

import java.util.Date;
import java.util.List;

public class QuestionDTO {

	private Long qsId;
	private String qsString;

	private Date date;

	private List<AnswerDTO> dtoAnswers;

	private List<TopicDTO> dtoTopics;

	public QuestionDTO() {
		super();
	}

	public QuestionDTO(Long qsId, String qsString, Date date) {
		super();
		this.qsId = qsId;
		this.qsString = qsString;
		this.date = date;
	}
	
	public QuestionDTO(Long qsId, String qsString, Date date, List<AnswerDTO> dtoAnswers) {
		super();
		this.qsId = qsId;
		this.qsString = qsString;
		this.date = date;
		this.dtoAnswers = dtoAnswers;
	}
	
	public QuestionDTO(Long qsId, String qsString, Date date, List<AnswerDTO> dtoAnswers, List<TopicDTO> dtoTopics) {
		super();
		this.qsId = qsId;
		this.qsString = qsString;
		this.date = date;
		this.dtoAnswers = dtoAnswers;
		this.dtoTopics = dtoTopics;
	}

	public Long getQsId() {
		return qsId;
	}

	public void setQsId(Long qsId) {
		this.qsId = qsId;
	}

	public String getQsString() {
		return qsString;
	}

	public void setQsString(String qsString) {
		this.qsString = qsString;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<AnswerDTO> getAnswers() {
		return dtoAnswers;
	}

	public void setAnswers(List<AnswerDTO> dtoAnswers) {
		this.dtoAnswers = dtoAnswers;
	}

	public List<TopicDTO> getTopics() {
		return dtoTopics;
	}

	public void setTopics(List<TopicDTO> dtoTopics) {
		this.dtoTopics = dtoTopics;
	}

	@Override
	public String toString() {
		return "QuestionDTO [qsId=" + qsId + ", qsString=" + qsString + ", date=" + date + "]";
	}

}
