package com.wzd.service.wechat.user.dto;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.wzd.model.entity.User;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 服务号返回的用户列表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class FwUserList extends BaseResp {
	private Integer total; // 关注该公众账号的总用户数
	private Integer count; // 拉取的OPENID个数，最大值为10000
	private Map<String, List<String>> data; // 列表数据，OPENID的列表
	private String next_openid; // 拉取列表的最后一个用户的OPENID
	private List<User> user_info_list; // 拉取的用户基本信息列表

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public List<String> getOpenids() {
		return data == null ? null : data.get("openid");
	}

	public void setData(List<String> openids) {
		Map<String, List<String>> data = new HashedMap<>();
		data.put("openid", openids);
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

	public List<User> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<User> user_info_list) {
		this.user_info_list = user_info_list;
	}

	@Override
	public String toString() {
		return super.toString() + ", total=" + total + ", count=" + count + ", data=" + data + ", next_openid=" + next_openid + ", user_info_list=" + user_info_list + "]";
	}

}
