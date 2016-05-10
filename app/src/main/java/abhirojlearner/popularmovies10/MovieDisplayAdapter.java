package abhirojlearner.popularmovies10;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by APnaturals on 5/6/2016.
 */
public class MovieDisplayAdapter extends ArrayAdapter<MovieData> {


    public MovieDisplayAdapter(Activity context, List<MovieData> movie_object) {
        super(context, 0, movie_object);
    }

    public View getView(int position,View convertView, ViewGroup parent){
        MovieData moviedata=getItem(position);
    String url="http://image.tmdb.org/t/p/w185"+moviedata.getImgPath();

            if(convertView==null)
            {
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.diplay_img,parent,false);
            }
        ImageView mImg=(ImageView) convertView.findViewById(R.id.movie_poster);
   Picasso.with(getContext()).load(url.trim()).error(R.drawable.cupcake).into(mImg);
    return convertView;
    }
}
