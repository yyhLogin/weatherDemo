package com.weather.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yyh
 * @date 2018/3/8 15:04
 */
@Data
public class Forecast implements Serializable{

	private static final long serialVersionUID = 1L;

	private String date;
	private String high;
	private String fengli;
	private String low;
	private String fengxiang;
	private String type;
}
