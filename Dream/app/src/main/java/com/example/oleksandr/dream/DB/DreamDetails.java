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
    public static final String MINUTE = "Minute";
    public static final String HOUR = "Hour";

    @DatabaseField(generatedId = true, columnName = ID_FIELD)
    public int dreamID;

    @DatabaseField(canBeNull = false, columnName = DREAM_NAME)
    public String dreamName;

    @DatabaseField(columnName = DESCRIPTION)
    public String descriptionDream;

    @DatabaseField(columnName = MINUTE)
    public int minute;

    @DatabaseField(columnName = HOUR)
    public int hour;

    public DreamDetails(){

    }

    public DreamDetails(int dreamID, String dreamName, String descriptionDream, int minute, int hour) {
        this.dreamID = dreamID;
        this.dreamName = dreamName;
        this.descriptionDream = descriptionDream;
        this.minute = minute;
        this.hour = hour;
    }
}
