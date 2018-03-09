package com.weather.web.service;

import com.weather.web.vo.City;

import java.util.List;

public interface CityDataService {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity() throws Exception;
}
