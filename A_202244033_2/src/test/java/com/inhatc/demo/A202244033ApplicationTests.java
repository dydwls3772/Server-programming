package com.inhatc.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inhatc.demo.question.Question;
import com.inhatc.demo.question.QuestionRepository;

@SpringBootTest
class A202244033ApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	@Test
	void testJpa() {        
	Question q1 = new Question();
	q1.setSubject("스프링 부트에 대한 질문");
	q1.setContent("Spring은 무슨 뜻인가요?");
	q1.setCreateDate(LocalDateTime.now());
	this.questionRepository.save(q1);
	Question q2 = new Question();
	q2.setSubject("수업에 대한 질문");
	q2.setContent("쉬는 시간은 있나요?");
	q2.setCreateDate(LocalDateTime.now());
	this.questionRepository.save(q2);
	}
}
