package com.questionanswer.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.questionanswer.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q FROM Question q INNER JOIN FETCH q.answers  ans  INNER JOIN FETCH q.topics t")
	Set<Question> findAllQuestionsWithAnswers();

	@Query("SELECT q FROM Question q WHERE qsId= :givenId")
	Set<Question> findByQuestionId(@Param("givenId") Long id);

}