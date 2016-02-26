package com.movies.app.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by DIMPESH : ${month}
 */
public class MovieCursorAdapter extends CursorAdapter{

    public static final String LOG_TAG=MovieCursorAdapter.class.getSimpleName();
    private Context mContext;
    private int loader_id;
    public MovieCursorAdapter(Context context, Cursor c, int flags)
    {
        super(context, c, flags);
        mContext=context;
        loader_id=flags;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item_movies,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String str=cursor.getString(cursor.getColumnIndex("poster_path"));
        ImageView poster= (ImageView) view.findViewById(R.id.grid_item_movies_imageview);
        Picasso.with(context).load(str).placeholder(R.mipmap.img_placeholder).into(poster);

    }
}
