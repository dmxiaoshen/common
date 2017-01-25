package com.bell.common.base.spring;

import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @description 获取bean操作类
 */
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * @Description 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (SpringContextHolder.applicationContext == null) {
			SpringContextHolder.applicationContext = applicationContext;
		}
	}

	/**
	 * @Description 取得存储在静态变量中的ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * @Description 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * @Description 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBean(clazz);
	}

	/**
	 * @Description 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 */
	public static <T> T getFirstBean(Class<T> clazz) {
		Map<String, T> map = getBeans(clazz);
		if (map != null && !map.isEmpty()) {
			return (T) map.values().iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * @Description 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 */
	public static <T> Map<String, T> getBeans(Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBeansOfType(clazz);
	}

	/**
	 * @Description 清除applicationContext静态变量
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}

	/**
	 * @Description 检查applicationContext是否注入
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null)
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义 ");
	}

	/**
	 * @Description 判断以给定名字注册的bean定义是一个singleton还是一个prototype
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getType(String name) throws NoSuchBeanDefinitionException {
		return (T) applicationContext.getType(name);
	}

	/**
	 * @Description 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

	/**
	 * @Description 获取所有Spring容器中所有bean
	 */
	public static DefaultListableBeanFactory getBeanFactory() {
		return (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
	}

	/**
	 * @Description 获取所有Spring容器中所有bean
	 */
	public static String[] getBeanNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}

	/**
	 * @Description 向spring容器注册bean
	 */
	public static void registerBean(String beanName, Class<?> clazz, Map<String, Object> properties) {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(clazz);
		beanDefinition.setPropertyValues(new MutablePropertyValues(properties));
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext
				.getAutowireCapableBeanFactory();
		acf.registerBeanDefinition(beanName, beanDefinition);
	}

	/**
	 * ;
	 * 
	 * @Description 向spring容器删除bean
	 */
	public static void removeBean(String beanName) {
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext
				.getAutowireCapableBeanFactory();
		acf.removeBeanDefinition(beanName);
	}

}