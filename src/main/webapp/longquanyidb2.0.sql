/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : longquanyidb2.0

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-03-26 16:56:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `created` datetime NOT NULL COMMENT '创建时间',
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
  `pub_user` char(32) NOT NULL COMMENT '发布人',
  `dep_id` int(11) DEFAULT NULL COMMENT '发布机构',
  `auditor` char(32) DEFAULT NULL COMMENT '审核人',
  `aud_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态，0：未审核；1，审核通过；2审核未通过',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='活动实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` char(32) NOT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `weixinid` varchar(32) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `extattr` varchar(999) DEFAULT NULL,
  `departments` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `auth` varchar(255) DEFAULT NULL,
  `openid` varchar(36) DEFAULT NULL,
  `pwd` varchar(36) DEFAULT NULL,
  `login` datetime DEFAULT NULL,
  `audit` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='管理员实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id，整型。指定时必须大于1，不指定时则自动生成',
  `name` varchar(20) DEFAULT NULL COMMENT '部门名称。长度限制为32个字（汉字或英文字母），字符不能包括\\:*?"<>｜',
  `parentid` int(11) NOT NULL DEFAULT '1' COMMENT '父亲部门id。根部门id为1',
  `orders` int(11) DEFAULT NULL COMMENT '在父部门中的次序值。order值小的排序靠前。',
  `admin` char(32) DEFAULT NULL COMMENT '创建者',
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entryform
-- ----------------------------
DROP TABLE IF EXISTS `entryform`;
CREATE TABLE `entryform` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `userId` char(32) NOT NULL COMMENT '用户ID',
  `activityId` char(32) NOT NULL COMMENT '活动ID',
  `time` datetime NOT NULL COMMENT '报名时间',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(1) DEFAULT NULL COMMENT '删除标志，0：未删除；1：回收站；2：永久删除',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0：未到场；1：已到场；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `url` varchar(200) DEFAULT NULL COMMENT 'url地址',
  `fk` char(32) DEFAULT NULL COMMENT '外键',
  `suffix` varchar(10) DEFAULT NULL COMMENT '后缀名',
  `userId` char(32) NOT NULL COMMENT '上传者',
  `created` datetime NOT NULL COMMENT '上传时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：未删除；1：回收站；2：永久删除',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `type` int(1) DEFAULT NULL COMMENT '类型，0：头像；1：附件；2：重要文件',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='文件实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `welfare_id` char(32) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `score` int(6) DEFAULT NULL,
  `ticket` char(6) DEFAULT NULL,
  `recording` datetime DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `deleled` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket` char(6) DEFAULT NULL,
  `foreign_key` char(32) DEFAULT NULL,
  `used` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1401 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(32) NOT NULL COMMENT 'ID(UUID)',
  `subscribe` int(1) DEFAULT NULL COMMENT '用户是否订阅该公众号标识',
  `openid` varchar(32) DEFAULT NULL COMMENT '用户的标识',
  `city` varchar(10) DEFAULT NULL COMMENT '用户所在城市',
  `country` varchar(10) DEFAULT NULL COMMENT '用户所在国家',
  `province` varchar(10) DEFAULT NULL COMMENT '用户所在省份',
  `language` varchar(10) DEFAULT NULL COMMENT '用户的语言',
  `subscribe_time` bigint(20) DEFAULT NULL COMMENT '用户关注时间，为时间戳',
  `unionid` varchar(32) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `groupid` int(11) DEFAULT NULL COMMENT '用户所在的分组ID',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户的昵称',
  `sex` int(1) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `remark` varchar(255) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `marriage` int(1) NOT NULL DEFAULT '0' COMMENT '婚姻',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `dep_id` int(11) DEFAULT NULL COMMENT '部门',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证编号',
  `position` varchar(20) DEFAULT NULL COMMENT '岗位',
  `audit` int(1) DEFAULT NULL COMMENT '状态，0：未审核，1：审核通过，2：审核未通过',
  `auditor` char(32) DEFAULT NULL COMMENT ' 审核人',
  `login` datetime DEFAULT NULL COMMENT '登录时间',
  `updated` datetime DEFAULT NULL COMMENT '修改时间',
  `auth` varchar(255) DEFAULT NULL COMMENT '权限',
  `score` int(20) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户实体类\r\n@author weizidong';

-- ----------------------------
-- Table structure for welfare
-- ----------------------------
DROP TABLE IF EXISTS `welfare`;
CREATE TABLE `welfare` (
  `id` char(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `score` int(6) DEFAULT NULL,
  `time` int(2) DEFAULT NULL,
  `provider` varchar(30) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `total` int(6) DEFAULT NULL,
  `current` int(6) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL,
  `rule` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wxactivity
-- ----------------------------
DROP TABLE IF EXISTS `wxactivity`;
CREATE TABLE `wxactivity` (
  `id` char(32) NOT NULL,
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `pub_user` char(32) DEFAULT NULL COMMENT '发布人',
  `dep_id` int(11) DEFAULT NULL COMMENT '发布机构',
  `auditor` char(32) DEFAULT NULL COMMENT '审核人',
  `aud_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态,0：未审核；1：审核通过；2：审核未通过；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
