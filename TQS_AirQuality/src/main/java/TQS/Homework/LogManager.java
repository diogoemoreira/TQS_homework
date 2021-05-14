package TQS.Homework;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LogManager {
    private Map<String, ArrayList<String>> logs = new LinkedHashMap<String, ArrayList<String>>();

    private int countOfReq = 0;

    LogManager(){ }

    public void addLog(String location,String log){
        countOfReq++;
        if(!logs.containsKey(location)){
            logs.put(location, new ArrayList<String>());
        }
        logs.get(location).add(log);
    }

    public int getCountOfReq() {
        return countOfReq;
    }
    
}
