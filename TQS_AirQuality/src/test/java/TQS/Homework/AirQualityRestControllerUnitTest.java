package TQS.Homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(AirQualityRestController.class)
class AirQualityRestControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CityAqService service;

    @MockBean
    LocalCache cache;

    CityAQ cityAQ;

    @BeforeEach
    void setup(){
        cityAQ = new CityAQ();
        cityAQ.setName("lisbon");
    }

    @DisplayName("City exists and isnt in cache")
    @Test
    void getCityInfo() throws Exception {
        //Mock returns
            when(cache.hasCity(Mockito.anyString())).thenReturn(false);
            when(service.searchCity(Mockito.anyString())).thenReturn(true);
            when(service.getCity()).thenReturn(cityAQ);

        //
        mvc.perform(get("/api/search/lisbon"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("o3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("lat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("lon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("aqi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("time")));

    }

    @DisplayName("City doesnt exist")
    @Test
    void getNoCityInfo() throws Exception {
        //Mock returns
        when(cache.hasCity(Mockito.anyString())).thenReturn(false);
        when(service.searchCity(Mockito.anyString())).thenReturn(false);

        //
        mvc.perform(get("/api/search/asdadasdsadsa"))
                .andExpect(status().isNotFound());

    }

    @DisplayName("City exists in cache")
    @Test
    void getCityInfoCache() throws Exception {
        //Mock returns
        when(cache.hasCity(Mockito.anyString())).thenReturn(true);
        when(cache.retrieveCity(Mockito.anyString())).thenReturn(cityAQ);

        //
        mvc.perform(get("/api/search/lisbon"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("o3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("lat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("lon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("aqi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("time")));
    }

    @Test
    void getStatistics() throws Exception {
        mvc.perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("Hits")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("Misses")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("No_Requests")));
    }
}