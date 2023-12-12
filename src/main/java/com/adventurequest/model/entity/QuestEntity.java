package com.adventurequest.model.entity;

import java.util.Arrays;
import java.util.Objects;

public class QuestEntity {
    private Long id;
    private String name;
    private String description;
    private byte[] questPhoto;
    private DifficultyEnum difficulty;
    private String author;

    public QuestEntity(Long id, String name, String description, byte[] questPhoto, DifficultyEnum difficulty, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questPhoto = questPhoto;
        this.difficulty = difficulty;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestEntity that = (QuestEntity) o;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getQuestPhoto() {
        return questPhoto;
    }

    public void setQuestPhoto(byte[] questPhoto) {
        this.questPhoto = questPhoto;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "QuestEntity{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", questPhoto=" + Arrays.toString(questPhoto) +
               ", difficulty=" + difficulty +
               ", author='" + author + '\'' +
               '}';
    }
}
