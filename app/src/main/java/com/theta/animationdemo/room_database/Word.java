package com.theta.animationdemo.room_database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Rozina on 07/03/19.
 */
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @ColumnInfo(name = "word2")
    private String mWord2;

    public Word(@NonNull String word) {
        this.mWord = word;
        this.mWord2 = word;
    }

    public String getWord() {
        return this.mWord;
    }

    public String getWord2() {
        return this.mWord2;
    }

    public void setMWord2(String mWord2) {
        this.mWord2 = mWord2;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
