package edu.ucsb.cs56.projects.androidapp.smokesignals.storage;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by porterhaet on 11/6/17.
 */

@Entity(indices = {
        @Index(value = "number", unique = true)})
public class PhoneNumber {

    public PhoneNumber(String number) {
        this.setNumber(number);
    }

    @PrimaryKey
    private Integer id;
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

    @Override
    public boolean equals(Object o) {
        if(o instanceof PhoneNumber) {
            return ((PhoneNumber) o).getNumber().equals(this.getNumber());
        }
        return false;
    }
}