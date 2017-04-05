package com.wzd.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.AdminDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.enums.APPType;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.ViewPage;
import com.wzd.service.wechat.user.QyUserApi;
import com.wzd.utils.EhcacheUtil;
import com.wzd.utils.FileUtil;
import com.wzd.utils.MD5Utils;
import com.wzd.utils.QRCodeUtil;
import com.wzd.utils.StringUtil;
import com.wzd.utils.ThreadPoolUtils;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.Session;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.IdListParam;
import com.wzd.web.param.PageParam;

/**
 * 管理员业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class AdminService {
	@Autowired
	private AdminDao dao;

	/**
	 * 登录
	 */
	public void login(Admin admin, HttpServletRequest request, HttpServletResponse response) {
		Admin dbAdmin = dao.login(admin, DeleteType.未删除);
		if (dbAdmin == null) {
			throw new WebException(ResponseCode.用户不存在);
		}
		String pwd = admin.getPwd();
		if (StringUtil.isEmpty(pwd) || !MD5Utils.getMD5ofStr(pwd).equals(dbAdmin.getPwd())) {
			throw new WebException(ResponseCode.密码错误);
		}
		// 更新登录时间
		dbAdmin.setLogin(new Date());
		dao.update(dbAdmin);
		// 保存Session
		Session session = SessionUtil.generateSession(APPType.管理平台.getValue(), null, null, dbAdmin);
		SessionUtil.saveSession(session, request, response);
	}

	/**
	 * 创建
	 */
	public void create(Admin admin) {
		QyUserApi.create(admin);
		dao.create(admin);
	}

	/**
	 * 删除
	 */
	public void delete(String userid, DeleteType type) {
		QyUserApi.delete(userid);
		dao.delete(userid, type);
	}

	/**
	 * 批量删除
	 */
	public void delete(IdListParam<String> param) {
		dao.delete(param);
	}

	/**
	 * 修改
	 */
	public void update(Admin admin) {
		// TODO 修改管理员

	}

	/**
	 * 查询详情
	 */
	public Admin findById(Integer id) {
		// TODO 查询管理员详情
		return null;
	}

	/**
	 * 条件分页查询
	 */
	public PageInfo<Admin> find(PageParam param) {
		// TODO 条件分页查询管理员
		return null;
	}

	/**
	 * 审核
	 */
	public void auditing(AuditType parse, Admin user) {
		// TODO 审核管理员
	}

	/**
	 * 批量导出管理员
	 */
	public HSSFWorkbook export(List<Integer> ids, HttpServletResponse response) {
		// TODO 批量导出管理员
		return null;
	}

	/**
	 * 获取登录参数
	 */
	public Session login2(HttpServletRequest request, HttpServletResponse response) {
		Session s = SessionUtil.generateSession(APPType.管理平台.getValue(), null, null, null);
		s.setQrcode(QRCodeUtil.encode(ViewPage.genarate(ViewPage.login2, s.getSessionId())));
		EhcacheUtil eh = EhcacheUtil.getInstance();
		eh.putSession(s.getSessionId(), s);
		ThreadPoolUtils.schedule(() -> {
			Session now = eh.getSession(s.getSessionId());
			if (now.getUser() == null) {
				eh.removeSession(s.getSessionId());
			}
			FileUtil.delete(s.getQrcode());
		}, new Date(s.getTs() + 2 * 60 * 1000));
		return s;
	}

}
