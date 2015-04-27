package com.tadpole.creator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.google.common.io.Files;
import com.tadpole.constants.SystemPropertiesReader;
import com.tadpole.util.SystemUtils;
import com.tadpole.vo.JpaEntityDefinition;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JavaSearchVoCreator {

	public static String generateCode(JpaEntityDefinition jpaEntityDefinition) {

		try {

			if (jpaEntityDefinition.hasSearchableAttribute()) {
				Template template = SystemUtils.getFTLTemplateConfiguration().getTemplate("java-search-vo.ftl");

				String javaCode = FreeMarkerTemplateUtils.processTemplateIntoString(template, jpaEntityDefinition);

				System.out.println(javaCode);

				return javaCode;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String generateSourceFile(JpaEntityDefinition jpaEntityDefinition) {

		if (jpaEntityDefinition.hasSearchableAttribute()) {

			String filePath = getSourceFileName(jpaEntityDefinition);
			String sourceCode = generateCode(jpaEntityDefinition);
			try {

				Files.write(sourceCode, new File(filePath), Charset.defaultCharset());

			} catch (IOException e) {

				return null;
			}

			return sourceCode;
		}

		return StringUtils.EMPTY;
	}

	public static String getSourceFileName(JpaEntityDefinition jpaEntityDefinition) {

		if (jpaEntityDefinition.hasSearchableAttribute()) {
			
			String jpaEntityPackagePath = SystemPropertiesReader.getString("java-search-vo-package");
			String filePath = jpaEntityPackagePath + jpaEntityDefinition.getJavaClassName() + "SearchVo.java";

			return filePath;
		}

		return StringUtils.EMPTY;
	}
}
