package ru.innopolis.client.service;


import ru.innopolis.client.entityModal.StudentModal;
import ru.innopolis.client.exception.DataSQLException;

import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
public interface StudentService {

    StudentModal getStudent(Integer id) throws DataSQLException;

    List<StudentModal> getStudentList() throws DataSQLException;

    List<StudentModal> getStudentListIsNotLesson(Integer idLesson) throws DataSQLException;

    void saveStudent(StudentModal student) throws DataSQLException;

    void deleteStudent(Integer id) throws DataSQLException;

    Integer countLesson(Integer studentId) throws DataSQLException;

}
