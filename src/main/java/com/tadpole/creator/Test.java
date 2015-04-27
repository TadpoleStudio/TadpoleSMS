//package com.tadpole.creator;
//
//import com.google.common.collect.ImmutableList;
//import com.tadpole.vo.JpaAttributeDefinition;
//import com.tadpole.vo.JpaEntityDefinition;
//
//public class Test {
//
//	public static void main(String[] args) {
//
//		createAttributes();
//
//	}
//	
//	public static void createPoJo() {
//		
//	}
//	
//	public static void createFunction() {
//		
//		JpaEntityDefinition jpaEntityDefinition = new JpaEntityDefinition();
//
//		JpaAttributeDefinition first = new JpaAttributeDefinition("tableName", "String");
//		JpaAttributeDefinition second = new JpaAttributeDefinition("entityName", "String");
//		JpaAttributeDefinition third = new JpaAttributeDefinition("strutsNamespace", "String");
//		JpaAttributeDefinition fourth = new JpaAttributeDefinition("description", "String");
//
//		JpaAttributeDefinition title = new JpaAttributeDefinition("title", "String");
//		JpaAttributeDefinition menuName = new JpaAttributeDefinition("menuTitle", "String");
//
//		JpaAttributeDefinition jpaEntityCode = new JpaAttributeDefinition("jpaEntityCode", "String", "1000");
//		JpaAttributeDefinition jsVoCode = new JpaAttributeDefinition("jsVoCode", "String");
//		JpaAttributeDefinition actionCode = new JpaAttributeDefinition("actionCode", "String", "2000");
//		JpaAttributeDefinition repositoryCode = new JpaAttributeDefinition("repositoryCode", "String", "500");
//		JpaAttributeDefinition serviceInterfaceCode = new JpaAttributeDefinition("serviceInterfaceCode", "String", "500");
//		JpaAttributeDefinition serviceImplementationCode = new JpaAttributeDefinition("serviceImplementationCode", "String", "4000");
//
//		jpaEntityDefinition.setAttributeDefinitions(ImmutableList.of(first, second, third, fourth, title, menuName, jpaEntityCode, jsVoCode, actionCode, repositoryCode, serviceInterfaceCode, serviceImplementationCode));
//
//		jpaEntityDefinition.setJavaClassName("TadFunction");
//		jpaEntityDefinition.setTableName("tad_dev_function");
//
//		AllInOneCreator.createEverything(jpaEntityDefinition);
//	}
//	
//	public static void createAttributes() {
//		
//		JpaEntityDefinition jpaEntityDefinition = new JpaEntityDefinition();
//
//		JpaAttributeDefinition first = new JpaAttributeDefinition("name", "String");
//		JpaAttributeDefinition functionId = new JpaAttributeDefinition("functionId", "String");
//		JpaAttributeDefinition second = new JpaAttributeDefinition("type", "String");
//		JpaAttributeDefinition third = new JpaAttributeDefinition("label", "String");
//		JpaAttributeDefinition fourth = new JpaAttributeDefinition("required", "Boolean");
//
//		jpaEntityDefinition.setAttributeDefinitions(ImmutableList.of(functionId, first, second, third, fourth));
//
//		jpaEntityDefinition.setJavaClassName("TadAttribute");
//		jpaEntityDefinition.setTableName("tad_dev_attribute");
//
//		AllInOneCreator.createEverything(jpaEntityDefinition);
//		
//	}
//
//}
