package com.wzd.web.rest.api;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.User;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.UserService;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserApi {
	@Autowired
	private UserService service;

	@Path("/create")
	@POST
	public void create(User user) {
		service.create(user);
	}

	@Path("/delete/{id}/{type}")
	@POST
	public void delete(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		service.delete(id, DeleteType.parse(type));
	}

	@Path("/update")
	@POST
	public void update(User user) {
		service.update(user);
	}

	@Path("/get/{id}/{type}")
	@POST
	public User getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}

	@Path("/find")
	@POST
	public List<User> getById(User user) {
		return service.find(user);
	}
}
