package com.weather.web.service.impl;

import com.weather.web.service.CityDataService;
import com.weather.web.util.XmlBuilder;
import com.weather.web.vo.City;
import com.weather.web.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author yyh
 * @date 2018/3/8 15:36
 */
@Service
public class CityDataServiceImpl implements CityDataService{
	@Override
	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
		StringBuilder buffer = new StringBuilder();
		String line;

		while ((line = br.readLine()) !=null) {
			buffer.append(line);
		}

		br.close();

		// XML转为Java对象
		CityList cityList = (CityList) XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
		return cityList.getCityList();
	}
}
