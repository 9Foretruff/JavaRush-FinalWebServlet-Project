package com.adventurequest.model.entity;

import java.util.Objects;

public class AnswerEntity {
    private Long id;
    private Integer questionId;
    private String text;
    private Boolean isCorrect;

    public AnswerEntity(Long id, Integer questionId, String text, Boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
               "id=" + id +
               ", questionId=" + questionId +
               ", text='" + text + '\'' +
               ", isCorrect=" + isCorrect +
               '}';
    }
}
