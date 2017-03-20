/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : longquanyidb2.0

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-03-20 15:36:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：未删除，1：回收站，2：永久删除',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0：未开始，1：进行中，2：已结束',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  `title` varchar(20) DEFAULT NULL COMMENT '活动主题',
  `pic_url` varchar(128) DEFAULT NULL COMMENT '推送配图',
  `start` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end` datetime DEFAULT NULL COMMENT '活动结束时间',
  `place` varchar(50) DEFAULT NULL COMMENT '活动举办地点',
  `detail` blob COMMENT '活动详情',
  `total` int(6) DEFAULT '0' COMMENT '最大参加人数',
  `current` int(6) DEFAULT '0' COMMENT '当前参加人数',
  `pub_user` int(11) NOT NULL COMMENT '发布人',
  `dep_id` int(11) DEFAULT NULL COMMENT '发布机构',
  `auditor` int(11) DEFAULT NULL COMMENT '审核人',
  `aud_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态，0：未审核；1，审核通过；2审核未通过',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='活动实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `userid` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '成员UserID。对应管理端的帐号',
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '成员名称',
  `departments` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '成员所属部门id列表',
  `position` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '职位信息',
  `mobile` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `gender` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '性别。0表示未定义，1表示男性，2表示女性',
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `weixinid` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信号',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可',
  `status` int(1) DEFAULT NULL COMMENT '关注状态: 1=已关注，2=已冻结，4=未关注',
  `extattr` varchar(999) CHARACTER SET utf8 DEFAULT NULL COMMENT '扩展属性',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：正常，1：回收站，2：永久删除',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `auth` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限',
  `openid` varchar(36) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信唯一标志',
  `uname` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号',
  `pwd` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `audit` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态,0：未审核；1：审核通过；2：审核未通过；',
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='管理员实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id，整型。指定时必须大于1，不指定时则自动生成',
  `name` varchar(20) DEFAULT NULL COMMENT '部门名称。长度限制为32个字（汉字或英文字母），字符不能包括\\:*?"<>｜',
  `parentid` int(11) NOT NULL DEFAULT '1' COMMENT '父亲部门id。根部门id为1',
  `orders` int(11) DEFAULT NULL COMMENT '在父部门中的次序值。order值小的排序靠前。',
  `admin` varchar(20) DEFAULT NULL COMMENT '创建者',
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entryform
-- ----------------------------
DROP TABLE IF EXISTS `entryform`;
CREATE TABLE `entryform` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `activityId` int(11) NOT NULL COMMENT '活动ID',
  `time` datetime NOT NULL COMMENT '报名时间',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` int(1) DEFAULT NULL COMMENT '删除标志，0：未删除；1：回收站；2：永久删除',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0：未到场；1：已到场；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `url` varchar(200) DEFAULT NULL COMMENT 'url地址',
  `fk` int(11) DEFAULT NULL COMMENT '外键',
  `suffix` varchar(10) DEFAULT NULL COMMENT '后缀名',
  `userId` int(11) NOT NULL COMMENT '上传者',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：未删除；1：回收站；2：永久删除',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `type` int(1) DEFAULT NULL COMMENT '类型，0：头像；1：附件；2：重要文件',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='文件实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id，整型，指定此参数时新增的标签会生成对应的标签id，不指定时则以目前最大的id自增。',
  `tagname` varchar(32) DEFAULT NULL COMMENT '标签名称，长度限制为32个字（汉字或英文字母），标签名不可与其他标签重名。',
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：正常，1：回收站，2：永久删除',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0：未审核，1：审核通过，2：审核未通过',
  `nickname` char(20) DEFAULT NULL COMMENT '用户昵称',
  `openid` varchar(36) DEFAULT NULL COMMENT '微信唯一标志',
  `auth` varchar(128) DEFAULT NULL COMMENT '权限',
  `sex` char(1) NOT NULL DEFAULT '1' COMMENT '性别，0：未知，1：男，2：女',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证编号',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `dep_name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `dep_address` varchar(50) DEFAULT NULL COMMENT '单位地址',
  `dep_tel` varchar(20) DEFAULT NULL COMMENT '单位电话',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '住址',
  `headimgurl` varchar(128) DEFAULT NULL COMMENT '头像',
  `position` varchar(20) DEFAULT NULL COMMENT '岗位',
  `login` datetime DEFAULT NULL COMMENT '登录时间',
  `province` varchar(10) DEFAULT NULL COMMENT '用户个人资料填写的省份',
  `city` varchar(10) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `country` varchar(5) DEFAULT NULL COMMENT '国家，如中国为CN',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for wxactivity
-- ----------------------------
DROP TABLE IF EXISTS `wxactivity`;
CREATE TABLE `wxactivity` (
  `id` int(11) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：未删除；1：回收站；2：永久删除',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  `title` varchar(30) DEFAULT NULL COMMENT '活动主题',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '推送配图',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `command` varchar(20) DEFAULT NULL COMMENT '活动口令',
  `totle` int(6) NOT NULL DEFAULT '0' COMMENT '最大参加人数',
  `current` int(6) NOT NULL DEFAULT '0' COMMENT '当前参加人数',
  `avg` double NOT NULL DEFAULT '0' COMMENT '平均金额',
  `wishing` varchar(255) DEFAULT NULL COMMENT '祝福语',
  `reply` varchar(255) DEFAULT NULL COMMENT '活动回复语',
  `repetition` varchar(255) DEFAULT NULL COMMENT '重复提示',
  `finish` varchar(255) DEFAULT NULL COMMENT '领完提示',
  `over` varchar(255) DEFAULT NULL COMMENT '活动结束语',
  `pub_user` int(11) DEFAULT NULL COMMENT '发布人',
  `dep_id` int(11) DEFAULT NULL COMMENT '发布机构',
  `auditor` int(11) DEFAULT NULL COMMENT '审核人',
  `aud_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态,0：未审核；1：审核通过；2：审核未通过；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
