package com.questionanswer.dto;

import java.util.List;

public class TopicDTO {

	private Long topicId;
	private String topicName;

	private List<QuestionDTO> dtoQuestions;

	public TopicDTO() {
		super();
	}

	public TopicDTO(Long topicId, String topicName) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
	}

	public TopicDTO(Long topicId, String topicName, List<QuestionDTO> dtoQuestions) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.dtoQuestions = dtoQuestions;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<QuestionDTO> getQuestions() {
		return dtoQuestions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.dtoQuestions = questions;
	}

	@Override
	public String toString() {
		return "TopicDTO [topicId=" + topicId + ", topicName=" + topicName + ", questions=" + dtoQuestions + "]";
	}

}
