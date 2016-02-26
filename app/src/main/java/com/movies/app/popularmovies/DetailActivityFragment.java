package com.movies.app.popularmovies;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movies.app.popularmovies.Data.MovieContract;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment
{
    boolean isFavourite=false;
    public String share_key;
    ShareActionProvider mShareActionProvider;
    String baseUrlImage="http://image.tmdb.org/t/p/w185/";
    TrailerAdapter myTrailerAdapter;
    TrailerObject[] trailerObjects;
    TrailerObject[] keyObject;
    String baseUrl="http://www.youtube.com/watch?v=";
    String hashApp="#MyMovieTrailer";
    public static String static_key;
    Button fBtn;
    List<String> trailerKeyList;
    String LOG_TAG= DetailActivityFragment.class.getSimpleName();
    String MyAppString = "#SpotifyStreamer";
    String reviewStr;
    String trailer_string;
    public MovieObject movieRecieved;
    int cnt=0;
    TextView reviewView;
    public DetailActivityFragment()
    {
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent intent=getActivity().getIntent();
        movieRecieved=intent.getParcelableExtra("data");

        // Uncomment to share key value correctly...
//        try {
//
//           trailer_string=new TrailerFetcher().execute(movieRecieved.id).get()[0].getKey();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
        new DataFetcher().execute(movieRecieved.id);

        // Uptil Here..



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.fragment_detail, menu);
        MenuItem item=menu.findItem(R.id.item_action_share);
        mShareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(getDefaultShareIntent());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent=getActivity().getIntent();
//l4        Bundle bundle= intent.getExtras();
        String API_KEY="api_key";
   //     trailerAdapter=new TrailerAdapter(getActivity(),R.layout.list_item_trailer,R.id.trailer_image,trailerKeyList);
        final FloatingActionButton updateFavouriteButton = (FloatingActionButton) rootView.findViewById(R.id.favbtn);

        // Trailer Object Populate the Trailer ListView
        trailerObjects= new TrailerObject[]{new TrailerObject(), new TrailerObject()};

        List<TrailerObject> listmovie=new ArrayList<TrailerObject>(Arrays.asList(trailerObjects));
        myTrailerAdapter=new TrailerAdapter(getActivity(),R.layout.trailer_item_imageview,R.id.gridview_movies_imageview,listmovie);
// Added Temporary Button

//        fBtn= (Button) rootView.findViewById(R.id.favouriteBtn);

//l4        MovieObject movieRecieved= (MovieObject) bundle.getSerializable("MovieObjectSent");
            final MovieObject movieRecieved=intent.getParcelableExtra("data");
        ImageView movieBackdrop= (ImageView) rootView.findViewById(R.id.movie_backdrop);
        final ImageView moviePoster= (ImageView) rootView.findViewById(R.id.movie_poster);
        TextView movie_vote_average= (TextView) rootView.findViewById(R.id.movie_vote_average);
        final TextView movie_release= (TextView) rootView.findViewById(R.id.movie_release);
        Picasso.with(getContext()).load(baseUrlImage + movieRecieved.backdrop_path).placeholder(R.mipmap.img_placeholder).into(movieBackdrop);
        Picasso.with(getContext()).load(baseUrlImage + movieRecieved.poster_path).placeholder(R.mipmap.img_placeholder).into(moviePoster);
        getActivity().setTitle(movieRecieved.title);


        // Uncomment to share key value correctly...
        try {

           trailer_string=new TrailerFetcher().execute(movieRecieved.id).get()[0].getKey();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String url="content://com.movies.app.popularmovies.app/movie";

        Uri fetchUri=Uri.parse(url);
        Cursor findQuery=getContext().getContentResolver().query(fetchUri, null, "movie_id=" + movieRecieved.id, null, null);
        if(findQuery.moveToFirst()) {
            isFavourite=true;
        }
        if(isFavourite==true)
        {
            updateFavouriteButton.setImageResource(R.mipmap.like);

        }
        else
        {
            updateFavouriteButton.setImageResource(R.mipmap.dislike);

        }



//        catch (TimeoutException e) {
//            e.printStackTrace();
//        }

//        new DataFetcher().execute(movieRecieved.id);

        // Uptil Here..

//        Log.v("MAIN STATIC KEY",static_key);
       // new TrailerFetcher().execute(movieRecieved.id);
   //     new TrailerFetcher().execute(movieRecieved.id);


// Added fab on CLick Listener

        updateFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "FAB Clicked...", Toast.LENGTH_SHORT).show();
// Tries Code from Here...
                String url="content://com.movies.app.popularmovies.app/movie";

                Uri fetchUri=Uri.parse(url);

//                Cursor findQuery=getContext().getContentResolver().query(fetchUri, null, "movie_id=" + movieRecieved.id, null, null);
//                if(findQuery.moveToFirst()) {
//                    isFavourite=true;
//                    Log.v("MOVIE AVAILABLE VERBOES", "MOVIE IS ADDED ALREADY "+isFavourite);
//
//                }
                if(isFavourite==false) {

                    ContentValues values = new ContentValues();
                    values.put(MovieContract.MovieEntry.COLUMN_TITLE, movieRecieved.title);
                    values.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, movieRecieved.overview);
                    values.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movieRecieved.release_date);
                    values.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, movieRecieved.vote_average);
                    values.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, movieRecieved.poster_path);
                    values.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, movieRecieved.backdrop_path);
                    values.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movieRecieved.id);
                    Uri uri = getContext().getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);

                    Toast.makeText(getContext(), "Added TO Favourites...", Toast.LENGTH_SHORT).show();
                    updateFavouriteButton.setImageResource(R.mipmap.like);
                    isFavourite=true;
                    String result = "";
                    Cursor c = getContext().getContentResolver().query(fetchUri, null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            {
                                result = "S. NUMBER           : " + c.getString(c.getColumnIndex(MovieContract.MovieEntry._ID))
                                        + "\nMOVIE ID             : " + c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_ID))
                                        + "\nMOVIE TITLE          : " + c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE));
