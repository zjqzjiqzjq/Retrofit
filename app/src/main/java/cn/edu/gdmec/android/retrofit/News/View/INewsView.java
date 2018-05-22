package cn.edu.gdmec.android.retrofit.News.View;

import cn.edu.gdmec.android.retrofit.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface INewsView {
    void hideDialog();
    void showNews(NewsBean newsBean);
    void showErrorMsg(String error);
    void showDialog();
}
