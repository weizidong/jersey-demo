package com.lifesense.healthcenter.service.wechat.file.enums;

import com.lifesense.healthcenter.utils.StringUtil;

/**
 * 上传的微信文件类型
 * @author lifesense401
 *
 */
public enum FileTypeEnum {
	
	图片("image"),语音("voice"),视频("video"),缩略图("thumb");
	
	// 文件存放在服务的相对路径
	private String fileRaletivePath ;
	
	private String type;

	private FileTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public static FileTypeEnum getFileTypeEnum(String type) {
        for (FileTypeEnum item : FileTypeEnum.values()) {
            if (StringUtil.equals(item.getType(), type)) {
                return item;
            }
        }
        
        throw new RuntimeException("值[" + type + "]不是" + FileTypeEnum.class + "有效值。");
    }

	
	public String getFileRaletivePath() {
		return fileRaletivePath;
	}

	public void setFileRaletivePath(String fileRaletivePath) {
		this.fileRaletivePath = fileRaletivePath;
	}
			
	
}
