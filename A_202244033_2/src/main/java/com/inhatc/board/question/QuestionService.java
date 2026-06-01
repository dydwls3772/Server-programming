package com.inhatc.board.question;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.inhatc.board.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import com.inhatc.board.user.User;
import com.inhatc.board.answer.Answer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
		
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("Question not found.");
		}
	}
	
	public void create(String subject, String content,  User author) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setAuthor(author);
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}
	
	public Page<Question> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.questionRepository.findAll(spec, pageable);
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	private Specification<Question> search(String kw) {
		return new Specification<>() {
		private static final long serialVersionUID = 1L;
		@Override
		public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
			query.distinct(true); // 중복 제거
			Join<Question, User> u1 = q.join("author", JoinType.LEFT);
			Join<Question, Answer> a = q.join("answers", JoinType.LEFT);
			Join<Answer, User> u2 = a.join("author", JoinType.LEFT);
			return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 질문 제목
					cb.like(q.get("content"), "%" + kw + "%"),      // 질문 내용
					cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
					cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
					cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
		}
	 };
  }	
}
