package com.theta.animationdemo.room_database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Rozina on 07/03/19.
 */

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    //@Query("SELECT * from word_table ORDER BY word ASC")
    //List<Word> getAllWords();

    @Query("SELECT * FROM word_table WHERE word=:query")
    LiveData<List<Word>> searchAllWords(String query);

    @Query("SELECT * FROM word_table WHERE word=:query")
    List<Word> searchWords(String query);
}
