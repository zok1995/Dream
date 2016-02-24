package com.example.oleksandr.dream.DB;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Oleksandr on 06.12.2015.
 */
public class DreamDetails implements Serializable{

    public static final String ID_FIELD = "dream_id";

    @DatabaseField(generatedId = true, columnName = ID_FIELD)
    public int dreamID;

    @DatabaseField(canBeNull = false, columnName = "cMameDream")
    public String dreamName;

    @DatabaseField(columnName = "Description")
    public String descriptionDream;

    public DreamDetails(){

    }
    public DreamDetails(final String dreamName) {
        this.dreamName = dreamName;

    }
}
