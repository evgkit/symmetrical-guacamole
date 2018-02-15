package com.evgkit.web.controller;

import com.evgkit.service.GeocodingService;
import com.evgkit.service.WeatherService;
import com.evgkit.service.dto.geocoding.GeocodingResult;
import com.evgkit.service.dto.geocoding.Location;
import com.evgkit.service.dto.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.evgkit.util.WebUtils.uriEncode;

@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private GeocodingService geocodingService;

    @RequestMapping("/geo")
    public String getWeatherForLocation(@RequestParam double lat, @RequestParam double lng, Model model) {
        Location loc = new Location(lat, lng);
        Weather weather = weatherService.findByLocation(loc);
        model.addAttribute("weather", weather);
        return "weather/detail";
    }

    @RequestMapping("/search")
    public String search(@RequestParam String q) {
        return String.format("redirect:/search/%s", uriEncode(q));
    }

    @RequestMapping("/search/{q}")
    public String getWeatherForSearchTerm(@PathVariable String q, Model model) {
        GeocodingResult geocodingResult = geocodingService.findBySearchTerm(q);
        Weather weather = weatherService.findByLocation(geocodingResult.getGeometry().getLocation());
        model.addAttribute("geocodingResult", geocodingResult);
        model.addAttribute("weather", weather);
        return "weather/detail";
    }

    @RequestMapping("/")
    public String home() {
        return "weather/detail";
    }
}