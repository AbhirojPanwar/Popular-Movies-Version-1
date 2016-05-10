package abhirojlearner.popularmovies10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Display_movie_detail extends ActionBarActivity {

    public final String IMGID="IMGID";
    public final String mTitle="mTitle";
    public final String mPlot="mPlot";
    public final String mRelDate="reldate";
    public final String mVotAvg="votavg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__movie__detail);


        Intent i=getIntent();
        String imageId="http://image.tmdb.org/t/p/w500"+i.getStringExtra(IMGID);
        String mPlota=i.getStringExtra(mPlot);
        String mtitle=i.getStringExtra(mTitle);
        String vot=i.getStringExtra(mVotAvg);
        String rel_date=i.getStringExtra(mRelDate);
        ImageView imgView=(ImageView) findViewById(R.id.poster);
        String s[]=rel_date.split("-");
        String day=s[2];
        String month=s[1];
        String year=s[0];
        rel_date=day+"/"+month+"/"+year;
        Picasso.with(this).load(imageId).into(imgView);
        TextView tv=(TextView) findViewById(R.id.Movie_Title);
        tv.setText(mtitle);
        TextView mPlo=(TextView) findViewById(R.id.Movie_Plot);
        mPlo.setText("Synopsis:\n"+mPlota);
        TextView mrd=(TextView) findViewById(R.id.Movie_relDate);
        mrd.setText("Released On:\n"+rel_date);
        TextView mvga=(TextView) findViewById(R.id.Movie_votAvg);
        mvga.setText("Rating:\n"+vot+"/10");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display__movie__detail, menu);
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
}
