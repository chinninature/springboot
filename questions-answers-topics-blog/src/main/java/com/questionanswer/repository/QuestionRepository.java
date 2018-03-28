package com.questionanswer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.questionanswer.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	@Query("SELECT q FROM Question q INNER JOIN  q.answers  ans INNER JOIN FETCH q.topics t")
	List<Question> findAllQuestionsWithAnswers();

}
