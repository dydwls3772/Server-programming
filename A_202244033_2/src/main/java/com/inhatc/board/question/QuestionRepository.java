package com.inhatc.board.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.domain.Specification;

public interface QuestionRepository extends JpaRepository<Question, 
Integer>, JpaSpecificationExecutor<Question>  {

	Question findBySubject(String string);

	Question findBySubjectAndContent(String string, String string2);

	List<Question> findBySubjectLike(String string);
	
	Page<Question> findAll(Pageable pageable);
	
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);

}
