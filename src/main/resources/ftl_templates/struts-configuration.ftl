<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN" "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
	<package name="${strutsNamespace}${entityName}" namespace="/${strutsNamespace}" extends="struts-default">
		
		<result-types>
			 <result-type name="json" class="org.apache.struts2.json.JSONResult" />
		</result-types>
		<action name="load${entityName}Mgr">
			<result>${strutsNamespace}-${entityName}-management.jsp</result>
		</action>
		<action name="saveOrUpdate${entityName}" class="${entityName}Action" method="saveOrUpdate${entityName}">
			<result name="input">
				${strutsNamespace}-${entityName}-management.jsp
			</result>
			<result name="success" type="json">
				<param name="root">response</param>
			</result>
			<result name="error" type="json">
				<param name="root">response</param>
			</result>
		</action>
		<action name="delete${entityName}" class="${entityName}Action" method="delete${entityName}">
			<result name="input">
				${strutsNamespace}-${entityName}-management.jsp
			</result>
			<result name="success" type="json">
				<param name="root">response</param>
			</result>
			<result name="error" type="json">
				<param name="root">response</param>
			</result>
		</action>
		<action name="load${entityName}s" class="${entityName}Action" method="load${entityName}s">
			<result name="success" type="json">
				<param name="root">response</param>
			</result>
			<result name="error" type="json">
				<param name="root">response</param>
			</result>
		</action>
	</package>
	
</struts>