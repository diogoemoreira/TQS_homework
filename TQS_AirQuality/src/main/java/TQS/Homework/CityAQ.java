package TQS.Homework;

import java.util.Arrays;
import java.util.List;

public class CityAQ {
    private String name;
    private int aqi;
    private Float lat;
    private Float lon;
    private String time;

    //avg,min,max,day and repeat
    private List<String> o3;  //{"avg":34, "min":28, "max":39,"day":"2021-05-13","avg":33,...}
    private List<String> pm10;//from here ^             to here                 ^ is one day of forecast
    private List<String> pm25;
    private List<String> uvi;

    public CityAQ(){
    }

    public String getName() {
        return name;
    }

    public Integer getAqi() {
        return aqi;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }

    public String getTime() {
        return time;
    }

    public List<String> getO3() {
        return o3;
    }

    public List<String> getPm10() {
        return pm10;
    }

    public List<String> getPm25() {
        return pm25;
    }

    public List<String> getUvi() {
        return uvi;
    }

    public int getMinForecast() {
        //used to know which has the min number of days between o3,pm10,pm25,uvi
        int minForecast = 999;
        if (minForecast > o3.size())
            minForecast = o3.size();
        if (minForecast > pm10.size())
            minForecast = pm10.size();
        if (minForecast > pm25.size())
            minForecast = pm25.size();
        if (minForecast > uvi.size())
            minForecast = uvi.size();

        return minForecast /4;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public void setLatLon(String latlon) {
        String lat_lon= latlon.substring(1, latlon.length()-1);
        String[] ll = lat_lon.split(",");
        this.lat = Float.parseFloat(ll[0]);
        this.lon= Float.parseFloat(ll[1]);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setO3(String o3) {
        this.o3 =  Arrays.asList(o3.substring(1,o3.length()-1).replaceAll("[{}]", "").split(","));
    }

    public void setPm10(String pm10) {
        this.pm10 =  Arrays.asList(pm10.substring(1,pm10.length()-1).replaceAll("[{}]", "").split(","));
    }

    public void setPm25(String pm25) {
        this.pm25 =  Arrays.asList(pm25.substring(1,pm25.length()-1).replaceAll("[{}]", "").split(","));
    }

    public void setUvi(String uvi) {
        this.uvi =  Arrays.asList(uvi.substring(1,uvi.length()-1).replaceAll("[{}]", "").split(","));
    }

    @Override
    public String toString() {
        return "CityAQ:{aqi:"+ aqi +", lat:" + lat + ", lon:" + lon + ", name:" + name + ", o3:" + o3 + ", pm10:" + pm10
                + ", pm25:" + pm25 + ", time:" + time + ", uvi:" + uvi + "}";
    }

}
