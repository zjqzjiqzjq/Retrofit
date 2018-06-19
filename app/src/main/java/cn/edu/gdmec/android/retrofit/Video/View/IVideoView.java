package cn.edu.gdmec.android.retrofit.Video.View;

import java.util.List;

import cn.edu.gdmec.android.retrofit.Bean.TodayContentBean;

/**
 * Created by apple on 18/6/12.
 */

public interface IVideoView {
    void hideDialog();
    void showVideo(List<TodayContentBean> todayContentBeans,List<String> videoList);
    void showMoreVideo(List<TodayContentBean> todayContentBeans, List<String> videoList);
    void showErrorMsg(Throwable throwable);
    void showDialog();
}
