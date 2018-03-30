package com.questionanswer.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.questionanswer.domain.Answer;
import com.questionanswer.domain.Question;
import com.questionanswer.domain.Topic;
import com.questionanswer.dto.AnswerDTO;
import com.questionanswer.dto.QuestionDTO;
import com.questionanswer.dto.TopicDTO;
import com.questionanswer.repository.AnswerRepository;
import com.questionanswer.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public List<QuestionDTO> getAllQuestionsWithAnswers() {

		Set<Question> questionList = questionRepository.findAllQuestionsWithAnswers();
		if (!CollectionUtils.isEmpty(questionList)) {
			List<QuestionDTO> dtoList = new ArrayList<QuestionDTO>();
			AnswerDTO ansDto = null;
			TopicDTO topicDto;
			for (Question qs : questionList) {
				List<AnswerDTO> ansDtoList = new ArrayList<AnswerDTO>();
				List<TopicDTO> topicDtoList = new ArrayList<TopicDTO>();
				Set<Answer> answerList = qs.getAnswers();
				Set<Topic> topicList = qs.getTopics();
				if (answerList != null) {
					for (Answer ans : answerList) {
						ansDto = new AnswerDTO(ans.getAnsId(), ans.getAnsString());
						ansDtoList.add(ansDto);
					}
				}
				if (topicList != null) {
					for (Topic topic : topicList) {
						topicDto = new TopicDTO(topic.getTopicId(), topic.getTopicName());
						topicDtoList.add(topicDto);
					}
				}
				dtoList.add(new QuestionDTO(qs.getQsId(), qs.getQsString(), qs.getDate(), ansDtoList, topicDtoList));
			}
			return dtoList;
		}
		return null;

	}

	@Override
	public void saveQuestionsWithAnswersTopics(QuestionDTO questionDto) {

		Question question = new Question();

		question.setQsId(null);
		question.setQsString(questionDto.getQsString());
		question.setDate(null);

		Set<Answer> ansList = new HashSet<Answer>();
		Set<Topic> topicList = new HashSet<Topic>();
		Set<Question> questionList = new LinkedHashSet<Question>();
		questionList.add(question);

		List<AnswerDTO> ansDtoList = questionDto.getAnswers();
		for (AnswerDTO ansDto : ansDtoList) {
			Answer ans = new Answer(ansDto.getAnsString());
			ansList.add(ans);
			ans.setQuestions(questionList);
		}

		List<TopicDTO> topicDtoList = questionDto.getTopics();
		for (TopicDTO topicDto : topicDtoList) {
			Topic topic = new Topic(topicDto.getTopicName());
			topicList.add(topic);
			topic.setQuestions(questionList);
		}
		question.setAnswers(ansList);
		question.setTopics(topicList);

		questionRepository.save(question);

	}

	@Override
	public void saveAnswerWithQuestionId(QuestionDTO questionDto, Long id) {
		
		Set<Question> questionList = questionRepository.findByQuestionId(id);
		
		List<AnswerDTO> ansDtoList = questionDto.getAnswers();
		Answer ans = null;
		for(AnswerDTO ansDto : ansDtoList){
			ans = new Answer(ansDto.getAnsString());
			ans.setQuestions(questionList);
		}
		answerRepository.save(ans);
		
	}
	
	
	

		
	}

