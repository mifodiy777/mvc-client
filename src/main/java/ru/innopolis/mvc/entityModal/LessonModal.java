package ru.innopolis.mvc.entityModal;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Кирилл on 01.11.2016.
 */
public class LessonModal implements Serializable {

    @Expose
    private Integer id;

    // Тема занятия
    @Expose
    private String topic;

    // Описание занятия
    @Expose
    private String description;

    //Длительно занятия в минутах
    @Expose
    private Integer duration;

    //Дата занятия
    @Expose
    private Date dateLesson;

    //Список студентов посетивших занятие
    private Set<StudentModal> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDateLesson() {
        return dateLesson;
    }

    public void setDateLesson(Date dateLesson) {
        this.dateLesson = dateLesson;
    }

    public Set<StudentModal> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentModal> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonModal that = (LessonModal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (dateLesson != null ? !dateLesson.equals(that.dateLesson) : that.dateLesson != null) return false;
        return students != null ? students.equals(that.students) : that.students == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (dateLesson != null ? dateLesson.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }
}
