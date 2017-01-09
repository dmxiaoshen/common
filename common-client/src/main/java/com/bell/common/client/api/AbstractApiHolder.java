package com.bell.common.client.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bell.common.client.context.AbstractDubboContext;

public abstract class AbstractApiHolder {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public static final ThreadLocal<AbstractDubboContext> holder = new ThreadLocal<>();

	public static <T> T getBean(Class<T> clazz) {
		return holder.get().getContext().getBean(clazz);
	}

	public abstract void showService();
	
	protected void printService(String serviceName){
		System.out.println("---"+serviceName+"---initialized");
	}
	
	protected void logService(String serviceName){
		logger.error("---"+serviceName+"---initialized");
	}

}
