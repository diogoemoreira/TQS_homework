package TQS.Homework.Controllers;

import TQS.Homework.CityAQ;
import TQS.Homework.CityAqService;
import TQS.Homework.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirQualityRestController {

    @Autowired
    LocalCache localCache;

    @Autowired
    CityAqService service;

    @GetMapping("/stats")
    public ResponseEntity<Object> getStatistics(){
       Map<String, Integer> retorno = new HashMap<>();

        retorno.put("No_Requests", localCache.getCountOfReq());
        retorno.put("Misses", localCache.getMisses());
        retorno.put("Hits", localCache.getHits());

        return new ResponseEntity<>(retorno,HttpStatus.OK);
    }

    @GetMapping("/search/{city}")
    public ResponseEntity<Object> getCityInfo(@PathVariable(value = "city") String city) {
        //call service method to get the new city
        CityAQ cityaq;

        if (localCache.hasCity(city)) {
            cityaq = localCache.retrieveCity(city);
        } else {
            if (!service.searchCity(city)) {
                localCache.missed();
                localCache.addCount(); //increments number of requests
                return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
            }
            cityaq = service.getCity();
            localCache.addCache(city, cityaq);
        }

        Map<String, Object> retorno = new HashMap<>();
        retorno.put("name", cityaq.getName());
        retorno.put("time", cityaq.getTime());
        retorno.put("aqi", cityaq.getAqi());
        retorno.put("lat", cityaq.getLat());
        retorno.put("lon", cityaq.getLon());
        retorno.put("o3", cityaq.getO3());
        retorno.put("pm10", cityaq.getPm10());
        retorno.put("pm25", cityaq.getPm25());
        retorno.put("uvi", cityaq.getUvi());

        return new ResponseEntity<>(retorno,HttpStatus.OK);
    }
}
