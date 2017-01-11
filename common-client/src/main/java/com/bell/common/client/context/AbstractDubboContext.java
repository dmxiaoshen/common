package com.bell.common.client.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bell.common.client.api.AbstractApiHolder;

public abstract class AbstractDubboContext<T extends AbstractDubboContext<T>> {

	protected ClassPathXmlApplicationContext context;
	protected final String appName;
	protected final String zkAddress;
	protected final String configPath;
	
	public AbstractDubboContext(String appName, String zkAddress) {
		super();
		this.appName = appName;
		this.zkAddress = zkAddress;
		this.configPath = "classpath*:dubbo-common-consumer.xml";
		Map<String, String> config = new HashMap<>();
		config.put("appName", this.appName);
		config.put("zkAddress", this.zkAddress);
		ContextHolder.contextHolder.set(config);
		context = new ClassPathXmlApplicationContext(this.configPath);
	}

	public AbstractDubboContext(String appName, String zkAddress, String configPath) {
		super();
		this.appName = appName;
		this.zkAddress = zkAddress;
		this.configPath = configPath;
		Map<String, String> config = new HashMap<>();
		config.put("appName", this.appName);
		config.put("zkAddress", this.zkAddress);
		ContextHolder.contextHolder.set(config);
		context = new ClassPathXmlApplicationContext(this.configPath);
	}

	@SuppressWarnings("unchecked")
	public T start() {
		if (context != null) {
			context.start();
			AbstractApiHolder.dubboContext = this;
			return (T) this;
		} else {
			throw new IllegalStateException("dubbo context can not be null");
		}
	}

	public ClassPathXmlApplicationContext getContext() {
		return this.context;
	}

	public String getAppName() {
		return appName;
	}

	public String getZkAddress() {
		return zkAddress;
	}

	public String getConfigPath() {
		return configPath;
	}

	@Override
	public String toString() {
		return "appName[" + appName + "],zkAddress[" + zkAddress + "],configPath[" + configPath + "]";
	}

}
