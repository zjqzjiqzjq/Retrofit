package cn.edu.gdmec.android.retrofit.weather.model;

/**
 * Created by apple on 18/6/12.
 */

public interface IWeatherModel {
    void loadWeather(Integer citykey, IWeatherLoadListener iWeatherLoadListener);
}
