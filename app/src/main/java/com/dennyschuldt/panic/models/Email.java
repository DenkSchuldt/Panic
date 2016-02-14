package com.dennyschuldt.panic.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by denny on 2/7/16.
 */
public class Email implements Serializable {

    public static final String ID = "_id";
    public static final String ADDRESS = "address";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = ADDRESS)
    private String address;

    public Email() {
        this.id = 0;
        this.address = "";
    }

    public Email(String address) {
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

}
