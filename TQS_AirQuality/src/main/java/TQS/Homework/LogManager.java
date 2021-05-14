package TQS.Homework;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LogManager {
    private Map<String, CityAQ> logs = new LinkedHashMap<String, CityAQ>();
    private Map<String, MutableInt> cityTTL = new LinkedHashMap<String, MutableInt>();
    private int countOfReq = 0;
    private int ttl = 5;

    LogManager(){ }

    public void addLog(String location,CityAQ city){
        if(!logs.containsKey(location)){
            logs.put(location, city);
            cityTTL.put(location, new MutableInt());
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
        for(String loc: cityTTL.keySet()){
            cityTTL.get(loc).increment();
            if(cityTTL.get(loc).get()>this.ttl){
                cityTTL.remove(loc);
                logs.remove(loc);
            }
        }
        this.countOfReq++;
    }

    public Integer getCityTTL(String city) {
        return cityTTL.get(city).get();
    }

    public Integer getTTL() {
        return this.ttl;
    }

    class MutableInt {
        int value = 0; //start at 0 since we're counting
        public void increment () { ++value;      }
        public int  get ()       { return value; }
    }
    
}
