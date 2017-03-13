package com.wzd.web.filter.authority;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lifesense.communityhospital.service.doctor.dto.LoginSession;
import com.wzd.model.enums.RoleType;
import com.wzd.utils.SessionUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 权限检验过滤器
 * 
 * @author WeiZiDong
 *
 */
public class AuthorityFilter implements ContainerRequestFilter {
	private static final Logger log = LogManager.getLogger(AuthorityFilter.class);
	@Context
	private HttpServletRequest request;
	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		// 获取接口允许的权限
		Authority authority = resourceInfo.getResourceMethod().getAnnotation(Authority.class);
		RoleType[] roles = authority.value();
		// 默认为普通权限
		if (roles == null || roles.length == 0) {
			roles = new RoleType[] { RoleType.普通医生 };
		}
		log.info("接口允许的权限==>" + Arrays.toString(roles));
		// 获取用户本身具有的权限
		LoginSession session = SessionUtil.getSession(request);
		if (session == null) {
			throw new WebException(ResponseCode.禁止访问, "未登录");
		}
		int[] ownRole = session.getRole();
		if (ownRole == null || ownRole.length == 0) {
			throw new WebException(ResponseCode.未授权, "权限不足");
		}
		log.info("用户具有的权限==>" + Arrays.toString(ownRole));
		// 判断用户是否具有该接口与允许的权限
		for (int role : ownRole) {
			if (Arrays.asList(roles).contains(RoleType.parse(role))) {
				return;
			}
		}
		throw new WebException(ResponseCode.未授权, "权限不足");
	}

}