//                                    +"\nMOVIE OVERVIEW       : "+c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_OVERVIEW))
//                                    +"\nMOVIE RELEASE DATE   : "+c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE))
//                                    +"\nMOVIE VOTE           : "+c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE))
//                                    +"\nMOVIE POSTER         : "+c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_PATH))
                                //                        +"\nMOVIE BACKDROP       : "+c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH)).toString();

                                Log.v("RESULT_QUERY VERBOSE", result);
                            }
                        } while (c.moveToNext());
                    }
// Uptil Here...
                }
                else
                {
                    Toast.makeText(getContext(),"Movie Successfully Removed...",Toast.LENGTH_SHORT).show();
                    int delID=getContext().getContentResolver().delete(fetchUri,"movie_id="+movieRecieved.id,null);
                    updateFavouriteButton.setImageResource(R.mipmap.dislike);
                    isFavourite=false;
                }

            }
        });

        //Uptil Here

        GridView trailerview= (GridView) rootView.findViewById(R.id.gridview_trailersview);
        trailerview.setAdapter(myTrailerAdapter);

//        keyObject=myTrailerAdapter.getItem(0);
//        share_key=keyObject.key;

        trailerview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TrailerObject trailerObject = myTrailerAdapter.getItem(position);
                String key = trailerObject.getKey();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key)));
            }
        });

        TextView overview= (TextView) rootView.findViewById(R.id.movie_overview);

        overview.setText(movieRecieved.overview);
//        TrailerObject obj=myTrailerAdapter.getItem(1);
//        Log.v(LOG_TAG,obj.getKey());
        movie_release.setText(movieRecieved.release_date);
        movie_vote_average.setText(movieRecieved.vote_average);
        reviewView= (TextView) rootView.findViewById(R.id.movie_review);
        reviewView.setText(reviewStr);


//        fBtn.setOnClickListener(new View.OnClickListener() {
//            boolean didItWork=true;
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"Added",Toast.LENGTH_SHORT).show();
//                try {
//
//                    String entTitle=movieRecieved.title;
//                String entOverview=movieRecieved.overview;
//                String entRelease=movieRecieved.release_date;
//                MyMovieClass entry=new MyMovieClass(view.getContext());
//                    entry.open();
//                    // Entry for Movie
//                    entry.createEntry(entTitle, entOverview, entRelease);
//                    // Entry for Review
////                    entry.createReviewEntry(movieRecieved.id, reviewView.getText().toString());
////                    entry.createTrailerEntry(movieRecieved.id);
//                    // Entry for Trailer
//                    entry.close();
//
//                }
//                catch (Exception e) {
//                    didItWork=false;
//                    e.printStackTrace();
//                }
//                finally {
//                    if(didItWork)
//                    {
//                        Dialog dialog=new Dialog(getContext());
//                        dialog.setTitle("Success...");
//                        TextView tv=new TextView(getContext());
//                        tv.setText("Movie is Added !");
//                        dialog.setContentView(tv);
//                        dialog.show();
//
//
//                    }
//                }
//
//
//            }
//        });

        return rootView;


    }

//    private String findMovieKey(TrailerAdapter myTrailerAdapter) {
//
//        String key= String.valueOf(myTrailerAdapter.getCount());
//
//        return key;
//    }



