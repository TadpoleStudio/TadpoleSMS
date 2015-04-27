package com.tadpole.creator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.google.common.io.Files;
import com.tadpole.constants.SystemPropertiesReader;
import com.tadpole.util.SystemUtils;
import com.tadpole.vo.JspVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JspCreator {

	public static String generateSourceFile(JspVo jspVo) {

		try {
			String filePath = getSourceFileName(jspVo);
			
			System.out.println(filePath);
			
			File jspFile = new File(filePath);
			if (!jspFile.exists()) {
				jspFile.createNewFile();
			}

			Template template = SystemUtils.getFTLTemplateConfiguration().getTemplate("jsp.ftl");

			String jspCode = FreeMarkerTemplateUtils.processTemplateIntoString(template, jspVo);

			System.out.println(jspCode);

			Files.write(jspCode, jspFile, Charset.defaultCharset());

			return jspCode;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (TemplateException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static String getSourceFileName(JspVo jspVo) {

		String jspFileName = SystemPropertiesReader.getString("jsp-base-folder");
		String filePath = jspFileName + jspVo.getStrutsNamespace() + "\\" + jspVo.getStrutsNamespace() + "-" + jspVo.getJavaClassName() + "-management.jsp";

		return filePath;
	}
}
