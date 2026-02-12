package com.week5.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController 
public class CountryController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/country/{code}")
    public Countries getCountry(@PathVariable String code) {
        String url = "https://topups.reloadly.com/countries/" + code; 
        
        // Fetches data and automatically converts it to JSON for the browser
        return restTemplate.getForObject(url, Countries.class); 
    }
}