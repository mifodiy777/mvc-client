package ru.innopolis.mvc.entityModal;

import java.io.Serializable;

/**
 * Created by Кирилл on 02.11.2016.
 */
public class RoleModal implements Serializable{

    private Integer id;

    //Роль
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleModal roleDTO = (RoleModal) o;

        if (id != null ? !id.equals(roleDTO.id) : roleDTO.id != null) return false;
        return name != null ? name.equals(roleDTO.name) : roleDTO.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}