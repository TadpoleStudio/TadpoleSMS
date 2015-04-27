package com.tadpole.rest;

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

import com.tadpole.entity.QuickDataSource;
import com.tadpole.repository.QuickDataSourceRepository;
import com.tadpole.service.QuickDataSourceService;

@Component
@Path("/quickDataSource")
public class QuickDataSourceResource {

	@Autowired
	private QuickDataSourceRepository quickDataSourceRepository;
	
	@Resource(name = "QuickDataSourceService")
	private QuickDataSourceService quickDataSourceService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public QuickDataSource getQuickDataSourceById(@PathParam("id") Integer id) {

		QuickDataSource quickDataSource = quickDataSourceRepository.findOne(id);

		return quickDataSource;

	}
	
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QuickDataSource saveOrUpdateQuickDataSource(QuickDataSource quickDataSource) {
		
		QuickDataSource saved = quickDataSourceService.saveOrUpdateQuickDataSource(quickDataSource);
		
		return saved;
	}
	
	@POST
	@Path("/delete/{id}")
	public Response deleteQuickDataSource(@PathParam("id") String id) {
		
		try {
			quickDataSourceService.deleteQuickDataSource(id);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.ok().build();
	}

}
