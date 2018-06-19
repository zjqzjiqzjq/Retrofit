package cn.edu.gdmec.android.retrofit.Movie.Presenter;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Http.Api;
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
    public void loadMovie(String type,int start) {
        if (start == 0) {
            iMovieView.showDialog();
        }
        iMovieModel.loadMovie(type,start,this);
    }

    @Override
    public void successmov(MovieBean data) {
        iMovieView.hideDialog();
        if (data.getStart()==0){
            iMovieView.showMovie(data);
        }else {
            if (data.getSubjects().size()==0){
                iMovieView.showErrorMsg("没有更多了......");
            }else{
                iMovieView.showMoreMovie(data);
            }
        }
    }

    @Override
    public void fail(Throwable throwable) {
        iMovieView.hideDialog();
        iMovieView.showErrorMsg(throwable.getMessage());
    }

    @Override
    public void loadMoreSuccessmov(MovieBean movieBean) {

    }


}
