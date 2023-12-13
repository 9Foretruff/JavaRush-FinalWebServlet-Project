package com.adventurequest.model.entity;

import java.util.Arrays;
import java.util.Objects;

public class QuestionEntity {
    private Long id;
    private Integer numberOfQuestion;
    private Long questId;
    private String text;
    private byte[] backgroundQuestionPhoto;
    private Boolean lastQuestion;

    public QuestionEntity(Long id, Integer numberOfQuestion, Long questId, String text, byte[] backgroundQuestionPhoto, Boolean lastQuestion) {
        this.id = id;
        this.numberOfQuestion = numberOfQuestion;
        this.questId = questId;
        this.text = text;
        this.backgroundQuestionPhoto = backgroundQuestionPhoto;
        this.lastQuestion = lastQuestion;
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

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
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

    public Boolean getLastQuestion() {
        return lastQuestion;
    }

    public void setLastQuestion(Boolean lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
               "id=" + id +
               ", numberOfQuestion=" + numberOfQuestion +
               ", questId=" + questId +
               ", text='" + text + '\'' +
               ", backgroundQuestionPhoto=" + Arrays.toString(backgroundQuestionPhoto) +
               ", lastQuestion=" + lastQuestion +
               '}';
    }
}