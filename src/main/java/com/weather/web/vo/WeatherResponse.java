package com.weather.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yyh
 * @date 2018/3/8 15:07
 */
@Data
public class WeatherResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Weather data;
	private Integer status;
	private String desc;
}
