package com.movies.app.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;



public class DetailActivity extends AppCompatActivity {

    ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.movie_detail_container,new DetailActivityFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//    getMenuInflater().inflate(R.menu.fragment_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
