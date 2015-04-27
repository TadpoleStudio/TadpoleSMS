package com.tadpole.vo;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.tadpole.entity.QuickDataSource;

public class Test {

	public static void main(String[] args) {

		QuickDataSource quickDataSource = new QuickDataSource();
		quickDataSource.setName("Jerry");

		try {
			
			System.out.println(BeanUtils.getProperty(quickDataSource, "name"));

		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}

	}

}
