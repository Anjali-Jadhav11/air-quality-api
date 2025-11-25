package com.aqi.aqi_service.controller;

import com.aqi.aqi_service.model.AqiResponse;
import com.aqi.aqi_service.service.AqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // <-- add this line
public class AqiController {

    @Autowired
    private AqiService aqiService;

    @GetMapping("/aqi")
    public AqiResponse getAqi(@RequestParam String city) {
        return aqiService.getAqiByCity(city);
    }
}
