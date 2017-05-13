package com.bell.common.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractResource {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected  String say(){
		System.out.println("say");
		return null;
	}
}
