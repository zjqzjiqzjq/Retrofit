package cn.edu.gdmec.android.retrofit.Movie.Model;


/**
 * Created by apple on 18/5/22.
 */

public interface IMovieModel {
    void loadMovie(String type,int start,IOnLoadListener iOnLoadListener);
}
