package cn.edu.gdmec.android.retrofit.Movie.Model;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IOnLoadListener {
    void successmov(MovieBean movieBean);
    void fail(Throwable throwable);
    void loadMoreSuccessmov(MovieBean movieBean);
}
