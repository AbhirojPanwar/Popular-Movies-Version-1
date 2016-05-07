package abhirojlearner.popularmovies10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Display_Movie_Detail extends ActionBarActivity {

    public final String IMGID="IMGID";
    public final String mTitle="mTitle";
    public final String mPlot="mPlot";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__movie__detail);


        Intent i=getIntent();
        String imageId="http://image.tmdb.org/t/p/w185"+i.getStringExtra(IMGID);
        String mtitle=i.getStringExtra(mTitle);
        String mPlota=i.getStringExtra(mPlot);
        ImageView imgView=(ImageView) findViewById(R.id.poster);
        Picasso.with(this).load(imageId).into(imgView);
        TextView tv=(TextView) findViewById(R.id.Movie_Title);
        TextView movie_plot=(TextView) findViewById(R.id.Movie_plot);
        tv.setText(mtitle);
        movie_plot.setText(mPlota);

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
