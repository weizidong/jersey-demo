/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : longquanyidb2.0

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-03-13 18:29:59
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
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `url` varchar(200) DEFAULT NULL COMMENT 'url地址',
  `fk` int(11) NOT NULL COMMENT '外键',
  `suffix` varchar(10) DEFAULT NULL COMMENT '后缀名',
  `userId` int(11) NOT NULL COMMENT '上传者',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志，0：未删除；1：回收站；2：永久删除',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `type` int(1) DEFAULT NULL COMMENT '类型，0：头像；1：附件；2：重要文件',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='文件实体类\r\n@author weizidong';

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
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '类型，0：管理员，1：普通用户，2：访客',
  `name` char(20) DEFAULT NULL COMMENT '姓名',
  `openid` varchar(36) DEFAULT NULL COMMENT '微信唯一标志',
  `auth` varchar(128) DEFAULT NULL COMMENT '权限',
  `uname` varchar(20) DEFAULT NULL COMMENT '账号',
  `pwd` char(32) DEFAULT NULL COMMENT '密码',
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '性别，0：未知，1：男，2：女',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证编号',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `dep_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `dep_name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '住址',
  `pic_url` varchar(128) DEFAULT NULL COMMENT '头像',
  `position` varchar(20) DEFAULT NULL COMMENT '岗位',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户实体类\r\n@author weizidong';
