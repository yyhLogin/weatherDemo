package com.weather.web.service;

import com.weather.web.vo.WeatherResponse;

public interface WeatherDataService {
	/**
	 * 根据城市ID查询天气数据
	 *
	 * @param cityId 城市id
	 * @return 天气统一响应数据
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 *
	 * @param cityName 城市名称
	 * @return 天气统一响应数据
	 */
	WeatherResponse getDataByCityName(String cityName);

	/**
	 * 根据城市ID来同步天气
	 * @param cityId 城市id
	 */
	void syncDateByCityId(String cityId);
}
