package TQS.Homework.Controllers;


import java.util.logging.Logger;

import TQS.Homework.CityAQ;
import TQS.Homework.CityAqService;
import TQS.Homework.LocalCache;
import TQS.Homework.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AirQualityController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    LocalCache localCache;

    @Autowired
    CityAqService service;

    private static final Logger logger = Logging.getLogger();

    @GetMapping("/")
    public ModelAndView home(){
        Logging.writeToFile();
        ModelAndView mv=new ModelAndView("index");
        //no forecast
        mv.addObject("cityName", "No City Found");
        return mv;
    }

    @GetMapping("/search/{city}")
    public ModelAndView getCityInfo(@PathVariable(value = "city") String city) throws ParseException {
        //call service method to get the new city

        ModelAndView mv = new ModelAndView("index");
        CityAQ cityaq;

        if(localCache.hasCity(city)) {
            cityaq = localCache.retrieveCity(city);
            logger.info("Used local-cache for city aq: "+ city);
        }
        else {
            if(!service.searchCity(city)) {
                logger.info("FAILED TO GET CITY "+city);

                localCache.missed();
                localCache.addCount(); //increments number of requests
                logger.info("Number of requests: "+localCache.getCountOfReq());
                logger.info("Number of Hits: "+localCache.getHits());
                logger.info("Number of Misses: "+localCache.getMisses());

                //no forecast
                mv.addObject("cityName", "No City Found");
                return mv;
            }
            cityaq = service.getCity();
            localCache.addCache(city,cityaq);
        }

        logger.info(cityaq.toString());

        //info of the city of the forecast
        mv.addObject("cityName", cityaq.getName());
        mv.addObject("cityAqi", cityaq.getAqi());
        mv.addObject("cityLat", cityaq.getLat());
        mv.addObject("cityLon", cityaq.getLon());
        mv.addObject("cityTime", cityaq.getTime());

        //forecast information
        int minForecastDays = cityaq.getMinForecast();

        mv.addObject("cityDate", sdf.format(sdf.parse(cityaq.getTime())));
        mv.addObject("cityO3", cityaq.getO3().get(0).split(":")[1]);
        mv.addObject("cityPm10", cityaq.getPm10().get(0).split(":")[1]);
        mv.addObject("cityPm25", cityaq.getPm25().get(0).split(":")[1]);
        mv.addObject("cityUvi", cityaq.getUvi().get(0).split(":")[1]);

        Map<String, String[]> forecast = new HashMap<>();

        logger.info("Min Forecast Days: "+minForecastDays);

        for(int i=1; i<minForecastDays; i++){
            String day = cityaq.getO3().get(3+i*4).split(":")[1].replace("\"","");
            forecast.put( (String) day, new String[]{
                    cityaq.getO3().get(i*4).split(":")[1],
                    cityaq.getPm10().get(i*4).split(":")[1],
                    cityaq.getPm25().get(i*4).split(":")[1],
                    cityaq.getUvi().get(i*4).split(":")[1],
            });
        }

        mv.addObject("forecastStats", forecast);

        localCache.addCount(); //increments number of requests
        logger.info("Number of requests: "+localCache.getCountOfReq());
        logger.info("Number of Hits: "+localCache.getHits());
        logger.info("Number of Misses: "+localCache.getMisses());
        //only show forecast info of minForecastDays (e.g. 3 days ahead)
        return mv;
    }

}