//    public void fabClicked(View v)
//    {
//        Toast.makeText(this.getContext(),"Button Clicked",Toast.LENGTH_SHORT).show();
//    }


    public class DataFetcher extends AsyncTask<String,Void,String>
    {
        ProgressDialog dialog=new ProgressDialog(getActivity());
        String LOG_TAG= DataFetcher.class.getSimpleName();
        String API_KEY="api_key";
        String jsonStr;
        int cnt=1;
        String reviewStr="";
        String reviewBaseUrl="https://api.themoviedb.org/3/movie/";
        String movie_id;
        BufferedReader reader=null;
        // String baseUrl="https://api.themoviedb.org/3/movie/286217/";
        HttpURLConnection urlConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             dialog.setMessage("Loading...");
            dialog.show();
            dialog.setMessage("Loading...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(String str)
        {

            reviewView.setText(str);
//            new TrailerFetcher().execute(movie_id);

            if(dialog.isShowing())
                dialog.dismiss();
        }

        @Override
        protected String doInBackground(String...strings) {
            Uri buildUrl=Uri.parse(reviewBaseUrl+strings[0]+"/reviews").buildUpon().appendQueryParameter(API_KEY, getString(R.string.api_key)).build();
//            Log.v("DATAFETCHER URI",buildUrl.toString());
            try {
                movie_id=strings[0];
//                URL url=new URL("https://api.themoviedb.org/3/movie/"+strings[0]+"/reviews?api_key=69323240f26aaa3f0ed513e2fd344a5f");
                    URL url=new URL(buildUrl.toString());
                Log.v(LOG_TAG+"VERBOSE",url.toString());
                urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream=urlConnection.getInputStream();
                StringBuffer buffer=new StringBuffer();
                if(inputStream==null)
                    jsonStr=null;

                reader=new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line=reader.readLine())!=null)
                {
                    buffer.append(line+"\n");
                }
                if(buffer.length()==0)
                {
                    jsonStr=null;
                }
                jsonStr=buffer.toString();
                JSONObject jsonObject=new JSONObject(jsonStr);
                JSONArray jsonArray =jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject review = jsonArray.getJSONObject(i);
                    //JSONObject authorObject=review.getJSONObject("author");
                    String authorName=review.getString("author");
                    Log.v("\nauthor JSON",authorName);
                    //JSONObject contentObject=review.getJSONObject("content");
                    String content=review.getString("content").toString();
                    Log.v("\ncontent JSON ",authorName);
                    reviewStr=reviewStr+"\n \t\t:: REVIEW "+cnt+" ::\n\n"+authorName+"\n"+content+"\n";
                    cnt++;
                }
                Log.v(LOG_TAG,reviewStr);



            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return reviewStr;



        }
    }

    public class TrailerFetcher extends AsyncTask<String, Void, TrailerObject[]> {
        ProgressDialog dialog=new ProgressDialog(getActivity());

        String LOG_TAG = TrailerFetcher.class.getSimpleName();
        String API_KEY = "api_key";
        String jsonStr;
        public String trailerBaseUrl="https://api.themoviedb.org/3/movie/";
        String myTrailer = "";
        //        String []trailer_key=null;
        BufferedReader reader = null;
        // String baseUrl="https://api.themoviedb.org/3/movie/286217/";
        HttpURLConnection urlConnection;

        @Override
        protected void onPostExecute(TrailerObject[] str) {
            if(dialog.isShowing()==true)
            {
                dialog.dismiss();
            }


//            Log.v("KEY VERBOSE", str[0].key);
            if (str != null)
                myTrailerAdapter.clear();
            myTrailerAdapter.addAll(str);
//            static_key=generateKey(str[0]);
//            Log.v("STATIC KEY : ",static_key);

            myTrailerAdapter.notifyDataSetChanged();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading...");
            dialog.show();
        }

        @Override
        protected TrailerObject[] doInBackground(String... strings) {
            try {
                Uri buildUrl=Uri.parse(trailerBaseUrl+strings[0]+"/videos").buildUpon().appendQueryParameter(API_KEY, getString(R.string.api_key)).build();
 //               URL url = new URL("https://api.themoviedb.org/3/movie/"+strings[0]+"/videos?api_key=69323240f26aaa3f0ed513e2fd344a5f");
                URL url=new URL(buildUrl.toString());
                Log.v(LOG_TAG + "VERBOSE", url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null)
                    jsonStr = null;

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    jsonStr = null;
                }
                jsonStr = buffer.toString();
                JSONObject trailerObject = new JSONObject(jsonStr);
                Log.v("VERBOSE",trailerObject.toString());
                JSONArray jsonArray = trailerObject.getJSONArray("results");
                trailerObjects = new TrailerObject[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    trailerObjects[i] = new TrailerObject();
                    JSONObject jsonArrayJSONObject = jsonArray.getJSONObject(i);
                    //JSONObject authorObject=review.getJSONObject("author");
                    trailerObjects[i].id = jsonArrayJSONObject.getString("id");
                    Log.v("ID VERBOSE",jsonArrayJSONObject.getString("key"));
                    //String key=jsonArrayJSONObject.getString("key");
                    trailerObjects[i].key = jsonArrayJSONObject.getString("key");
                    trailerObjects[i].name = jsonArrayJSONObject.getString("name");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return trailerObjects;
        }
    }

     Intent getDefaultShareIntent()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

    //    TrailerObject obj=myTrailerAdapter.getItem(0);

    //    intent.putExtra(Intent.EXTRA_TEXT,baseUrl+share_key+"\n"+hashApp);
          intent.putExtra(Intent.EXTRA_TEXT,baseUrl+trailer_string+"\n"+hashApp);
        return intent;
    }

    static String generateKey(TrailerObject j)
    {
        return j.getKey();
    }
}

