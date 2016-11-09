package ru.innopolis.client;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

import static ru.innopolis.client.config.Constance.DATE_FORMAT;

/**
 * Created by IntelliJ IDEA.
 * UserModal: Mahankov
 * Date: 13.05.2015
 * Time: 15:28:50
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    /**
     * Преобразование коллекции элементов в JSON массив с именем aaData для таблиц
     */
    public static ResponseEntity<String> convertListToJson(GsonBuilder gson, Iterable collection) {
        gson.serializeNulls().setDateFormat(DATE_FORMAT);
        JsonObject obj = new JsonObject();
        obj.add("aaData", gson.create().toJsonTree(collection));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.OK);
    }

}
