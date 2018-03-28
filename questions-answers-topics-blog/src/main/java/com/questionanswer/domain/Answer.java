package com.questionanswer.domain;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "ANSWERS")
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ans_id")
	private Long ansId;

	@Column(name = "ans_string")
	private String ansString;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "QUESTIONS_ANSWERS", joinColumns = { @JoinColumn(name = "answer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "question_id") })
	private List<Question> questions;

	public Answer() {
		super();
	}

	public Answer(Long ansId, String ansString) {
		super();
		this.ansId = ansId;
		this.ansString = ansString;
	}

	public Answer(Long ansId, String ansString, List<Question> questions) {
		super();
		this.ansId = ansId;
		this.ansString = ansString;
		this.questions = questions;
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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Answer [ansId=" + ansId + ", ansString=" + ansString + ", questions=" + questions + "]";
	}

}
