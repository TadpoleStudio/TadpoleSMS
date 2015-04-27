package com.tadpole.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadpole.entity.${javaClassName};
import com.tadpole.repository.${javaClassName}Repository;
import com.tadpole.service.${javaClassName}Service;

@Component
@Path("/${firstLetterLowerCaseJavaClassName}")
public class ${javaClassName}Resource {

	@Autowired
	private ${javaClassName}Repository ${firstLetterLowerCaseJavaClassName}Repository;
	
	@Resource(name = "${javaClassName}Service")
	private ${javaClassName}Service ${firstLetterLowerCaseJavaClassName}Service;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ${javaClassName} get${javaClassName}ById(@PathParam("id") Integer id) {

		${javaClassName} ${firstLetterLowerCaseJavaClassName} = ${firstLetterLowerCaseJavaClassName}Repository.findOne(id);

		return ${firstLetterLowerCaseJavaClassName};

	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<${javaClassName}> get${javaClassName}s() {

		List<${javaClassName}> ${firstLetterLowerCaseJavaClassName}s = ${firstLetterLowerCaseJavaClassName}Repository.findAll();

		return ${firstLetterLowerCaseJavaClassName}s;

	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ${javaClassName} saveOrUpdate${javaClassName}(${javaClassName} ${firstLetterLowerCaseJavaClassName}) {
		
		${javaClassName} saved = ${firstLetterLowerCaseJavaClassName}Service.saveOrUpdate${javaClassName}(${firstLetterLowerCaseJavaClassName});
		
		return saved;
	}
	
	@POST
	@Path("/delete/{id}")
	public Response delete${javaClassName}(@PathParam("id") String id) {
		
		try {
			${firstLetterLowerCaseJavaClassName}Service.delete${javaClassName}(id);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.ok().build();
	}

}
