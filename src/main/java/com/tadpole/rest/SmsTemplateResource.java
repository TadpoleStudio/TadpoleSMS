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

import com.tadpole.entity.SmsTemplate;
import com.tadpole.repository.SmsTemplateRepository;
import com.tadpole.service.SmsTemplateService;

@Component
@Path("/smsTemplate")
public class SmsTemplateResource {

	@Autowired
	private SmsTemplateRepository smsTemplateRepository;
	
	@Resource(name = "SmsTemplateService")
	private SmsTemplateService smsTemplateService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SmsTemplate getSmsTemplateById(@PathParam("id") Integer id) {

		SmsTemplate smsTemplate = smsTemplateRepository.findOne(id);

		return smsTemplate;

	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SmsTemplate> getSmsTemplates() {

		List<SmsTemplate> smsTemplates = smsTemplateRepository.findAll();

		return smsTemplates;

	}
	
	@GET
	@Path("/allNames")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SmsTemplate> getSmsTemplateNames() {

		List<SmsTemplate> smsTemplates = smsTemplateRepository.findAll();

		return smsTemplates;

	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SmsTemplate saveOrUpdateSmsTemplate(SmsTemplate smsTemplate) {
		
		SmsTemplate saved = smsTemplateService.saveOrUpdateSmsTemplate(smsTemplate);
		
		return saved;
	}
	
	@POST
	@Path("/delete/{id}")
	public Response deleteSmsTemplate(@PathParam("id") String id) {
		
		try {
			smsTemplateService.deleteSmsTemplate(id);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.ok().build();
	}

}
