package cn.edu.gdmec.android.retrofit.Movie.Model;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Http.Api;
import cn.edu.gdmec.android.retrofit.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 18/5/22.
 */

public class MovieModel implements IMovieModel {
    @Override
    public void loadMovie(final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.Movie_HOST);
        retrofitHelper.getMovie().enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                if (response.isSuccessful()){
                    iOnLoadListener.success(response.body());
                }else {
                    iOnLoadListener.fail("");
                }

            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {
                iOnLoadListener.fail(t.toString());
            }
        });
    }
}
