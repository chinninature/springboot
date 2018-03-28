package com.questionanswer.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "qs_id")
	private Long qsId;

	@Column(name = "qs_string")
	private String qsString;

	@Column(name = "date")
	private Date date;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
	private List<Answer> answers;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
	private List<Topic> topics;

	public Question() {
		super();
	}

	public Question(Long qsId, String qsString, Date date) {
		super();
		this.qsId = qsId;
		this.qsString = qsString;
		this.date = date;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Question [qsId=" + qsId + ", qsString=" + qsString + ", date=" + date + "]";
	}

}
