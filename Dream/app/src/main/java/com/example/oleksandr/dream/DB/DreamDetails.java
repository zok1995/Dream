package com.example.oleksandr.dream.DB;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Oleksandr on 06.12.2015.
 */
public class DreamDetails implements Serializable{

    public static final String ID_FIELD = "dream_id";
    public static final String DREAM_NAME = "cMameDream";
    public static final String DESCRIPTION = "Description";

    @DatabaseField(generatedId = true, columnName = ID_FIELD)
    public int dreamID;

    @DatabaseField(canBeNull = false, columnName = DREAM_NAME)
    public String dreamName;

    @DatabaseField(columnName = DESCRIPTION)
    public String descriptionDream;

    public DreamDetails(){

    }
    public DreamDetails(final String dreamName,final String descriptionDream) {
        this.dreamName = dreamName;
        this.descriptionDream = descriptionDream;
    }
}
