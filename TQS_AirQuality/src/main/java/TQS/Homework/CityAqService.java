package TQS.Homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.*;

@Service
public class CityAqService {
    private CityAQ city = new CityAQ();

    @Autowired
    private RestTemplate restTemplate;

    public Boolean searchCity(String city){
        try {
            var location = restTemplate.getForObject(
                        "https://api.waqi.info/feed/"+city+"/?token=5d9753a14280b7405f6ee4228c6f8d0bf8e086e7", String.class); 
            
            JSONObject obj = new JSONObject(location);
            

            this.city.setName(obj.getJSONObject("data").getJSONObject("city").getString("name"));
            this.city.setTime(obj.getJSONObject("data").getJSONObject("time").getString("iso"));
            this.city.setLatLon(obj.getJSONObject("data").getJSONObject("city").get("geo").toString());
            this.city.setO3(obj.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").get("o3").toString());
            this.city.setPm10(obj.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").get("pm10").toString());
            this.city.setPm25(obj.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").get("pm25").toString());
            this.city.setUvi(obj.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").get("uvi").toString());

            return true; 
        } catch (Exception e) {
            return false;
        }
        
    }

    public CityAQ getCity() {
        return city;
    }
    
}
