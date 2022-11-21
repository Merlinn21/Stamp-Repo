import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import Model.ForecastModel;
import Model.ResponseModel;

public class WeatherForecast{
    public static void main(String[] args){
        // Jakarta latitude & longitude
        String city = "jakarta";

        getWeatherData(city);
    }

    public static void getWeatherData(String city){
        try {
            URL url = getUrl(city);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            InputStream response = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(response);

            ResponseModel forecastData = new Gson().fromJson(reader, ResponseModel.class);
            
            printForecast(forecastData);

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public static URL getUrl(String city){
        try {
            String API_KEY = "79e7b5fd3f68df73fca2d63475b31a7f";
            String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
            String UNITS = "metric";

            BASE_URL += "?q=" + city;
            BASE_URL += "&appid=" + API_KEY;
            BASE_URL += "&units=" + UNITS;

            return new URL(BASE_URL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static void printForecast(ResponseModel forecastData){
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        
        try {
            for(int i = 0; i < forecastData.getForecastModels().size(); i += 8){
                ForecastModel forecast = forecastData.getForecastModels().get(i);
                Date date = dateFormat.parse(forecast.getDate());
                calendar.setTime(date);

                String dateString = convertDateString(date);
                String temp = forecast.getWeatherData().getTemp() + "°C";
    
                System.out.println(dateString + ": " + temp);
            }     
            
        } catch (Exception e) {
            
        }
    }

    public static String convertDateString(Date date){
        SimpleDateFormat newFormat = new SimpleDateFormat("EEE, dd MMM yyyy");

        String dateString = "";
        dateString = newFormat.format(date);

        return dateString;
    }
}
