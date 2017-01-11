package com.bell.common.client.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bell.common.client.context.AbstractDubboContext;

public abstract class AbstractApiHolder {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractApiHolder.class);
	@SuppressWarnings("rawtypes")
	public static AbstractDubboContext dubboContext = null;

	public static <T> T getBean(Class<T> clazz) {
		return dubboContext.getContext().getBean(clazz);
	}
	
	public static void printService(String serviceName){
		System.out.println("---"+serviceName+"---initialized");
	}
	
	public static void logService(String serviceName){
		logger.error("---"+serviceName+"---initialized");
	}
	
	public static void notNull(Object obj,String message){
		if(obj==null){
			throw new IllegalArgumentException(message);
		}
	}

}
