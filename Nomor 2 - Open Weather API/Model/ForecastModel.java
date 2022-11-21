package Model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class ForecastModel {
    @SerializedName("main")
    private WeatherModel weatherModel;

    @SerializedName("dt_txt")
    private String date;

    public WeatherModel getWeatherData(){
        return weatherModel;
    }

    public String getDate(){
        return date;
    }
}
