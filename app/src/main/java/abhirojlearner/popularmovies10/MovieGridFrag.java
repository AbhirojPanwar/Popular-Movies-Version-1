package abhirojlearner.popularmovies10;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

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
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFrag extends Fragment {

    public MovieData[] moviedata;
    public final String IMGID="IMGID";
    public final String mTitle="mTitle";
    public final String mPlot="mPlot";
    public final String mRelDate="reldate";
    public final String mVotAvg="votavg";
    public String sorter;
    public MovieGridFrag() {
    }
    public View rootView;
    public GridView moviegrid;
    public MovieDisplayAdapter mAdapter;
    public List<MovieData> movieDataList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
setHasOptionsMenu(true);
         rootView = inflater.inflate(R.layout.fragment_main, container, false);
         if(sorter==null)
             sorter="popular";

        moviegrid=(GridView) rootView
                .findViewById(R.id.movie_display);
        FetchDataTask datain=new FetchDataTask();
      datain.execute();

              return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main_act, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_popularity) {
      if(sorter!="popular")
      {
          sorter="popular";
          FetchDataTask dataTask=new FetchDataTask();
          dataTask.execute();
      }
          return true;
        }

        if(id==R.id.action_voter)
        {
            if(sorter!="vote_average.desc")
            {
                sorter="top_rated";
                FetchDataTask dataTask=new FetchDataTask();
                dataTask.execute();

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  class FetchDataTask extends AsyncTask<Void,Void,Void>
    {

        final String TAG=FetchDataTask.class.getSimpleName();
        @Override
        protected Void doInBackground(Void... params) {

            HttpURLConnection connect=null;
            BufferedReader reader=null;
            String moviejson=null;
            try{

                URL url=new URL("https://api.themoviedb.org/3/movie/"+sorter+"?api_key=98ee66ec333fc7b3902c1f87cbfea17a".trim());
                connect=(HttpURLConnection)url.openConnection();
                connect.setRequestMethod("GET");
                connect.connect();

                InputStream inputStream=connect.getInputStream();
                StringBuffer buffer=new StringBuffer();
                if(inputStream==null) return null;
                reader=new BufferedReader(new InputStreamReader(inputStream));
                String line=null;
                while((line=reader.readLine())!=null)
                {
                    buffer.append(line+"\n");
                }

                if(buffer.length()==0) return null;

                moviejson=buffer.toString();
                moviedata= FillMovieData(moviejson);
                if(moviedata.length==0) return null;



            } catch (MalformedURLException e) {
                Log.e(TAG,"Malformed URL",e);
                return null;
            } catch (IOException e) {
                Log.e(TAG,"IOError",e);
                return null;
            }

            if(connect!=null)
            {
                connect.disconnect();
            }
            if(reader!=null)
            {
                try{
                    reader.close();
                }
                catch (Exception e)
                {
                    Log.e(TAG,"Closing Stream Error",e);
                    return null;
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            movieDataList= Arrays.asList(moviedata);
            mAdapter=new MovieDisplayAdapter(getActivity(),movieDataList);

            mAdapter.notifyDataSetChanged();
            moviegrid.setAdapter(mAdapter);
            moviegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i=new Intent(getActivity(),Display_movie_detail.class);

                    i.putExtra(IMGID,moviedata[position].getBackgroungUrl());
                    i.putExtra(mTitle,moviedata[position].getmmTitle());
                    i.putExtra(mPlot,moviedata[position].getPlotsynop());
                    i.putExtra(mRelDate,moviedata[position].getmRelDate());
                    i.putExtra(mVotAvg,moviedata[position].getVoteAvg());
                    startActivity(i);
                }
            });


        }


        public MovieData[] FillMovieData(String moviejson)
        {
            MovieData movies[]=null;
            try {
                JSONObject jsonRootObject=new JSONObject(moviejson);
                JSONArray result=jsonRootObject.optJSONArray("results");
                movies=new MovieData[result.length()];
                for (int i=0;i<movies.length;i++)
                {
                    MovieData movie=new MovieData();
                    JSONObject posres=result.getJSONObject(i);

                    String posterpath=posres.optString("poster_path");
                    String plosynp=posres.optString("overview");
                    String reldate=posres.optString("release_date");
                    String title=posres.optString("title");
                    String voteavg=posres.optString("vote_average");
                    String burl=posres.optString("backdrop_path");
                    movie.setBackgroungUrl(burl);
                    movie.setImgPath(posterpath);
                    movie.setmmTitle(title);
                    movie.setmRelDate(reldate);
                    movie.setVoteAvg(voteavg);
                    movie.setPlotsynop(plosynp);
                    movies[i]=movie;
                }

            } catch (JSONException e) {
                Log.e(TAG,"Error in JSOn Parsing",e);
            }
            return movies;
        }


    }

}