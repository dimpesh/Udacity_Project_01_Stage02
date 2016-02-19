package com.movies.app.popularmovies.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by DIMPESH : ${month}
 */
public class MovieProvider extends ContentProvider{

    public static final String LOG_TAG=MovieProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MovieDbHelper mDbHelper;

    // Codes For UriMatcher
// May Try to remove static
    private static final int MOVIE=100;
    //code for ID Specific

    // try to do also by removing static
    private  static final int MOVIE_WITH_ID=200;

    private static UriMatcher buildUriMatcher()
    {
        // Build URI Matcher By adding specific code to return based on common to use NO_MATCHER

        final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);

        final String authority=MovieContract.CONTENT_AUTHORITY;
        // add code for each type we add the Add Uri matcher

        matcher.addURI(authority,MovieContract.MovieEntry.TABLE_MOVIE,MOVIE);
        matcher.addURI(authority,MovieContract.MovieEntry.TABLE_MOVIE+"/#",MOVIE_WITH_ID);


        return matcher;

    }



    @Override
    public boolean onCreate() {

        mDbHelper=new MovieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        final int  match=sUriMatcher.match(uri);
        switch (match)
        {

        }
       return null;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

}
