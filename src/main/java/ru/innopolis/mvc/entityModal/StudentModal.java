package ru.innopolis.mvc.entityModal;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
public class StudentModal implements Serializable {

    @Expose
    private Integer id;

    //Фамилия
    @Expose
    private String surname;

    //Имя
    @Expose
    private String name;

    //Пол
    @Expose
    private String gender;

    //Дата рождения
    @Expose
    private Date birthday;

    //Список занятий
    private List<LessonModal> lessonList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<LessonModal> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonModal> lessonList) {
        this.lessonList = lessonList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentModal that = (StudentModal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        return lessonList != null ? lessonList.equals(that.lessonList) : that.lessonList == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (lessonList != null ? lessonList.hashCode() : 0);
        return result;
    }
}
