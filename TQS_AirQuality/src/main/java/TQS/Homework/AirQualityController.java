package TQS.Homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AirQualityController {
    @Autowired
    LogManager logs;

    @Autowired
    CityAqService service;

    private ModelAndView mv = new ModelAndView("CityAirQuality");

    @GetMapping("/")
    public ModelAndView home(){
        mv.addObject("cityName", service.getCity().getName());
        return mv;
    }

    @GetMapping("/search/{city}")
    public String getCityInfo(@PathVariable(value = "id") String city) {  
        //call service method to get the new city  
        if(!service.searchCity(city))
            return "/";
        //info of the city of the forecast
        mv.addObject("cityName", service.getCity().getName());
        mv.addObject("lat", service.getCity().getLat());
        mv.addObject("lon", service.getCity().getLon());
        mv.addObject("time", service.getCity().getTime());
        //forecast information
        int minForecastDays = service.getCity().getMinForecast();
        String avg,min,max,day; //save the forecast data

        //only show forecast info of minForecastDays (e.g. 3 days ahead)
        

        return "";
    }

}
