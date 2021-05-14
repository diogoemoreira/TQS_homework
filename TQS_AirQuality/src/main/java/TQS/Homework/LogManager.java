package TQS.Homework;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LogManager {
    private Map<String, CityAQ> logs = new LinkedHashMap<String, CityAQ>();
    private int countOfReq = 0;

    LogManager(){ }

    public void addLog(String location,CityAQ city){
        if(!logs.containsKey(location)){
            logs.put(location, city);
        }
    }

    public boolean hasCity(String location){
        return logs.containsKey(location);
    }

    public CityAQ retrieveCity(String location){
        return logs.get(location);
    }

    public int getCountOfReq() {
        return countOfReq;
    }

    public void addCount(){
        this.countOfReq++;
    }
    
}
