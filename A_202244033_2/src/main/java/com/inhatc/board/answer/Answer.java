package com.inhatc.board.answer;
import java.time.LocalDateTime;

import com.inhatc.board.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import com.inhatc.board.user.User;
@Getter
@Setter
@Entity
public class Answer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Column(columnDefinition = "TEXT")
private String content;
private LocalDateTime createDate; 
// private Question question;
@ManyToOne
private Question question;

@ManyToOne
private User author;

private LocalDateTime modifyDate;

}
