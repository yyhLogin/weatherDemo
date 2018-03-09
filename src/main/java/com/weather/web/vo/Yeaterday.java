package com.weather.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yyh
 * @date 2018/3/8 15:06
 */
@Data
public class Yeaterday implements Serializable{

	private static final long serialVersionUID = 1L;
	private String date;
	private String high;
	private String fx;
	private String low;
	private String fl;
	private String type;
}
