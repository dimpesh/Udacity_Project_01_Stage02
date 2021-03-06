package com.movies.app.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity{
    //implements} MainActivityFragment.WorkoutListListener{

    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(findViewById(R.id.movie_detail_container)!=null)
        {
            mTwoPane=true;

            if(savedInstanceState==null)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_container,new DetailActivityFragment(),DETAILFRAGMENT_TAG).commit();
            }
        }
        else
        {
            mTwoPane=false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MainActivityFragment ff= (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_movie);

    }

//    @Override
//    public void onClick(long id) {
//        View fragmentContainer=findViewById(R.id.movie_detail_container);
//        if(fragmentContainer!=null)
//        {
//            DetailActivityFragment details=new DetailActivityFragment();
//            FragmentTransaction ft=getFragmentManager().beginTransaction();
//        }
//    }
}
