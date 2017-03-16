package com.wzd.service.wechat.tag;

import java.util.List;

import com.wzd.service.wechat.base.BaseResp;

/**
 * 企业号--->标签列表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxTagList extends BaseResp {
	private List<WxTag> taglist;

	public List<WxTag> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<WxTag> taglist) {
		this.taglist = taglist;
	}

	@Override
	public String toString() {
		return super.toString() + ", taglist=" + taglist + "}";
	}

}
