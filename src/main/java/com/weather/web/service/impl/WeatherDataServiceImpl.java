package com.weather.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.web.service.WeatherDataService;
import com.weather.web.vo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yyh
 * @date 2018/3/8 15:11
 */
@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService{

	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	/**
	 * TIME_OUT 写入redis数据库中的数据过期时间  默认为1800s
	 */
	private static final long TIME_OUT = 1800L;

	private final RestTemplate restTemplate;

	private final StringRedisTemplate stringRedisTemplate;

	@Autowired
	public WeatherDataServiceImpl(RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
		this.restTemplate = restTemplate;
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeahter(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeahter(uri);
	}

	@Override
	public void syncDateByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);
	}

	private WeatherResponse doGetWeahter(String uri) {
		String key = uri;
		String strBody = null;
		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse resp = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		// 先查缓存，缓存有的取缓存中的数据
		if (stringRedisTemplate.hasKey(key)) {
			log.info("Redis has data");
			strBody = ops.get(key);
		} else {
			log.info("Redis don't has data");
			// 缓存没有，再调用服务接口来获取
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

			if (respString.getStatusCodeValue() == 200) {
				strBody = respString.getBody();
			}

			// 数据写入缓存
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
		}

		try {
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("Error!",e);
		}

		return resp;
	}

	/**
	 * 把天气数据放在缓存
	 * @param uri
	 */
	private void saveWeatherData(String uri) {
		String key = uri;
		String strBody = null;
		ValueOperations<String, String>  ops = stringRedisTemplate.opsForValue();

		// 调用服务接口来获取
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}

		// 数据写入缓存
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

	}
}
