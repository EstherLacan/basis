package com.jiangfw.common.lang.test.json;

import net.sf.json.processors.PropertyNameProcessor;

public class JsonNameProcessor implements PropertyNameProcessor {

	public String processPropertyName(Class beanClass, String name) {
		if(name.equals("id")){
			return "NID";
		}else if(name.equals("name")){
			return "NName";
		}
		return name;
	}


}