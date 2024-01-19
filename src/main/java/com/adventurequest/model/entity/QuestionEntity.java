package com.adventurequest.model.entity;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.Objects;

public class QuestionEntity {
    private Long id;
    private Integer questionNumber;
    private Long questId;
    private String text;
    private byte[] backgroundQuestionPhoto;
    private Boolean isLastQuestion;

    public QuestionEntity(Long id, Integer questionNumber, Long questId, String text, byte[] backgroundQuestionPhoto, Boolean isLastQuestion) {
        this.id = id;
        this.questionNumber = questionNumber;
        this.questId = questId;
        this.text = text;
        this.backgroundQuestionPhoto = backgroundQuestionPhoto;
        this.isLastQuestion = isLastQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

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

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getBackgroundQuestionPhoto() {
        return backgroundQuestionPhoto;
    }

    public void setBackgroundQuestionPhoto(byte[] backgroundQuestionPhoto) {
        this.backgroundQuestionPhoto = backgroundQuestionPhoto;
    }

    public Boolean getIsLastQuestion() {
        return isLastQuestion;
    }


    public void setIsLastQuestion(Boolean lastQuestion) {
        isLastQuestion = lastQuestion;
    }

    public String getBase64Image() {
        return Base64.encodeBase64String(backgroundQuestionPhoto);
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
               "id=" + id +
               ", questionNumber=" + questionNumber +
               ", questId=" + questId +
               ", text='" + text + '\'' +
               ", backgroundQuestionPhoto=" + Arrays.toString(backgroundQuestionPhoto) +
               ", isLastQuestion=" + isLastQuestion +
               '}';
    }
}