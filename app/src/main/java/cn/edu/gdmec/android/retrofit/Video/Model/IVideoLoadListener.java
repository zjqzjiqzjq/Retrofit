package cn.edu.gdmec.android.retrofit.Video.Model;

import java.util.List;

import cn.edu.gdmec.android.retrofit.Bean.TodayContentBean;
import cn.edu.gdmec.android.retrofit.Bean.VideoUrlBean;

/**
 * Created by apple on 18/6/12.
 */

public interface IVideoLoadListener {
    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
}
