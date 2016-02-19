package com.movies.app.popularmovies.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DIMPESH : ${month}
 */
public class MovieDbHelper extends SQLiteOpenHelper{
    public static final String LOG_TAG=MovieDbHelper.class.getSimpleName();

    // Database name and version
    private static final String DATABASE_NAME="movies.db";
    private static final int DATABASE_VERSION=1;



    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE="CREATE TABLE "+ MovieContract.MovieEntry.TABLE_MOVIE+"("
                + MovieContract.MovieEntry._ID+" INTEGER NOT NULL AUTOINCREMENT"
                +MovieContract.MovieEntry.COLUMN_MOVIE_ID+" TEXT NOT NULL"
                +MovieContract.MovieEntry.COLUMN_TITLE+" TEXT NOT NULL"
                +MovieContract.MovieEntry.COLUMN_OVERVIEW+" TEXT NOT NULL"
                +MovieContract.MovieEntry.COLUMN_RELEASE_DATE+"TEXT NOT NULL"
                +MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE+" TEXT NOT NULL"
                +MovieContract.MovieEntry.COLUMN_POSTER_PATH+" BLOB NOT NULL"
                +MovieContract.MovieEntry.COLUMN_BACKDROP_PATH+" BLOB NOT NULL"
                +"ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }


    //Update the Database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop the Table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MovieContract.MovieEntry.TABLE_MOVIE);
        // Recreate the Table
        onCreate(sqLiteDatabase);

    }
}
