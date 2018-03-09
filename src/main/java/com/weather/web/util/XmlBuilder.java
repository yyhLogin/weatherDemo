package com.weather.web.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author yyh
 * @date 2018/3/8 15:38
 */
public class XmlBuilder {
	/**
	 * 将XML转为指定的POJO
	 * @param clazz 需要转换的对象的类
	 * @param xmlStr 需要转换的字符串
	 * @return Object
	 * @throws Exception Exception
	 */
	public static Object xmlStrToOject(Class<?> clazz, String xmlStr) throws Exception {
		Object xmlObject = null;
		Reader reader = null;
		JAXBContext context = JAXBContext.newInstance(clazz);

		// XML 转为对象的接口
		Unmarshaller unmarshaller = context.createUnmarshaller();

		reader = new StringReader(xmlStr);
		xmlObject = unmarshaller.unmarshal(reader);

		if (null != reader) {
			reader.close();
		}

		return xmlObject;
	}
}
