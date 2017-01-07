package com.bell.common.client.context;

import java.util.Map;

public class ContextHolder {
	public static final ThreadLocal<Map<String,String>> contextHolder = new ThreadLocal<>();
			
}
