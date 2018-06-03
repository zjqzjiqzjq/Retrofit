package cn.edu.gdmec.android.retrofit.News.Model;

import cn.edu.gdmec.android.retrofit.Bean.NewsBean;
import cn.edu.gdmec.android.retrofit.Http.Api;
import cn.edu.gdmec.android.retrofit.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 18/5/22.
 */

public class NewsModel implements INewsModel {
    @Override
    public void loadNews(final String hostType,final int startPage,final String id,final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.NEWS_HOST);
        //enqueue异步请求
        retrofitHelper.getNews(hostType,id,startPage).enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                if (response.isSuccessful()){
                    iOnLoadListener.success(response.body());
                }else {
                    iOnLoadListener.fail("");
                }
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                iOnLoadListener.fail(t.toString());
            }
        });
    }
}
