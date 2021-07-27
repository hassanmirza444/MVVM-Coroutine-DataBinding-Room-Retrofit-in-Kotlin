package com.hssn_mirza.digitify_code_challenge.room;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hssn_mirza.digitify_code_challenge.model.MovieModel;


@Database(entities = {MovieModel.class}, version = 1,exportSchema = false)
public abstract class OfflineMoviesAppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "offline_movies";
    private static OfflineMoviesAppDatabase INSTANCE;

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */


    public static OfflineMoviesAppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    OfflineMoviesAppDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract MoviesDao moviesDao();


}
