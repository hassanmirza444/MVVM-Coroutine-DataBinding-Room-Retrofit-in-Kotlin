package com.hssn_mirza.digitify_code_challenge.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.hssn_mirza.digitify_code_challenge.model.MovieModel;

import java.io.Serializable;
import java.util.List;

@Dao
public interface MoviesDao extends Serializable {

    @Query("SELECT * FROM MovieModel")
    LiveData<List<MovieModel>> getAllMovieModels();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieModel(MovieModel movieModel);

    @Delete
    void deleteMovieModel(MovieModel movieModel);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovieModel(MovieModel movieModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<MovieModel> cidVos);


}
