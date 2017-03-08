package com.wzd.web.api;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.User;
import com.wzd.model.mapper.UserMapper;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserApi {
	@Autowired
	private UserMapper mapper;

	@Path("create")
	@POST
	public void create(User user) {
		mapper.insert(user);
	}

	@Path("delete/{id}")
	@POST
	public void delete(@PathParam("id") Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Path("update")
	@POST
	public void update(User user) {
		mapper.updateByPrimaryKey(user);
	}

	@Path("all")
	@POST
	public List<User> getAll() {
		return mapper.selectAll();
	}

	@Path("get/{id}")
	@POST
	public User getById(@PathParam("id") Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
}
