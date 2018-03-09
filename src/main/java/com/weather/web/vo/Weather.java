package com.weather.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yyh
 * @date 2018/3/8 15:05
 */
@Data
public class Weather implements Serializable{

	private static final long serialVersionUID = 1L;

	private String city;
	private String aqi;
	private String ganmao;
	private String wendu;
	private Yeaterday yesterday;
	private List<Forecast> forecast;

}
