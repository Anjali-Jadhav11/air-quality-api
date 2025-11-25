package com.aqi.aqi_service.service;

import com.aqi.aqi_service.model.AqiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class AqiService {

    private final WebClient webClient;

    public AqiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.waqi.info").build();
    }

    @Cacheable(value = "aqiCache", key = "#city")
    public AqiResponse getAqiByCity(String city) {

        String token = "6b61b37f0ec3d7cf3b9d459f066e58422baf76c7"; // replace with your token
        String url = "/feed/" + city + "/?token=" + token;

        try {
            // Get response as a generic Map to avoid mapping issues
            Map<String, Object> apiResponse = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            AqiResponse response = new AqiResponse();

            if (apiResponse == null || !"ok".equals(apiResponse.get("status"))) {
                response.setCity("Invalid city or token");
                return response;
            }

            Map<String, Object> data = (Map<String, Object>) apiResponse.get("data");
            response.setCity(((Map<String,Object>)data.get("city")).get("name").toString());
            response.setAqi((int) data.get("aqi"));
            response.setDominentPollutant((String)data.get("dominentpol"));

            // iAQI
            Map<String,Object> iaqi = (Map<String,Object>)data.get("iaqi");
            if (iaqi != null) {
                if (iaqi.get("pm25") != null) {
                    response.setPm25((int) ((Map<String,Object>)iaqi.get("pm25")).get("v"));
                }
                if (iaqi.get("pm10") != null) {
                    response.setPm10((int) ((Map<String,Object>)iaqi.get("pm10")).get("v"));
                }
            }

            return response;

        } catch (Exception e) {
            AqiResponse response = new AqiResponse();
            response.setCity("Error: " + e.getMessage());
            return response;
        }
    }
}