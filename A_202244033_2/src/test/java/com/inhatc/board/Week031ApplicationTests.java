package com.inhatc.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

import com.inhatc.board.answer.Answer;
import com.inhatc.board.answer.AnswerRepository;
import com.inhatc.board.question.Question;
import com.inhatc.board.question.QuestionRepository;
import com.inhatc.board.question.QuestionService;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Week031ApplicationTests {
	
	@Autowired
	private QuestionService questionService;
	@Test
	void testJpa() {
	for (int i = 1; i <= 200; i++) {
	String subject = String.format("테스트 데이터 [%03d]", i);
	String content = "제곧내";
	this.questionService.create(subject, content);
	}
}
}
	
//	@Autowired
//	private QuestionRepository questionRepository;
//	@Autowired
//	private AnswerRepository answerRepository;
//
//	@Transactional
//	@Test
//	void contextLoads() {
//		// 저장
//		Question q1 = new Question();
//		q1.setSubject("스프링 부트에 대한 질문");
//		q1.setContent("Spring은 무슨 뜻인가요?");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question();
//		q2.setSubject("수업에 대한 질문");
//		q2.setContent("쉬는 시간은 있나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
		
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//		Question q = all.get(0);
//		assertEquals("스프링 부트에 대한 질문", q.getSubject());
		
//		Optional<Question> oq = this.questionRepository.findById(2);
//		if (oq.isPresent()) {
//		Question q = oq.get();
//		assertEquals("수업에 대한 질문", q.getSubject());
		
//		Question q = this.questionRepository.findBySubject("스프링 부트에 대한 질문");
//		assertEquals(1, q.getId());
		
//		Question q = this.questionRepository.findBySubjectAndContent("스프링 부트에 대한 질문","Spring은 무슨 뜻인가요?");
//		assertEquals(1, q.getId());
		
//		List<Question> qLike = this.questionRepository.findBySubjectLike("스프링%");
//		assertEquals(1, qLike.size());
		
		 //조회 후 수정
//		Optional<Question> q = this.questionRepository.findById(1);
//		assertTrue(q.isPresent());
//	    Question q1 = q.get();
//	    q1.setSubject("질문");
//	    this.questionRepository.save(q1);
//		
//		//삭제
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//	    Question q = oq.get();
//	    this.questionRepository.delete(q);
//	    assertEquals(1, this.questionRepository.count());
	    
		//답변 생성
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//	    Question q = oq.get();
//	    Answer a1 = new Answer();
//		a1.setContent("있습니다.");
//		a1.setCreateDate(LocalDateTime.now());
//		a1.setQuestion(q);
//		this.answerRepository.save(a1);
//		assertEquals(1, this.answerRepository.count());
		
		//답변 조회
//		Optional<Answer> oa = this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals("2", a.getQuestion().getId());

//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		List<Answer> answers = oq.get().getAnswers();
//	    assertEquals(1, answers.size());

		
//		}
//	}


