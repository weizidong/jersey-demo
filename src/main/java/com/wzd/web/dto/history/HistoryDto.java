package com.wzd.web.dto.history;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wzd.model.entity.History;
import com.wzd.model.entity.User;

@SuppressWarnings("serial")
public class HistoryDto implements Serializable {
	private User user;
	private List<History> historys;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<History> getHistorys() {
		return historys;
	}

	public void setHistorys(List<History> historys) {
		this.historys = historys;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
