package com.questionanswer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.questionanswer.domain.Answer;
import com.questionanswer.domain.Question;
import com.questionanswer.domain.Topic;
import com.questionanswer.dto.AnswerDTO;
import com.questionanswer.dto.QuestionDTO;
import com.questionanswer.dto.TopicDTO;
import com.questionanswer.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public List<QuestionDTO> getAllQuestionsWithAnswers() {

		List<Question> questionList = questionRepository.findAllQuestionsWithAnswers();
		if (!CollectionUtils.isEmpty(questionList)) {
			List<QuestionDTO> dtoList = new ArrayList<QuestionDTO>();
			AnswerDTO ansDto = null;
			TopicDTO topicDto;
			List<AnswerDTO> ansDtoList = new ArrayList<AnswerDTO>();
			List<TopicDTO> topicDtoList = new ArrayList<TopicDTO>();
			for (Question qs : questionList) {
				List<Answer> answerList = qs.getAnswers();
				List<Topic> topicList = qs.getTopics();
				if (answerList != null ) {
					for (Answer ans : answerList) {
						ansDto = new AnswerDTO(ans.getAnsId(), ans.getAnsString());
						ansDtoList.add(ansDto);
					}
				}
				if(topicList != null){
					for (Topic topic : topicList) {
						topicDto = new TopicDTO(topic.getTopicId(), topic.getTopicName());
						topicDtoList.add(topicDto);
					}
				}
					dtoList.add(
							new QuestionDTO(qs.getQsId(), qs.getQsString(), qs.getDate(), ansDtoList, topicDtoList));
				}
			return dtoList;
			}
		return null;
			
		}
}
