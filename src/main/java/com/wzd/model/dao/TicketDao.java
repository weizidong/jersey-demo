package com.wzd.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.wzd.model.entity.Ticket;
import com.wzd.model.mapper.TicketMapper;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

import tk.mybatis.mapper.entity.Example;

/**
 * 券票数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class TicketDao {
	@Autowired
	private TicketMapper mapper;

	/**
	 * 创建券票
	 */
	public void create(Integer total, String foreignKey) {
		create(0, total, foreignKey);
	}

	/**
	 * 增加券票
	 */
	public void create(Integer old, Integer total, String foreignKey) {
		List<Ticket> list = new ArrayList<>();
		for (int i = old; i < total; i++) {
			list.add(new Ticket(Ticket.generate(i, foreignKey), foreignKey));
		}
		mapper.insertList(list);
	}

	/**
	 * 减少券票
	 */
	public void reduce(Integer old, Integer total, String foreignKey) {
		Example e = new Example(Ticket.class);
		List<String> keys = new ArrayList<>();
		for (int i = old; i < total; i++) {
			keys.add(Ticket.generate(i, foreignKey));
		}
		e.createCriteria().andIn("foreignKey", keys);
		mapper.deleteByExample(e);
	}

	/**
	 * 获取最新一张未使用的票
	 */
	public Ticket getNew(String foreignKey) {
		Example e = new Example(Ticket.class);
		e.createCriteria().andEqualTo("foreignKey", foreignKey).andIsNull("draw");
		PageHelper.startPage(1, 1);
		List<Ticket> tickets = mapper.selectByExample(e);
		if (tickets == null || tickets.size() <= 0) {
			throw new WebException(ResponseCode.已领完, foreignKey);
		}
		return tickets.get(0);
	}

	/**
	 * 使用票券
	 */
	public void used(Ticket t) {
		t.setUsed(new Date());
		mapper.updateByPrimaryKeySelective(t);
	}

	/**
	 * 领取票券
	 */
	public void draw(Ticket t) {
		t.setDraw(new Date());
		mapper.updateByPrimaryKeySelective(t);
	}

	/**
	 * 清理票券
	 */
	public void delete(String foreignKey) {
		Ticket t = new Ticket();
		t.setForeignKey(foreignKey);
		mapper.delete(t);
	}

	/**
	 * 清理票券
	 */
	public void delete(List<String> fks) {
		if (fks != null && fks.size() > 0) {
			Example e = new Example(Ticket.class);
			e.createCriteria().andIn("foreignKey", fks);
			mapper.deleteByExample(e);
		}
	}
}
