package com.wzd.service.wechat.tag;

import com.wzd.service.wechat.base.BaseResp;

/**
 * 企业号--->标签
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxTag extends BaseResp {
	private String tagname;
	private String tagid;

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

	@Override
	public String toString() {
		return super.toString() + ", tagname=" + tagname + ", tagid=" + tagid + "}";
	}

}
