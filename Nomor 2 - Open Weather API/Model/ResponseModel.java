package Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseModel {
    @SerializedName("list")
    private List<ForecastModel> forecastModels;

    public List<ForecastModel> getForecastModels(){
        return forecastModels;
    }
}
