package TQS.Homework;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LocalCache {
    private int ttl=20;
    private static TtlHashMap<String, CityAQ> logs;
    private int countOfReq = 0;
    private int hits = 0;
    private int misses = 0;

    LocalCache(){
        logs = new TtlHashMap<>(TimeUnit.SECONDS, this.ttl);
    }

    LocalCache(int newttl){
        logs = new TtlHashMap<>(TimeUnit.SECONDS, newttl);
    }

    public void addCache(String location, CityAQ city){
        if(!logs.containsKey(location)){
            hits++;
            logs.put(location, city);
        }
    }

    public boolean hasCity(String location){
        return logs.containsKey(location);
    }

    public CityAQ retrieveCity(String location){
        hits++;
        return logs.get(location);
    }

    public int getCountOfReq() {
        return countOfReq;
    }

    public void addCount(){
        this.countOfReq++;
    }

    public int getMisses(){
        return this.misses;
    }
    public void missed(){
        this.misses++;
    }

    public int getHits(){
        return this.hits;
    }
    
}
