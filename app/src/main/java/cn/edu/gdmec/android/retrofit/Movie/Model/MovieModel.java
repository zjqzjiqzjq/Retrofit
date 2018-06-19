package cn.edu.gdmec.android.retrofit.Movie.Model;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Http.Api;
import cn.edu.gdmec.android.retrofit.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class MovieModel implements IMovieModel {
    @Override
    public void loadMovie(final String type,final int start,final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.Movie_HOST);
        retrofitHelper.getMovie(type,start).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        /*if (start != 0 && start <= 40){
                            iOnLoadListener.loadMoreSuccessmov(movieBean);
                        }else {
                            iOnLoadListener.successmov(movieBean);
                        }*/

                        iOnLoadListener.successmov(movieBean);
                    }
                });
    }

}
