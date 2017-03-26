package com.wzd.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Ticket;
import com.wzd.model.mapper.TicketMapper;

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
			Ticket t = new Ticket();
			t.setForeignKey(foreignKey);
			t.setCreated(new Date());
			t.setTicket(Ticket.generate(i, foreignKey));
			list.add(t);
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
}
