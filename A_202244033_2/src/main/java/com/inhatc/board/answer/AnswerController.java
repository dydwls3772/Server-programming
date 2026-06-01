package com.inhatc.board.answer;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PatchExchange;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import com.inhatc.board.question.Question;
import com.inhatc.board.question.QuestionService;

import org.springframework.ui.Model;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.inhatc.board.user.User;
import com.inhatc.board.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String create(Model model, @PathVariable("id") Integer qid, 
			@Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		Question question = this.questionService.getQuestion(qid);
		User user = this.userService.get(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		this.answerService.create(question, answerForm.getContent(),  user);
		return String.format("redirect:/question/detail/%s", qid);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal 
	principal) {
	Answer answer = this.answerService.get(id);
	if(!answer.getAuthor().getUsername().equals(principal.getName())) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다.");
	}
	answerForm.setContent(answer.getContent());
	return "answer_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
	@PathVariable("id") Integer id, Principal principal) {
	if (bindingResult.hasErrors()) {
	return "answer_form";
	}
	Answer answer = this.answerService.get(id);
	if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다.");
	}
	this.answerService.modify(answer, answerForm.getContent());
	return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
	Answer answer = this.answerService.get(id);
	if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이없습니다.");
	}
	Integer qId = answer.getQuestion().getId();
	this.answerService.delete(answer);
	return String.format("redirect:/question/detail/%s", qId);
	}
}
