package com.jiangfw.common.lang.test.json;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * 
* <p>Title: JSON-XML转换工具</p>
* <p>desc:
* <p>Copyright: Copyright(c)Gb 2012</p>
* @author http://www.ij2ee.com
* @time 上午8:20:40
* @version 1.0
* @since
 */
public class XmlToJSONParser {
	private static final String STR_JSON = "{\"name\":\"Michael\",\"address\":{\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";
	public static String xml2JSON(String xml){
		return new XMLSerializer().read(xml).toString();
	}
	
	public static String json2XML(String json){
		JSONObject jobj = JSONObject.fromObject(json);
		String xml =  new XMLSerializer().write(jobj);
		return xml;
	}
	
	public static void main(String[] args) {
//		String xml = json2XML(STR_JSON);
//		System.out.println("xml = "+xml);
//		String json = xml2JSON(xml);  
//		System.out.println("json="+json);
		
		String xml1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><usp><result>0</result><errors><error dest=\"136888\" result=\"3505\" msg=\"目标号非法，为空或长度超出\"/><error dest=\"136999\" result=\"350235\" msg=\"超出\"/></errors></usp>";
		System.out.println(xml2JSON(xml1));
	}
}