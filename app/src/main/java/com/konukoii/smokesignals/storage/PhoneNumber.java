package com.konukoii.smokesignals.storage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by porterhaet on 11/6/17.
 */

@Entity
public class PhoneNumber {

    public PhoneNumber(String number) {
        this.setNumber(number);
    }

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "number")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return number;
    }
}
