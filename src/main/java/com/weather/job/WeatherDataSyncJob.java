package com.weather.job;

import com.weather.web.service.CityDataService;
import com.weather.web.service.WeatherDataService;
import com.weather.web.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @author yyh
 * @date 2018/3/8 16:35
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {

	@Autowired
	private CityDataService cityDataService;

	@Autowired
	private WeatherDataService weatherDataService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("Weather Data Sync Job. Start！");
		// 获取城市ID列表
		List<City> cityList = null;

		try {
			cityList = cityDataService.listCity();
		} catch (Exception e) {
			log.error("Exception!", e);
		}

		// 遍历城市ID获取天气
		assert cityList != null;
		for (City city : cityList) {
			String cityId = city.getCityId();
			log.info("Weather Data Sync Job, cityId:" + cityId);

			weatherDataService.syncDateByCityId(cityId);
		}

		log.info("Weather Data Sync Job. End！");
	}
}
