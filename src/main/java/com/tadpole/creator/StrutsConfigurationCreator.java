package com.tadpole.creator;

import java.io.File;
import java.io.IOException;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.google.common.io.Files;
import com.tadpole.constants.SystemPropertiesReader;
import com.tadpole.entity.TadFunction;
import com.tadpole.util.SystemUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StrutsConfigurationCreator {

	public static String generateSourceFile(TadFunction tadFunction) {

		try {
			Template template = SystemUtils.getFTLTemplateConfiguration().getTemplate("struts-configuration.ftl");

			String javaCode = FreeMarkerTemplateUtils.processTemplateIntoString(template, tadFunction);
			
			String strutsConfigurationFileName = getSourceFileName(tadFunction);
			File strutsFile = new File(strutsConfigurationFileName);
			if (!strutsFile.exists()) {
				strutsFile.createNewFile();
			}
			
			Files.write(javaCode.getBytes(), strutsFile);
			
			System.out.println(javaCode);

			return javaCode;

		} catch (IOException e) {
		} catch (TemplateException e) {
		}

		return "";
	}

	public static String getSourceFileName(TadFunction tadFunction) {

		String strutsConfigurationFileName = SystemPropertiesReader.getString("struts-folder") + tadFunction.getStrutsNamespace() + "-" + tadFunction.getEntityName() + "-struts.xml";

		return strutsConfigurationFileName;
	}
}
