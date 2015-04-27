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

import com.tadpole.entity.Menu;
import com.tadpole.repository.MenuRepository;
import com.tadpole.service.MenuService;

@Component
@Path("/menu")
public class MenuResource {

	@Autowired
	private MenuRepository menuRepository;
	
	@Resource(name = "MenuService")
	private MenuService menuService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Menu getMenuById(@PathParam("id") Integer id) {

		Menu menu = menuRepository.findOne(id);

		return menu;

	}
	
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Menu saveOrUpdateMenu(Menu menu) {
		
		Menu saved = menuService.saveOrUpdateMenu(menu);
		
		return saved;
	}
	
	@POST
	@Path("/delete/{id}")
	public Response deleteMenu(@PathParam("id") String id) {
		
		try {
			menuService.deleteMenu(id);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.ok().build();
	}

}
