package com.questionanswer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.questionanswer.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
