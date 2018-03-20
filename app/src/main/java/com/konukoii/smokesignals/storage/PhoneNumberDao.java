package com.konukoii.smokesignals.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by porterhaet on 11/6/17.
 */

@Dao
public interface PhoneNumberDao {

    @Query("SELECT * FROM phonenumber where number LIKE :number")
    PhoneNumber findByNumber(String number);
    @Query("SELECT number FROM phonenumber")
    List<PhoneNumber> getAll();
    @Query("DELETE FROM phonenumber")
    void deleteAll();

    @Insert
    void addPhoneNumber(PhoneNumber number);

    @Delete
    void deletePhoneNumber(PhoneNumber number);





}
