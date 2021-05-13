package TQS.Homework;

//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HomeworkApplication {

    //private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    /*
    @Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			var location = restTemplate.getForObject(
					"https://api.waqi.info/feed/oporto/?token=5d9753a14280b7405f6ee4228c6f8d0bf8e086e7", String.class);            

            JSONObject obj = new JSONObject(location);

            log.info(""+obj.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").get("o3").toString().substring(1).replaceAll("\\{|\\}", "").split(",")[3]);
		};
	}
    */
}


//5d9753a14280b7405f6ee4228c6f8d0bf8e086e7
//https://api.waqi.info/feed/beijing/?token=5d9753a14280b7405f6ee4228c6f8d0bf8e086e7 
/*{
    "status": "ok",
    "data": {
        "aqi": 38,
        "idx": 1451,
        "attributions": [
            {
                "url": "http://www.bjmemc.com.cn/",
                "name": "Beijing Environmental Protection Monitoring Center (北京市环境保护监测中心)"
            },
            {
                "url": "https://waqi.info/",
                "name": "World Air Quality Index Project"
            }
        ],
        "city": {
            "geo": [
                39.954592,
                116.468117
            ],
            "name": "Beijing (北京)",
            "url": "https://aqicn.org/city/beijing"
        },
        "dominentpol": "pm25",
        "iaqi": {
            "co": {
                "v": 5.5
            },
            "h": {
                "v": 62
            },
            "no2": {
                "v": 29.8
            },
            "o3": {
                "v": 12.6
            },
            "p": {
                "v": 1010
            },
            "pm10": {
                "v": 38
            },
            "pm25": {
                "v": 38
            },
            "so2": {
                "v": 1.1
            },
            "t": {
                "v": 14
            },
            "w": {
                "v": 1.5
            }
        },
        "time": {
            "s": "2021-05-12 01:00:00",
            "tz": "+08:00",
            "v": 1620781200,
            "iso": "2021-05-12T01:00:00+08:00"
        },
        "forecast": {
            "daily": {
                "o3": [
                    {
                        "avg": 22,
                        "day": "2021-05-10",
                        "max": 34,
                        "min": 7
                    },
                    {
                        "avg": 22,
                        "day": "2021-05-11",
                        "max": 40,
                        "min": 6
                    },
                    {
                        "avg": 19,
                        "day": "2021-05-12",
                        "max": 41,
                        "min": 2
                    },
                    {
                        "avg": 16,
                        "day": "2021-05-13",
                        "max": 47,
                        "min": 1
                    },
                    {
                        "avg": 16,
                        "day": "2021-05-14",
                        "max": 31,
                        "min": 1
                    },
                    {
                        "avg": 22,
                        "day": "2021-05-15",
                        "max": 26,
                        "min": 15
                    }
                ],
                "pm10": [
                    {
                        "avg": 81,
                        "day": "2021-05-10",
                        "max": 119,
                        "min": 72
                    },
                    {
                        "avg": 77,
                        "day": "2021-05-11",
                        "max": 91,
                        "min": 65
                    },
                    {
                        "avg": 82,
                        "day": "2021-05-12",
                        "max": 117,
                        "min": 57
                    },
                    {
                        "avg": 101,
                        "day": "2021-05-13",
                        "max": 122,
                        "min": 72
                    },
                    {
                        "avg": 57,
                        "day": "2021-05-14",
                        "max": 66,
                        "min": 48
                    },
                    {
                        "avg": 68,
                        "day": "2021-05-15",
                        "max": 114,
                        "min": 45
                    },
                    {
                        "avg": 34,
                        "day": "2021-05-16",
                        "max": 45,
                        "min": 21
                    },
                    {
                        "avg": 45,
                        "day": "2021-05-17",
                        "max": 57,
                        "min": 35
                    }
                ],
                "pm25": [
                    {
                        "avg": 157,
                        "day": "2021-05-10",
                        "max": 158,
                        "min": 138
                    },
                    {
                        "avg": 157,
                        "day": "2021-05-11",
                        "max": 173,
                        "min": 137
                    },
                    {
                        "avg": 156,
                        "day": "2021-05-12",
                        "max": 164,
                        "min": 137
                    },
                    {
                        "avg": 174,
                        "day": "2021-05-13",
                        "max": 189,
                        "min": 149
                    },
                    {
                        "avg": 147,
                        "day": "2021-05-14",
                        "max": 158,
                        "min": 137
                    },
                    {
                        "avg": 140,
                        "day": "2021-05-15",
                        "max": 162,
                        "min": 93
                    },
                    {
                        "avg": 94,
                        "day": "2021-05-16",
                        "max": 134,
                        "min": 67
                    },
                    {
                        "avg": 105,
                        "day": "2021-05-17",
                        "max": 137,
                        "min": 64
                    }
                ],
                "uvi": [
                    {
                        "avg": 2,
                        "day": "2021-05-11",
                        "max": 6,
                        "min": 0
                    },
                    {
                        "avg": 1,
                        "day": "2021-05-12",
                        "max": 4,
                        "min": 0
                    },
                    {
                        "avg": 0,
                        "day": "2021-05-13",
                        "max": 3,
                        "min": 0
                    },
                    {
                        "avg": 1,
                        "day": "2021-05-14",
                        "max": 4,
                        "min": 0
                    },
                    {
                        "avg": 0,
                        "day": "2021-05-15",
                        "max": 3,
                        "min": 0
                    },
                    {
                        "avg": 0,
                        "day": "2021-05-16",
                        "max": 0,
                        "min": 0
                    }
                ]
            }
        },
        "debug": {
            "sync": "2021-05-12T03:01:08+09:00"
        }
    }
} */