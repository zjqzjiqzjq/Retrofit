package cn.edu.gdmec.android.retrofit.weather.model;


import cn.edu.gdmec.android.retrofit.Bean.WeatherBean;

/**
 * Created by apple on 18/6/12.
 */

public interface IWeatherLoadListener {
    void success(WeatherBean weatherBean);
    void fail(Throwable throwable);
}
