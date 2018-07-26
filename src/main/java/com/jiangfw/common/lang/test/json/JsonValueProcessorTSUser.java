package com.jiangfw.common.lang.test.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonValueProcessorTSUser implements JsonValueProcessor {

	
	public JsonValueProcessorTSUser() {
		super();
	}

	public JsonValueProcessorTSUser(String format) {
		super();
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		return obj;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		
		return value.toString();
	}

}