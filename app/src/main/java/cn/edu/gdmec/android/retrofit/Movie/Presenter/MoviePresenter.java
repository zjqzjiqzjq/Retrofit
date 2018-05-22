package cn.edu.gdmec.android.retrofit.Movie.Presenter;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Movie.Model.IMovieModel;
import cn.edu.gdmec.android.retrofit.Movie.Model.IOnLoadListener;
import cn.edu.gdmec.android.retrofit.Movie.Model.MovieModel;
import cn.edu.gdmec.android.retrofit.Movie.View.IMovieView;

/**
 * Created by apple on 18/5/22.
 */

public class MoviePresenter implements IMoviePresenter,IOnLoadListener{
    private IMovieModel iMovieModel;
    private IMovieView iMovieView;

    public MoviePresenter(IMovieView iMovieView) {
        this.iMovieView = iMovieView;
        this.iMovieModel = new MovieModel();
    }

    @Override
    public void loadNews() {
        iMovieView.showDialog();
        iMovieModel.loadMovie(this);
    }

    @Override
    public void success(MovieBean movieBean) {
        iMovieView.hideDialog();
        if (movieBean != null){
            iMovieView.showNews(movieBean);
        }
    }

    @Override
    public void fail(String error) {
        iMovieView.hideDialog();
        iMovieView.showErrorMsg(error);
    }
}
