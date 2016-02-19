package com.movies.app.popularmovies.Data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DIMPESH : ${month}
 */
public class MovieContract
{
    public static final String CONTENT_AUTHORITY="com.movies.app.popularmovies.app";
    //Add the BASE_CONTENT_URI

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final class MovieEntry implements BaseColumns
    {
        //Table
        public static final String TABLE_MOVIE="movie_detail";

        // Columns name
        public static final String COLUMN_ID="_id";
        public static final String COLUMN_TITLE= "title";
        public static final String COLUMN_OVERVIEW="overview";
        public static final String COLUMN_RELEASE_DATE="release_date";
        public static final String COLUMN_VOTE_AVERAGE="vote_average";
        public static final String COLUMN_POSTER_PATH="poster_path";
        public static final String COLUMN_BACKDROP_PATH="backdrop_path";
        public static final String COLUMN_MOVIE_ID="movie_id";


        // Create Content Uri
        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(TABLE_MOVIE).build();

        //Create cursor of base dir type for multiple entries
        public static final String CONTENT_DIR_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+TABLE_MOVIE;

        // CURSOR FOR SINGLE ENTRY
        public static final String CONTENT_ITEM_TYPE=ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+TABLE_MOVIE;

        // FOR BUILDING URI ON INSERTION
        public static Uri buildMoviesUri(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }

}
