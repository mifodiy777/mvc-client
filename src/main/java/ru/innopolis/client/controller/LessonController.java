package ru.innopolis.client.controller;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.client.Utils;
import ru.innopolis.client.editor.DateCustomEditor;
import ru.innopolis.client.entityModal.LessonModal;
import ru.innopolis.client.exception.DataSQLException;
import ru.innopolis.client.service.LessonService;
import ru.innopolis.client.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private StudentService studentService;

    /**
     * Преобразователь даты для сборки объекта LessonModal
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }

    /**
     * Получение страницы занятий
     *
     * @return страница занятий
     */
    @RequestMapping(value = "lessonPage", method = RequestMethod.GET)
    public String getLessonPage() {
        return "lessons";
    }

    /**
     * Получение всех занятий - для плагина DataTable по ajax
     *
     * @return список всех занятий в формате JSON
     */
    @RequestMapping(value = "allLessons", method = RequestMethod.GET)
    public ResponseEntity<String> getLessons() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        try {
            return Utils.convertListToJson(gsonBuilder.excludeFieldsWithoutExposeAnnotation(), lessonService.getLessonList());
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    /**
     * Форма создания занятия
     *
     * @param map
     * @return форма
     */
    @RequestMapping(value = "lesson", method = RequestMethod.GET)
    public String addLessonPage(ModelMap map) {
        map.addAttribute("type", "Режим добавления занятия");
        map.addAttribute("lesson", new LessonModal());
        return "lesson";
    }

    /**
     * Форма редактирования занятий
     *
     * @param id  - занятия
     * @param map
     * @return форма
     */
    @RequestMapping(value = "lesson/{id}", method = RequestMethod.GET)
    public String editLessonForm(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        map.addAttribute("type", "Режим редактирования занятия");
        try {
            map.addAttribute("lesson", lessonService.getLesson(id));
            return "lesson";
        } catch (DataSQLException e) {
            map.addAttribute("message", "Ошибка получения данных для формы");
            response.setStatus(409);
            return "error";
        }

    }

    /**
     * Форма добавления студентов к занятиям
     *
     * @param idLesson - занятия
     * @param map
     * @return форма
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "putStudent/{id}", method = RequestMethod.GET)
    public String putStudentForm(@PathVariable("id") Integer idLesson, ModelMap map, HttpServletResponse response) {
        map.addAttribute("type", "Режим добавления студентов к занятиям");
        try {
            map.addAttribute("lesson", lessonService.getLesson(idLesson));
            //получение студентов не посетивших текущее занятие
            map.addAttribute("studentList", studentService.getStudentListIsNotLesson(idLesson));
        } catch (DataSQLException e) {
            map.addAttribute("message", "Ошибка получения данных для формы");
            response.setStatus(409);
            return "error";
        }


        return "putStudent";
    }

    /**
     * Сохранение занятия с добавленным студентом
     *
     * @param idLesson  Занятие
     * @param idStudent Студент
     * @param map
     * @return страницца success(msg)
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "putSaveStudent", method = RequestMethod.POST)
    public String putSaveStudent(@RequestParam("id") Integer idLesson,
                                 @RequestParam("student") Integer idStudent,
                                 HttpServletResponse response, ModelMap map) {

        try {
            lessonService.putStudent(idLesson, idStudent);
            map.put("message", "Студент добавлен к занятию!");
            return "success";
        } catch (DataSQLException e) {
            map.addAttribute("message", "Ошибка добавления студента к занятию");
            response.setStatus(409);
            return "error";
        }

    }


    /**
     * Сохранение занятия
     *
     * @param lesson Занятие
     * @param map
     * @return страницца success(msg)
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "saveLesson", method = RequestMethod.POST)
    public String saveLesson(LessonModal lesson, ModelMap map, HttpServletResponse response) {
        try {
            lessonService.addLesson(lesson);
            map.put("message", "Занятие сохранено!");
            return "success";
        } catch (DataSQLException e) {
            map.addAttribute("message", "Ошибка добавления студента к занятию");
            response.setStatus(409);
            return "error";
        }

    }

    /**
     * Удаление занятия
     *
     * @param id  занятия
     * @param map
     * @return страницца success(msg)
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "deleteLesson/{id}", method = RequestMethod.POST)
    public String deleteLesson(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            lessonService.deleteLesson(id);
            map.put("message", "Занятие удалено!");
            return "success";
        } catch (DataSQLException e) {
            map.addAttribute("message", "Ошибка удаления занятия");
            response.setStatus(409);
            return "error";
        }

    }
}
