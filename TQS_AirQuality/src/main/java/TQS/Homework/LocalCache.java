package TQS.Homework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.stereotype.Service;

@Service
public class LocalCache {
    private TtlHashMap<String, CityAQ> logs = new TtlHashMap<>(TimeUnit.SECONDS, 20);
    private int countOfReq = 0;

    LocalCache(){ }

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
