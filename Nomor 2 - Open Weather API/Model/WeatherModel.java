package Model;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {
    @SerializedName("temp")
    String temp;

    public String getTemp(){
        return temp;
    }
}
