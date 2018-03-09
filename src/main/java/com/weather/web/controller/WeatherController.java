package com.weather.web.controller;

import com.weather.web.service.WeatherDataService;
import com.weather.web.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yyh
 * @date 2018/3/8 15:08
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
	private final WeatherDataService weatherDataService;

	@Autowired
	public WeatherController(WeatherDataService weatherDataService) {
		this.weatherDataService = weatherDataService;
	}

	@GetMapping("/cityId/{cityId}")
	public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}

	@GetMapping("/cityName/{cityName}")
	public WeatherResponse getWeatherByCityName(@PathVariable("cityName") String cityName) {
		return weatherDataService.getDataByCityName(cityName);
	}
}
