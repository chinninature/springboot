package com.questionanswer.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TOPICS")
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topic_id")
	private Long topicId;

	@Column(name = "topic_name")
	private String topicName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "QUESTIONS_TOPICS", joinColumns = { @JoinColumn(name = "topic_id") }, inverseJoinColumns = {
			@JoinColumn(name = "question_id") })
	private Set<Question> questions;

	public Topic() {
		super();
	}
	
	

	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}



	public Topic(Long topicId, String topicName) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
	}

	public Topic(Long topicId, String topicName, Set<Question> questions) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.questions = questions;
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

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", questions=" + questions + "]";
	}

}
