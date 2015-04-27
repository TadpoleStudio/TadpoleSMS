package com.tadpole.creator;

import com.tadpole.vo.JpaEntityDefinition;

public class AllInOneCreator {

	public static void createEverything(JpaEntityDefinition jpaEntityDefinition) {

		EntityBeanCreator.generateSourceFile(jpaEntityDefinition);

		JsObjectCreator.generateSourceFile(jpaEntityDefinition);

		RepositoryCreator.generateSourceFile(jpaEntityDefinition);

		ServiceInterfaceCreator.generateSourceFile(jpaEntityDefinition);

		ServiceImplemetationCreator.generateSourceFile(jpaEntityDefinition);

		ControllerCreator.generateSourceFile(jpaEntityDefinition);
	}

}
