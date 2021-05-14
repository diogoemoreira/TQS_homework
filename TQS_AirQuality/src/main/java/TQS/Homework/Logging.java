package TQS.Homework;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


//Adapted from https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
public class Logging {
    private static final Logger logger = Logger.getLogger("MyLog");
    private static FileHandler fh;

    public static void writeToFile(){
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("Logs.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger(){
        return logger;
    }
}
