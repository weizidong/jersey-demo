package com.wzd.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.WxActivityDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Wxactivity;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.utils.PoiExcelUtils;
import com.wzd.web.dto.PageDto;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.PageParam;

/**
 * 微信活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class WxActivityService {
	@Autowired
	private WxActivityDao dao;

	/**
	 * 创建活动
	 */
	public void create(Wxactivity activity, Admin admin) {
		dao.create(activity);
	}

	/**
	 * 删除活动
	 */
	public void delete(Integer id, DeleteType type, Admin admin) {
		// TODO 删除活动
	}

	/**
	 * 修改活动
	 */
	public void update(Wxactivity activity, Admin admin) {
		// TODO 修改活动
	}

	/**
	 * 根据id查询
	 */
	public Wxactivity findById(Integer id, DeleteType type) {
		// TODO 根据id查询
		return null;
	}

	/**
	 * 条件查询所有
	 */
	public PageDto find(PageParam param) {
		// TODO 条件查询所有
		return null;
	}

	/**
	 * 审核
	 */
	public void auditing(AuditType parse, Admin user) {
		// TODO 审核
	}

	/**
	 * 暂停/启用
	 */
	public void changeState(StateType parse, Admin user) {
		// TODO 暂停/启用

	}

	/**
	 * 批量导出活动详情
	 */
	public HSSFWorkbook export(List<Integer> ids, HttpServletResponse response) {
		String[] headers = { 
				"活动主题@title", 
				"开始时间@start", 
				"结束时间@end", 
				"活动口令@command", 
				"最大参加人数@totle", 
				"当前参加人数@current", 
				"平均金额@avg", 
				"祝福语@wishing", 
				"活动回复语@reply", 
				"重复提示@repetition", 
				"领完提示@finish", 
				"活动结束语@over", 
				"审核状态@audit"
				};
		try {
			return PoiExcelUtils.createExcel2Export("活动列表", "活动列表", headers, dao.findByIds(ids));
		} catch (Exception e) {
			throw new WebException(ResponseCode.生成Excel表格数据失败);
		}
	}

	/**
	 * 导出参加活动的人员
	 */
	public void exportUser(Integer id, HttpServletResponse response) {
		// TODO 导出参加活动的人员

	}
}
