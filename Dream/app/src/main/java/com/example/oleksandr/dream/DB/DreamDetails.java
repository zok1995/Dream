package com.example.oleksandr.dream.DB;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Oleksandr on 06.12.2015.
 */
public class DreamDetails implements Serializable{
    private static final long serialVersionUID = -222864131214757024L;

    public static final String ID_FIELD = "student_id";

    @DatabaseField(generatedId = true, columnName = ID_FIELD)
    public int dreamID;

    @DatabaseField(columnName = "cMameDream")
    public String dreamName;

}
