package abhirojlearner.popularmovies10;

/**
 * Created by APnaturals on 5/5/2016.
 */
public class MovieData {

    String mTitle;
    String mRelDate;
    String vote_avg;
    String plotsynop;
    String imgPath;
    public void setmmTitle(String title)
    {
        mTitle=title;
    }

    public String getmmTitle()
    {
        return mTitle;
    }

    public void setmRelDate(String reldate)
    {
        mRelDate=reldate;
    }

    public String getmRelDate()
    {
        return  mRelDate;
    }

    public void setVoteAvg(String voteavg)
    {
        vote_avg=voteavg;
    }

    public String getVoteAvg()
    {
        return vote_avg;
    }

    public void setPlotsynop(String psn)
    {
        plotsynop=psn;
    }

    public String getPlotsynop()
    {
        return plotsynop;
    }

    public  void setImgPath(String imgpath)
    {
        imgPath=imgpath;
    }

    public  String getImgPath()
    {
        return imgPath;
    }

}
