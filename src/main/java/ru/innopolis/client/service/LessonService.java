package ru.innopolis.client.service;


import ru.innopolis.client.entityModal.LessonModal;
import ru.innopolis.client.exception.DataSQLException;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
public interface LessonService {

    LessonModal getLesson(Integer id) throws DataSQLException;

    List<LessonModal> getLessonList() throws DataSQLException;

    void addLesson(LessonModal lesson) throws DataSQLException;

    void deleteLesson(Integer id) throws DataSQLException;

    void putStudent(Integer idLesson, Integer idStudent) throws DataSQLException;

}
