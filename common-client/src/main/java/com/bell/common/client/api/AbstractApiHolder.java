package com.bell.common.client.api;

import com.bell.common.client.context.AbstractDubboContext;

public abstract class AbstractApiHolder {
	public static final ThreadLocal<AbstractDubboContext> holder = new ThreadLocal<>();

	public static <T> T getBean(Class<T> clazz) {
		return holder.get().getContext().getBean(clazz);
	}

}
