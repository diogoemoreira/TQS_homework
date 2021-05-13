package TQS.Homework;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AirQualityApplication {

    private static final Logger log = LoggerFactory.getLogger(AirQualityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AirQualityApplication.class, args);
	}

    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    @Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			var location = restTemplate.getForObject(
					"https://api.waqi.info/feed/oporto/?token=5d9753a14280b7405f6ee4228c6f8d0bf8e086e7", String.class);            

            JSONObject obj = new JSONObject(location);

            log.info(""+obj.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").get("o3").toString().substring(1).replaceAll("\\{|\\}", "").split(",")[4]);
		};
	}
    
}

