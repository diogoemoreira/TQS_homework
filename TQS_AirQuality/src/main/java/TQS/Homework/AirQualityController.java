package TQS.Homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    LogManager logs;

    @Autowired
    CityAqService service;

    private static final Logger logger = LoggerFactory.getLogger(AirQualityController.class);

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    @GetMapping("/search/{city}")
    public ModelAndView getCityInfo(@PathVariable(value = "city") String city) throws ParseException {
        //call service method to get the new city
        ModelAndView mv = new ModelAndView("index");

        if(!service.searchCity(city)) {
            logger.error("FAILED TO GET CITY");
            return mv;
        }

        logger.info("City: "+service.getCity().toString());

        //info of the city of the forecast
        mv.addObject("cityName", service.getCity().getName());
        mv.addObject("cityAqi", service.getCity().getAqi());
        mv.addObject("cityLat", service.getCity().getLat());
        mv.addObject("cityLon", service.getCity().getLon());
        mv.addObject("cityTime", service.getCity().getTime());

        //forecast information
        int minForecastDays = service.getCity().getMinForecast();

        mv.addObject("cityDate", sdf.format(sdf.parse(service.getCity().getTime())));
        mv.addObject("cityO3", service.getCity().getO3().get(0).split(":")[1]);
        mv.addObject("cityPm10", service.getCity().getPm10().get(0).split(":")[1]);
        mv.addObject("cityPm25", service.getCity().getPm25().get(0).split(":")[1]);
        mv.addObject("cityUvi", service.getCity().getUvi().get(0).split(":")[1]);

        Map<String, String[]> forecast = new HashMap<>();

        logger.info("Min Forecast Days: "+minForecastDays);

        for(int i=1; i<minForecastDays; i++){
            String day = service.getCity().getO3().get(3+i*4).split(":")[1].replace("\"","");
            forecast.put( (String) day, new String[]{
                    service.getCity().getO3().get(i*4).split(":")[1],
                    service.getCity().getPm10().get(i*4).split(":")[1],
                    service.getCity().getPm25().get(i*4).split(":")[1],
                    service.getCity().getUvi().get(i*4).split(":")[1],
            });
        }

        mv.addObject("forecastStats", forecast);

        //only show forecast info of minForecastDays (e.g. 3 days ahead)
        return mv;
    }

}
