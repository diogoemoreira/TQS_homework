package TQS.Homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalCacheTest {
    CityAQ city;
    LocalCache cache;

    @MockBean
    CityAqService aqService;

    @BeforeEach
    void setup(){
        city = new CityAQ();
        city.setName("Test");
        cache = new LocalCache();
        cache.addCache("Test", city);
    }

    @DisplayName("Adding new city")
    @Test
    void addCache() {
        CityAQ newCity = new CityAQ();
        newCity.setName("NewCity");
        when(aqService.getCity()).thenReturn(newCity);
        cache.addCache("NewCity",aqService.getCity());

        assertEquals(true, cache.hasCity("NewCity"));
        verify(aqService, times(1)).getCity();
    }

    @DisplayName("Confirming if city exists")
    @Test
    void hasCity() {
        assertEquals(true, cache.hasCity("Test"));
    }

    @DisplayName("retrieve city from cache")
    @Test
    void retrieveCity() {
        assertEquals("Test", cache.retrieveCity("Test").getName());
    }

    @DisplayName("Adding number of requests")
    @Test
    void getCountOfReq() {
        cache.addCount();
        cache.addCount();
        assertEquals(2, cache.getCountOfReq());

    }

    @DisplayName("Number of hits increases")
    @Test
    void getHits(){
        CityAQ newCity = new CityAQ();
        newCity.setName("NewCity");
        when(aqService.getCity()).thenReturn(newCity);
        cache.addCache("NewCity",aqService.getCity());
        cache.retrieveCity("Test");
        cache.retrieveCity("Test");

        assertEquals(4, cache.getHits());
    }

    @DisplayName("Number of misses increases")
    @Test
    void getMisses(){
        cache.missed();
        cache.missed();
        cache.missed();
        assertEquals(3, cache.getMisses());
    }

    @DisplayName("TTL expired")
    @Test
    void ttlExpires(){
        LocalCache expiredCache = new LocalCache(-1);
        expiredCache.addCache("Test", city);
        assertNull(expiredCache.retrieveCity("Test"));
    }
}