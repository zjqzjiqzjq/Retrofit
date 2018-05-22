package cn.edu.gdmec.android.retrofit.Movie.View;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieView {
    void hideDialog();
    void showNews(MovieBean movieBean);
    void showErrorMsg(String error);
    void showDialog();
}
