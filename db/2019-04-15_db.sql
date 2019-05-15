/*
Navicat MySQL Data Transfer

Source Server         : TA聊
Source Server Version : 50725
Source Host           : 39.100.41.77:55623
Source Database       : ta_chat

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-15 18:33:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_activity_name` varchar(100) DEFAULT NULL,
  `t_activity_number` int(11) DEFAULT NULL,
  `t_join_term` int(11) DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.禁用',
  `t_begin_time` datetime DEFAULT NULL,
  `t_end_time` datetime DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activity
-- ----------------------------

-- ----------------------------
-- Table structure for t_activity_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_detail`;
CREATE TABLE `t_activity_detail` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_activity_id` int(11) DEFAULT NULL,
  `t_prize_name` varchar(100) DEFAULT NULL,
  `t_prize_size` varchar(20) DEFAULT NULL,
  `t_prize_number` int(11) DEFAULT NULL,
  `t_surplus_number` int(11) DEFAULT NULL,
  `t_is_join` int(11) DEFAULT NULL COMMENT '0.参与\r\n1.不参与',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activity_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_name` varchar(255) DEFAULT NULL,
  `t_pass_word` varchar(255) DEFAULT NULL,
  `t_role_id` int(11) DEFAULT NULL,
  `t_is_disable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.禁用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('8', 'xxb', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '0', '2018-09-10 11:01:47');
INSERT INTO `t_admin` VALUES ('11', 'test', '098F6BCD4621D373CADE4E832627B4F6', '1', '0', '2019-03-09 20:51:51');

-- ----------------------------
-- Table structure for t_album
-- ----------------------------
DROP TABLE IF EXISTS `t_album`;
CREATE TABLE `t_album` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_title` varchar(100) DEFAULT NULL,
  `t_fileId` varchar(100) DEFAULT NULL,
  `t_video_img` varchar(255) DEFAULT NULL,
  `t_addres_url` varchar(150) DEFAULT NULL,
  `t_file_type` int(11) DEFAULT NULL COMMENT '0.图片\r\n1.视频',
  `t_is_private` int(11) DEFAULT NULL COMMENT '0.否\r\n1.是',
  `t_see_count` int(11) DEFAULT NULL,
  `t_money` decimal(7,2) DEFAULT NULL,
  `t_is_del` int(11) DEFAULT NULL COMMENT '0.否\r\n1.是',
  `t_auditing_type` int(11) DEFAULT NULL COMMENT '0.未审核\r\n1.已审核\r\n2.审核失败',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_album
-- ----------------------------

-- ----------------------------
-- Table structure for t_alipay_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_alipay_setup`;
CREATE TABLE `t_alipay_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_alipay_appid` varchar(255) DEFAULT NULL,
  `t_alipay_public_key` varchar(3000) DEFAULT NULL,
  `t_alipay_private_key` varchar(3000) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_alipay_setup
-- ----------------------------

-- ----------------------------
-- Table structure for t_anchor
-- ----------------------------
DROP TABLE IF EXISTS `t_anchor`;
CREATE TABLE `t_anchor` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_state` int(11) DEFAULT NULL COMMENT '0.空闲1.忙碌2.离线',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_anchor
-- ----------------------------

-- ----------------------------
-- Table structure for t_anchor_devote
-- ----------------------------
DROP TABLE IF EXISTS `t_anchor_devote`;
CREATE TABLE `t_anchor_devote` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_anchor_id` int(11) DEFAULT NULL,
  `t_devote_value` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_anchor_devote
-- ----------------------------

-- ----------------------------
-- Table structure for t_anchor_guild
-- ----------------------------
DROP TABLE IF EXISTS `t_anchor_guild`;
CREATE TABLE `t_anchor_guild` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_guild_id` int(11) DEFAULT NULL,
  `t_anchor_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_anchor_guild
-- ----------------------------

-- ----------------------------
-- Table structure for t_anchor_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_anchor_setup`;
CREATE TABLE `t_anchor_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_video_gold` decimal(7,2) DEFAULT NULL,
  `t_text_gold` decimal(7,2) DEFAULT NULL,
  `t_phone_gold` decimal(7,2) DEFAULT NULL,
  `t_weixin_gold` decimal(7,2) DEFAULT NULL,
  `t_private_photo_gold` decimal(7,2) DEFAULT NULL,
  `t_private_video_gold` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_anchor_setup
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_role_id` int(11) DEFAULT NULL,
  `t_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2153 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('114', '2', '3');
INSERT INTO `t_authority` VALUES ('115', '2', '8');
INSERT INTO `t_authority` VALUES ('116', '2', '9');
INSERT INTO `t_authority` VALUES ('117', '2', '10');
INSERT INTO `t_authority` VALUES ('118', '2', '11');
INSERT INTO `t_authority` VALUES ('119', '2', '19');
INSERT INTO `t_authority` VALUES ('120', '2', '20');
INSERT INTO `t_authority` VALUES ('1164', '7', '3');
INSERT INTO `t_authority` VALUES ('1165', '7', '5');
INSERT INTO `t_authority` VALUES ('1166', '7', '6');
INSERT INTO `t_authority` VALUES ('1167', '7', '7');
INSERT INTO `t_authority` VALUES ('1168', '7', '8');
INSERT INTO `t_authority` VALUES ('1169', '7', '14');
INSERT INTO `t_authority` VALUES ('1170', '7', '16');
INSERT INTO `t_authority` VALUES ('1171', '7', '17');
INSERT INTO `t_authority` VALUES ('1172', '7', '43');
INSERT INTO `t_authority` VALUES ('1173', '7', '48');
INSERT INTO `t_authority` VALUES ('1174', '7', '57');
INSERT INTO `t_authority` VALUES ('1175', '7', '58');
INSERT INTO `t_authority` VALUES ('1176', '7', '4');
INSERT INTO `t_authority` VALUES ('1177', '7', '9');
INSERT INTO `t_authority` VALUES ('1178', '7', '10');
INSERT INTO `t_authority` VALUES ('1179', '7', '11');
INSERT INTO `t_authority` VALUES ('1180', '7', '12');
INSERT INTO `t_authority` VALUES ('1717', '3', '49');
INSERT INTO `t_authority` VALUES ('1718', '3', '50');
INSERT INTO `t_authority` VALUES ('1719', '3', '51');
INSERT INTO `t_authority` VALUES ('1720', '3', '65');
INSERT INTO `t_authority` VALUES ('1721', '3', '66');
INSERT INTO `t_authority` VALUES ('1722', '3', '67');
INSERT INTO `t_authority` VALUES ('2031', '8', '1');
INSERT INTO `t_authority` VALUES ('2032', '8', '2');
INSERT INTO `t_authority` VALUES ('2033', '8', '30');
INSERT INTO `t_authority` VALUES ('2034', '8', '31');
INSERT INTO `t_authority` VALUES ('2035', '8', '39');
INSERT INTO `t_authority` VALUES ('2036', '8', '41');
INSERT INTO `t_authority` VALUES ('2037', '8', '53');
INSERT INTO `t_authority` VALUES ('2038', '8', '54');
INSERT INTO `t_authority` VALUES ('2039', '8', '64');
INSERT INTO `t_authority` VALUES ('2040', '8', '69');
INSERT INTO `t_authority` VALUES ('2041', '8', '3');
INSERT INTO `t_authority` VALUES ('2042', '8', '5');
INSERT INTO `t_authority` VALUES ('2043', '8', '6');
INSERT INTO `t_authority` VALUES ('2044', '8', '7');
INSERT INTO `t_authority` VALUES ('2045', '8', '8');
INSERT INTO `t_authority` VALUES ('2046', '8', '14');
INSERT INTO `t_authority` VALUES ('2047', '8', '16');
INSERT INTO `t_authority` VALUES ('2048', '8', '17');
INSERT INTO `t_authority` VALUES ('2049', '8', '43');
INSERT INTO `t_authority` VALUES ('2050', '8', '48');
INSERT INTO `t_authority` VALUES ('2051', '8', '57');
INSERT INTO `t_authority` VALUES ('2052', '8', '58');
INSERT INTO `t_authority` VALUES ('2053', '8', '61');
INSERT INTO `t_authority` VALUES ('2054', '8', '63');
INSERT INTO `t_authority` VALUES ('2055', '8', '4');
INSERT INTO `t_authority` VALUES ('2056', '8', '9');
INSERT INTO `t_authority` VALUES ('2057', '8', '10');
INSERT INTO `t_authority` VALUES ('2058', '8', '11');
INSERT INTO `t_authority` VALUES ('2059', '8', '12');
INSERT INTO `t_authority` VALUES ('2060', '8', '68');
INSERT INTO `t_authority` VALUES ('2061', '8', '13');
INSERT INTO `t_authority` VALUES ('2062', '8', '15');
INSERT INTO `t_authority` VALUES ('2063', '8', '18');
INSERT INTO `t_authority` VALUES ('2064', '8', '19');
INSERT INTO `t_authority` VALUES ('2065', '8', '20');
INSERT INTO `t_authority` VALUES ('2066', '8', '21');
INSERT INTO `t_authority` VALUES ('2067', '8', '32');
INSERT INTO `t_authority` VALUES ('2068', '8', '26');
INSERT INTO `t_authority` VALUES ('2069', '8', '36');
INSERT INTO `t_authority` VALUES ('2070', '8', '42');
INSERT INTO `t_authority` VALUES ('2071', '8', '35');
INSERT INTO `t_authority` VALUES ('2072', '8', '37');
INSERT INTO `t_authority` VALUES ('2073', '8', '38');
INSERT INTO `t_authority` VALUES ('2074', '8', '45');
INSERT INTO `t_authority` VALUES ('2075', '8', '46');
INSERT INTO `t_authority` VALUES ('2076', '8', '47');
INSERT INTO `t_authority` VALUES ('2077', '8', '55');
INSERT INTO `t_authority` VALUES ('2078', '8', '56');
INSERT INTO `t_authority` VALUES ('2079', '8', '59');
INSERT INTO `t_authority` VALUES ('2080', '8', '60');
INSERT INTO `t_authority` VALUES ('2081', '8', '49');
INSERT INTO `t_authority` VALUES ('2082', '8', '50');
INSERT INTO `t_authority` VALUES ('2083', '8', '51');
INSERT INTO `t_authority` VALUES ('2084', '8', '65');
INSERT INTO `t_authority` VALUES ('2085', '8', '66');
INSERT INTO `t_authority` VALUES ('2086', '8', '67');
INSERT INTO `t_authority` VALUES ('2087', '1', '1');
INSERT INTO `t_authority` VALUES ('2088', '1', '2');
INSERT INTO `t_authority` VALUES ('2089', '1', '27');
INSERT INTO `t_authority` VALUES ('2090', '1', '28');
INSERT INTO `t_authority` VALUES ('2091', '1', '29');
INSERT INTO `t_authority` VALUES ('2092', '1', '30');
INSERT INTO `t_authority` VALUES ('2093', '1', '31');
INSERT INTO `t_authority` VALUES ('2094', '1', '39');
INSERT INTO `t_authority` VALUES ('2095', '1', '41');
INSERT INTO `t_authority` VALUES ('2096', '1', '53');
INSERT INTO `t_authority` VALUES ('2097', '1', '54');
INSERT INTO `t_authority` VALUES ('2098', '1', '64');
INSERT INTO `t_authority` VALUES ('2099', '1', '3');
INSERT INTO `t_authority` VALUES ('2100', '1', '5');
INSERT INTO `t_authority` VALUES ('2101', '1', '6');
INSERT INTO `t_authority` VALUES ('2102', '1', '7');
INSERT INTO `t_authority` VALUES ('2103', '1', '8');
INSERT INTO `t_authority` VALUES ('2104', '1', '14');
INSERT INTO `t_authority` VALUES ('2105', '1', '16');
INSERT INTO `t_authority` VALUES ('2106', '1', '17');
INSERT INTO `t_authority` VALUES ('2107', '1', '43');
INSERT INTO `t_authority` VALUES ('2108', '1', '48');
INSERT INTO `t_authority` VALUES ('2109', '1', '57');
INSERT INTO `t_authority` VALUES ('2110', '1', '58');
INSERT INTO `t_authority` VALUES ('2111', '1', '61');
INSERT INTO `t_authority` VALUES ('2112', '1', '63');
INSERT INTO `t_authority` VALUES ('2113', '1', '4');
INSERT INTO `t_authority` VALUES ('2114', '1', '9');
INSERT INTO `t_authority` VALUES ('2115', '1', '10');
INSERT INTO `t_authority` VALUES ('2116', '1', '11');
INSERT INTO `t_authority` VALUES ('2117', '1', '12');
INSERT INTO `t_authority` VALUES ('2118', '1', '68');
INSERT INTO `t_authority` VALUES ('2119', '1', '13');
INSERT INTO `t_authority` VALUES ('2120', '1', '15');
INSERT INTO `t_authority` VALUES ('2121', '1', '18');
INSERT INTO `t_authority` VALUES ('2122', '1', '19');
INSERT INTO `t_authority` VALUES ('2123', '1', '20');
INSERT INTO `t_authority` VALUES ('2124', '1', '21');
INSERT INTO `t_authority` VALUES ('2125', '1', '22');
INSERT INTO `t_authority` VALUES ('2126', '1', '23');
INSERT INTO `t_authority` VALUES ('2127', '1', '24');
INSERT INTO `t_authority` VALUES ('2128', '1', '25');
INSERT INTO `t_authority` VALUES ('2129', '1', '70');
INSERT INTO `t_authority` VALUES ('2130', '1', '71');
INSERT INTO `t_authority` VALUES ('2131', '1', '32');
INSERT INTO `t_authority` VALUES ('2132', '1', '26');
INSERT INTO `t_authority` VALUES ('2133', '1', '33');
INSERT INTO `t_authority` VALUES ('2134', '1', '34');
INSERT INTO `t_authority` VALUES ('2135', '1', '36');
INSERT INTO `t_authority` VALUES ('2136', '1', '42');
INSERT INTO `t_authority` VALUES ('2137', '1', '35');
INSERT INTO `t_authority` VALUES ('2138', '1', '37');
INSERT INTO `t_authority` VALUES ('2139', '1', '38');
INSERT INTO `t_authority` VALUES ('2140', '1', '45');
INSERT INTO `t_authority` VALUES ('2141', '1', '46');
INSERT INTO `t_authority` VALUES ('2142', '1', '47');
INSERT INTO `t_authority` VALUES ('2143', '1', '55');
INSERT INTO `t_authority` VALUES ('2144', '1', '56');
INSERT INTO `t_authority` VALUES ('2145', '1', '59');
INSERT INTO `t_authority` VALUES ('2146', '1', '60');
INSERT INTO `t_authority` VALUES ('2147', '1', '49');
INSERT INTO `t_authority` VALUES ('2148', '1', '50');
INSERT INTO `t_authority` VALUES ('2149', '1', '51');
INSERT INTO `t_authority` VALUES ('2150', '1', '65');
INSERT INTO `t_authority` VALUES ('2151', '1', '66');
INSERT INTO `t_authority` VALUES ('2152', '1', '67');

-- ----------------------------
-- Table structure for t_award_record
-- ----------------------------
DROP TABLE IF EXISTS `t_award_record`;
CREATE TABLE `t_award_record` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_activity_id` int(11) DEFAULT NULL,
  `t_prizedetai_id` int(11) DEFAULT NULL,
  `t_award_gold` int(11) DEFAULT NULL,
  `t_award_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_award_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_balance
-- ----------------------------
DROP TABLE IF EXISTS `t_balance`;
CREATE TABLE `t_balance` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_recharge_money` decimal(10,2) DEFAULT NULL,
  `t_profit_money` decimal(10,2) DEFAULT NULL,
  `t_share_money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_balance
-- ----------------------------

-- ----------------------------
-- Table structure for t_banned_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_banned_setup`;
CREATE TABLE `t_banned_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_count` int(11) DEFAULT NULL,
  `t_hours` decimal(5,2) DEFAULT NULL,
  `t_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_banned_setup
-- ----------------------------
INSERT INTO `t_banned_setup` VALUES ('1', '2', '1.00', '2018-08-09 09:44:04');
INSERT INTO `t_banned_setup` VALUES ('2', '3', '12.00', '2018-08-09 09:48:13');
INSERT INTO `t_banned_setup` VALUES ('3', '4', '24.00', '2018-08-09 09:49:00');
INSERT INTO `t_banned_setup` VALUES ('5', '1', '0.10', '2019-01-17 09:47:44');
INSERT INTO `t_banned_setup` VALUES ('7', '6', '-1.00', '2019-03-08 10:05:17');

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_img_url` varchar(255) DEFAULT NULL,
  `t_link_url` varchar(100) DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用1.禁用',
  `t_create_time` datetime DEFAULT NULL,
  `t_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_banner
-- ----------------------------

-- ----------------------------
-- Table structure for t_browse
-- ----------------------------
DROP TABLE IF EXISTS `t_browse`;
CREATE TABLE `t_browse` (
  `t_browse_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_browse_user` int(11) DEFAULT NULL,
  `t_cover_browse` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_browse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_browse
-- ----------------------------

-- ----------------------------
-- Table structure for t_call_log
-- ----------------------------
DROP TABLE IF EXISTS `t_call_log`;
CREATE TABLE `t_call_log` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_callout_user` int(11) DEFAULT NULL,
  `t_answer_user` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_call_time` int(11) DEFAULT NULL,
  `t_room_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_call_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_certification
-- ----------------------------
DROP TABLE IF EXISTS `t_certification`;
CREATE TABLE `t_certification` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_user_photo` varchar(100) DEFAULT NULL,
  `t_user_video` varchar(100) DEFAULT NULL,
  `t_nam` varchar(24) DEFAULT NULL,
  `t_id_card` varchar(18) DEFAULT NULL,
  `t_certification_type` int(11) DEFAULT NULL COMMENT '0.未审核\r\n1.审核成功\r\n2.审核失败',
  `t_describe` varchar(100) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_certification
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_com_user_id` int(11) DEFAULT NULL,
  `t_cover_user_id` int(11) DEFAULT NULL,
  `t_com_type` int(11) DEFAULT NULL COMMENT '0.评论\r\n1.回复评论',
  `t_comment` varchar(500) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_is_del` int(11) DEFAULT NULL COMMENT '0.未删除\r\n1.已删除',
  `t_dynamic_id` int(11) DEFAULT NULL,
  `t_is_examine` int(11) DEFAULT NULL,
  `t_is_read` int(11) DEFAULT NULL COMMENT '0.未读1.已读',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment_count
-- ----------------------------
DROP TABLE IF EXISTS `t_comment_count`;
CREATE TABLE `t_comment_count` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_mesg_count` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment_count
-- ----------------------------

-- ----------------------------
-- Table structure for t_coordinate
-- ----------------------------
DROP TABLE IF EXISTS `t_coordinate`;
CREATE TABLE `t_coordinate` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_lng` double DEFAULT NULL,
  `t_lat` double DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coordinate
-- ----------------------------

-- ----------------------------
-- Table structure for t_cover_examine
-- ----------------------------
DROP TABLE IF EXISTS `t_cover_examine`;
CREATE TABLE `t_cover_examine` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_img_url` varchar(150) DEFAULT NULL,
  `t_user_id` int(11) DEFAULT NULL,
  `t_first` int(11) DEFAULT NULL COMMENT '是否设置为第一涨\r\n0.是\r\n1.否',
  `t_is_examine` int(11) DEFAULT NULL COMMENT '0.未审核1.已审核',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cover_examine
-- ----------------------------

-- ----------------------------
-- Table structure for t_cps
-- ----------------------------
DROP TABLE IF EXISTS `t_cps`;
CREATE TABLE `t_cps` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_real_name` varchar(20) DEFAULT NULL,
  `t_settlement_type` int(11) DEFAULT NULL COMMENT '0.支付宝\r\n1.中国工商银行\r\n2.中国农业银行\r\n3.中国银行\r\n4.中国建设银行',
  `t_bank` varchar(50) DEFAULT NULL,
  `t_phone` varchar(11) DEFAULT NULL,
  `t_cps_name` varchar(50) DEFAULT NULL,
  `t_cps` varchar(100) DEFAULT NULL,
  `t_active_user` int(11) DEFAULT NULL,
  `t_proportions` int(11) DEFAULT NULL,
  `t_audit_status` int(11) DEFAULT NULL COMMENT '0.申请中\r\n1.已通过\r\n2.已下架',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cps
-- ----------------------------

-- ----------------------------
-- Table structure for t_cps_devote
-- ----------------------------
DROP TABLE IF EXISTS `t_cps_devote`;
CREATE TABLE `t_cps_devote` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_cps_id` int(11) DEFAULT NULL,
  `t_user_id` int(11) DEFAULT NULL,
  `t_devote_value` decimal(7,2) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_ratio` int(11) DEFAULT NULL,
  `t_recharge_money` decimal(7,2) DEFAULT NULL,
  `t_source_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cps_devote
-- ----------------------------

-- ----------------------------
-- Table structure for t_cps_settlement
-- ----------------------------
DROP TABLE IF EXISTS `t_cps_settlement`;
CREATE TABLE `t_cps_settlement` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_cps_id` int(11) DEFAULT NULL,
  `t_settlement_amount` decimal(9,2) DEFAULT NULL,
  `t_order_no` varchar(100) DEFAULT NULL,
  `t_settlement_type` int(11) DEFAULT NULL COMMENT '0.结算成功\r\n1.结算失败',
  `t_createt_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cps_settlement
-- ----------------------------

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_phone_type` varchar(200) DEFAULT NULL,
  `t_system_version` varchar(200) DEFAULT NULL,
  `t_ip_address` varchar(255) DEFAULT NULL,
  `t_referee_id` int(11) DEFAULT NULL,
  `t_is_use` int(11) DEFAULT NULL COMMENT '0.未使用\r\n1.已使用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device
-- ----------------------------

-- ----------------------------
-- Table structure for t_device_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_device_login_log`;
CREATE TABLE `t_device_login_log` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_device_number` varchar(255) DEFAULT NULL,
  `t_user_id` int(11) DEFAULT NULL,
  `t_user_ip` varchar(255) DEFAULT NULL,
  `t_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_disable
-- ----------------------------
DROP TABLE IF EXISTS `t_disable`;
CREATE TABLE `t_disable` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_context` varchar(255) DEFAULT NULL,
  `t_disable_time` int(11) DEFAULT NULL,
  `t_start_time` datetime DEFAULT NULL,
  `t_end_time` datetime DEFAULT NULL,
  `t_state` int(11) DEFAULT NULL COMMENT '0.生效中\r\n1.已失效',
  `t_operate_user` varchar(255) DEFAULT NULL COMMENT '操作人',
  `t_create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_disable
-- ----------------------------

-- ----------------------------
-- Table structure for t_discount
-- ----------------------------
DROP TABLE IF EXISTS `t_discount`;
CREATE TABLE `t_discount` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_num` int(11) DEFAULT NULL,
  `t_money` float DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.禁用',
  `t_crate_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_discount
-- ----------------------------

-- ----------------------------
-- Table structure for t_discuss_record
-- ----------------------------
DROP TABLE IF EXISTS `t_discuss_record`;
CREATE TABLE `t_discuss_record` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_label_id` int(11) DEFAULT NULL,
  `t_evaluation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_discuss_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_domainnamepool
-- ----------------------------
DROP TABLE IF EXISTS `t_domainnamepool`;
CREATE TABLE `t_domainnamepool` (
  `t_id` int(11) NOT NULL,
  `t_domain_name` varchar(100) DEFAULT NULL,
  `t_effect_type` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_domainnamepool
-- ----------------------------

-- ----------------------------
-- Table structure for t_domainNamePool
-- ----------------------------
DROP TABLE IF EXISTS `t_domainNamePool`;
CREATE TABLE `t_domainNamePool` (
  `t_id` int(11) NOT NULL,
  `t_domain_name` varchar(100) DEFAULT NULL,
  `t_effect_type` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_domainNamePool
-- ----------------------------

-- ----------------------------
-- Table structure for t_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamic`;
CREATE TABLE `t_dynamic` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_title` varchar(100) DEFAULT NULL,
  `t_content` varchar(250) CHARACTER SET utf8mb4 DEFAULT NULL,
  `t_see_count` int(11) DEFAULT NULL,
  `t_is_del` int(11) DEFAULT NULL COMMENT '0.否\r\n1.是',
  `t_auditing_type` int(11) DEFAULT NULL COMMENT '0.未审核\r\n1.已审核\r\n2.审核失败',
  `t_is_visible` int(11) DEFAULT NULL COMMENT '0.公开 1.关注',
  `t_address` varchar(255) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for t_dynamic_file
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamic_file`;
CREATE TABLE `t_dynamic_file` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_dynamic_id` int(11) DEFAULT NULL,
  `t_file_type` int(11) DEFAULT NULL COMMENT '0.图片\r\n1.视频',
  `t_cover_img_url` varchar(255) DEFAULT NULL COMMENT '封面地址',
  `t_file_url` varchar(255) DEFAULT NULL,
  `t_gold` decimal(11,2) DEFAULT NULL,
  `t_file_id` varchar(200) DEFAULT NULL,
  `t_video_time` varchar(20) DEFAULT NULL,
  `t_see_count` int(11) DEFAULT NULL COMMENT '查看数',
  `t_is_private` int(11) DEFAULT NULL COMMENT '0.否\r\n1.是',
  `t_is_del` int(11) DEFAULT NULL COMMENT '0.未删除\r\n1.已删除',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dynamic_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_enroll
-- ----------------------------
DROP TABLE IF EXISTS `t_enroll`;
CREATE TABLE `t_enroll` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_sex` int(11) DEFAULT NULL,
  `t_gold` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_enroll
-- ----------------------------
INSERT INTO `t_enroll` VALUES ('5', '0', '50', '2019-02-23 15:29:20');
INSERT INTO `t_enroll` VALUES ('6', '1', '20', '2019-02-26 22:14:57');

-- ----------------------------
-- Table structure for t_extract
-- ----------------------------
DROP TABLE IF EXISTS `t_extract`;
CREATE TABLE `t_extract` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_project_type` int(11) DEFAULT NULL COMMENT '0.平台抽成比例\r\n1.一级主播推广比例\r\n2.二级主播推广比例\r\n3.一级用户推广比例\r\n4.二级用户推广比例\r\n5.视频聊天\r\n6.文字聊天\r\n7.查看手机号\r\n8.查看微信号\r\n9.查看私密照片\r\n10.查看私密视频',
  `t_extract_ratio` varchar(100) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_extract
-- ----------------------------
INSERT INTO `t_extract` VALUES ('1', '0', '90', '2018-06-13 15:27:01');
INSERT INTO `t_extract` VALUES ('2', '1', '90', '2018-06-13 15:27:50');
INSERT INTO `t_extract` VALUES ('3', '2', '0', '2018-06-13 15:28:04');
INSERT INTO `t_extract` VALUES ('4', '3', '6', '2018-06-13 15:28:15');
INSERT INTO `t_extract` VALUES ('5', '4', '3', '2018-06-13 15:28:34');
INSERT INTO `t_extract` VALUES ('6', '5', '30', '2018-10-30 09:17:26');
INSERT INTO `t_extract` VALUES ('7', '6', '0.1', '2018-10-30 09:17:29');
INSERT INTO `t_extract` VALUES ('8', '7', '2000', '2018-10-30 09:17:32');
INSERT INTO `t_extract` VALUES ('9', '8', '2000', '2018-10-30 09:38:36');
INSERT INTO `t_extract` VALUES ('10', '9', '100,200,300,400', '2018-10-30 09:38:39');
INSERT INTO `t_extract` VALUES ('11', '10', '50,80,100,150,200,250', '2018-10-30 09:38:42');
INSERT INTO `t_extract` VALUES ('12', '11', '111', '2018-11-02 10:35:02');

-- ----------------------------
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_phone` varchar(20) DEFAULT NULL,
  `t_content` varchar(255) DEFAULT NULL,
  `t_img_url` varchar(500) DEFAULT NULL,
  `t_user_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_handle_res` varchar(500) DEFAULT NULL,
  `t_is_handle` int(11) DEFAULT NULL COMMENT '0.未处理 1.已处理',
  `t_handle_time` datetime DEFAULT NULL,
  `t_handle_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_follow_id` int(11) DEFAULT NULL,
  `t_cover_follow` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_follow
-- ----------------------------

-- ----------------------------
-- Table structure for t_free_anthor
-- ----------------------------
DROP TABLE IF EXISTS `t_free_anthor`;
CREATE TABLE `t_free_anthor` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_is_free` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_free_anthor
-- ----------------------------

-- ----------------------------
-- Table structure for t_gift
-- ----------------------------
DROP TABLE IF EXISTS `t_gift`;
CREATE TABLE `t_gift` (
  `t_gift_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_gift_name` varchar(100) DEFAULT NULL,
  `t_gift_gif_url` varchar(100) DEFAULT NULL,
  `t_gift_still_url` varchar(150) DEFAULT NULL,
  `t_gift_gold` decimal(15,2) DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.停用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_gift_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gift
-- ----------------------------
INSERT INTO `t_gift` VALUES ('2', '么么哒', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090515146274.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090515101119.png', '68.00', '0', '2018-11-09 17:15:18');
INSERT INTO `t_gift` VALUES ('3', '玫瑰花', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090524381375.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090524364434.png', '98.00', '0', '2018-11-09 17:24:41');
INSERT INTO `t_gift` VALUES ('4', '棒棒糖', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090525076453.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090525049892.png', '188.00', '0', '2018-11-09 17:25:09');
INSERT INTO `t_gift` VALUES ('5', '小红鞋', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090525374194.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090525355255.png', '268.00', '0', '2018-11-09 17:25:47');
INSERT INTO `t_gift` VALUES ('6', '皇冠', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090527052342.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090526594689.png', '666.00', '0', '2018-11-09 17:27:07');
INSERT INTO `t_gift` VALUES ('7', '戒子', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090527226576.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090527208486.png', '520.00', '0', '2018-12-01 01:08:04');
INSERT INTO `t_gift` VALUES ('8', '小花车', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090527451083.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090527425303.png', '1314.00', '0', '2018-11-09 17:27:49');
INSERT INTO `t_gift` VALUES ('9', '口红', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090528067398.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090528034626.png', '138.00', '0', '2018-12-01 01:09:13');
INSERT INTO `t_gift` VALUES ('10', '跑车', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090528333517.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181109/201811090528307808.png', '9999.00', '0', '2018-11-09 17:28:36');
INSERT INTO `t_gift` VALUES ('11', '星星', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260135265600.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260135165999.png', '128.00', '0', '2018-12-01 01:08:53');
INSERT INTO `t_gift` VALUES ('12', '梦幻城堡', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260136265468.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260136194641.png', '1880.00', '0', '2018-12-01 00:47:11');
INSERT INTO `t_gift` VALUES ('13', '生日蛋糕', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260137219165.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260136593077.png', '5200.00', '0', '2018-12-01 00:56:10');
INSERT INTO `t_gift` VALUES ('14', '法拉利', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260139204550.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260139114181.png', '52000.00', '0', '2018-12-01 01:10:40');
INSERT INTO `t_gift` VALUES ('15', '皇冠女王', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260140155280.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260140076552.png', '19999.00', '0', '2018-12-01 00:51:17');
INSERT INTO `t_gift` VALUES ('16', '指环', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260140504058.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260140467922.png', '1280.00', '0', '2018-12-01 00:56:45');
INSERT INTO `t_gift` VALUES ('17', '欢乐大火箭', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181201/201812010113397475.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181201/20181201011311248.gif', '1.00', '0', '2018-12-13 15:10:56');
INSERT INTO `t_gift` VALUES ('18', '玫瑰花', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260142158421.gif', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20181026/201810260142085580.png', '456.00', '1', '2018-10-26 13:42:26');
INSERT INTO `t_gift` VALUES ('19', '测试礼物', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20190129/201901290305028561.png', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20190129/201901290304515082.png', '11.00', '1', '2019-01-29 15:05:08');
INSERT INTO `t_gift` VALUES ('20', '丘比特', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20190224/201902240135011340.jpg', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/backstage/20190224/201902240134373367.jpg', '200.00', '0', '2019-02-24 13:37:44');

-- ----------------------------
-- Table structure for t_gift_record
-- ----------------------------
DROP TABLE IF EXISTS `t_gift_record`;
CREATE TABLE `t_gift_record` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_gift_type` int(11) DEFAULT NULL COMMENT '0.图片礼物\r\n1.红包礼物',
  `t_gift_id` int(11) DEFAULT NULL,
  `t_gift_num` int(11) DEFAULT NULL,
  `t_gift_gold` int(11) DEFAULT NULL,
  `t_record_user` int(11) DEFAULT NULL,
  `t_cover_user` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gift_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_give_gold_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_give_gold_notice`;
CREATE TABLE `t_give_gold_notice` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_gold` decimal(5,2) DEFAULT NULL,
  `t_is_read` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_give_gold_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_guild
-- ----------------------------
DROP TABLE IF EXISTS `t_guild`;
CREATE TABLE `t_guild` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_guild_name` varchar(50) DEFAULT NULL,
  `t_admin_name` varchar(20) DEFAULT NULL,
  `t_idcard` varchar(20) DEFAULT NULL,
  `t_hand_img` varchar(100) DEFAULT NULL,
  `t_admin_phone` varchar(20) DEFAULT NULL,
  `t_anchor_number` int(11) DEFAULT NULL,
  `t_extract` int(11) DEFAULT NULL,
  `t_examine` int(11) DEFAULT NULL COMMENT '0.未审核\r\n1.已审核',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_guild
-- ----------------------------

-- ----------------------------
-- Table structure for t_guild_invite
-- ----------------------------
DROP TABLE IF EXISTS `t_guild_invite`;
CREATE TABLE `t_guild_invite` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_guild_id` int(11) DEFAULT NULL,
  `t_anchor_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_guild_invite
-- ----------------------------

-- ----------------------------
-- Table structure for t_help_center
-- ----------------------------
DROP TABLE IF EXISTS `t_help_center`;
CREATE TABLE `t_help_center` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_title` varchar(255) DEFAULT NULL,
  `t_content` varchar(5000) DEFAULT NULL,
  `t_sort` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_help_center
-- ----------------------------

-- ----------------------------
-- Table structure for t_im_log
-- ----------------------------
DROP TABLE IF EXISTS `t_im_log`;
CREATE TABLE `t_im_log` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_send_user_id` int(11) DEFAULT NULL,
  `t_accept_user_id` int(11) DEFAULT NULL,
  `t_content` varchar(255) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_im_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_label
-- ----------------------------
DROP TABLE IF EXISTS `t_label`;
CREATE TABLE `t_label` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_label_name` varchar(100) DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.停用',
  `t_sex` int(11) DEFAULT NULL COMMENT '0.女\r\n1.男',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_label
-- ----------------------------
INSERT INTO `t_label` VALUES ('1', '很漂亮', '0', '0');
INSERT INTO `t_label` VALUES ('2', '性感', '0', '0');
INSERT INTO `t_label` VALUES ('3', '温柔', '0', '0');
INSERT INTO `t_label` VALUES ('4', '大长腿', '0', '0');
INSERT INTO `t_label` VALUES ('5', '完美身材', '0', '0');
INSERT INTO `t_label` VALUES ('6', '声音甜美', '0', '0');
INSERT INTO `t_label` VALUES ('7', '萌萌哒', '0', '0');
INSERT INTO `t_label` VALUES ('8', '清纯萝莉', '0', '0');
INSERT INTO `t_label` VALUES ('9', '御姐', '0', '0');
INSERT INTO `t_label` VALUES ('10', '性感火辣', '0', '0');
INSERT INTO `t_label` VALUES ('11', '波涛汹涌', '0', '0');
INSERT INTO `t_label` VALUES ('12', '会聊天', '0', '0');
INSERT INTO `t_label` VALUES ('13', '妩媚动人', '0', '0');
INSERT INTO `t_label` VALUES ('14', '温柔体贴', '0', '0');
INSERT INTO `t_label` VALUES ('15', '颜值高', '0', '0');
INSERT INTO `t_label` VALUES ('16', '舞姿美', '0', '0');
INSERT INTO `t_label` VALUES ('17', '梦中情人', '0', '0');
INSERT INTO `t_label` VALUES ('18', '很体贴', '0', '1');
INSERT INTO `t_label` VALUES ('19', '肌肉男', '0', '1');
INSERT INTO `t_label` VALUES ('20', '会聊天', '0', '1');
INSERT INTO `t_label` VALUES ('21', '情商高', '0', '1');
INSERT INTO `t_label` VALUES ('22', '帅哥', '0', '1');
INSERT INTO `t_label` VALUES ('23', '大叔范', '0', '1');
INSERT INTO `t_label` VALUES ('24', '会唱歌', '0', '1');
INSERT INTO `t_label` VALUES ('25', '白马王子', '0', '1');
INSERT INTO `t_label` VALUES ('26', '真男神', '0', '1');
INSERT INTO `t_label` VALUES ('27', '梦中情人', '0', '1');
INSERT INTO `t_label` VALUES ('28', '猛男', '0', '1');

-- ----------------------------
-- Table structure for t_log_time
-- ----------------------------
DROP TABLE IF EXISTS `t_log_time`;
CREATE TABLE `t_log_time` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_login_time` datetime DEFAULT NULL,
  `t_logout_time` datetime DEFAULT NULL,
  `t_duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log_time
-- ----------------------------

-- ----------------------------
-- Table structure for t_login_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_login_setup`;
CREATE TABLE `t_login_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_app_id` varchar(100) DEFAULT NULL,
  `t_app_secret` varchar(255) DEFAULT NULL,
  `t_type` int(11) DEFAULT NULL COMMENT '0.QQ互联\r\n1.微信互联',
  `t_state` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.禁用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login_setup
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_menu_name` varchar(100) DEFAULT NULL,
  `t_menu_url` varchar(255) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_father_id` int(11) DEFAULT NULL,
  `t_icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '仪表盘', '/menu/jumpHome.htm', '2018-08-04 16:32:10', '0', '<i class=\"fa fa-home nav_icon\"></i>');
INSERT INTO `t_menu` VALUES ('2', '系统管理', '#', '2018-08-04 10:02:00', '0', '<i 	class=\"fa fa-cogs\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('3', ' 用户管理', '#', '2018-08-03 17:18:25', '0', '<i class=\"glyphicon glyphicon-user nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('4', '财务管理', '#', '2018-08-04 09:44:47', '0', '<i class=\"glyphicon glyphicon-usd nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('5', '添加用户', '/menu/addUser.htm', '2018-08-04 09:46:22', '3', '');
INSERT INTO `t_menu` VALUES ('6', '用户列表', '/menu/userList.htm', '2018-08-04 09:48:57', '3', '');
INSERT INTO `t_menu` VALUES ('7', '封号用户', '/menu/jumpFreezeList.htm', '2018-08-04 09:50:22', '3', '');
INSERT INTO `t_menu` VALUES ('8', '禁用用户', '/menu/disableUserList.htm', '2018-08-04 09:54:25', '3', '');
INSERT INTO `t_menu` VALUES ('9', '财务报表', '/menu/jumpFinancialDetails.htm', '2018-08-04 09:54:55', '4', '');
INSERT INTO `t_menu` VALUES ('10', '充值记录', '/menu/jumpRechargeList.htm', '2018-08-04 09:55:16', '4', '');
INSERT INTO `t_menu` VALUES ('11', '消费记录', '/menu/jumpConsumeList.htm', '2018-08-04 09:57:00', '4', '');
INSERT INTO `t_menu` VALUES ('12', '提现记录', '/menu/jumpPutForwardList.htm', '2018-08-04 09:57:26', '4', '');
INSERT INTO `t_menu` VALUES ('13', '礼物管理', '/menu/jumpGiftList.htm', '2018-08-04 09:58:29', '0', '<i class=\"glyphicon glyphicon-gift nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('14', '封面审核', '/menu/jumpCoverList.htm', '2018-08-04 09:58:41', '3', '<i class=\"glyphicon glyphicon-picture nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('15', '广告管理', '/menu/jumpBanner.htm', '2018-08-04 09:58:53', '0', '<i class=\"glyphicon glyphicon-blackboard nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('16', '相册审核', '/menu/jumpPrivatePhotoList.htm', '2018-08-04 09:59:11', '3', '<i class=\"glyphicon glyphicon-fire nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('17', '实名认证', '/menu/jumpidCardList.htm', '2018-08-04 09:59:28', '3', '<i class=\"fa fa-bar-chart nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('18', '意见反馈', '/menu/jumpFeedbackList.htm', '2018-08-04 09:59:41', '0', '<i class=\"glyphicon glyphicon-paperclip nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('19', '举报列表', '/menu/jumpReportList.htm', '2018-08-04 09:59:54', '0', '<i class=\"fa fa-exclamation-triangle\"></i>');
INSERT INTO `t_menu` VALUES ('20', 'VIP套餐', '/menu/jumpVipSetMealList.htm', '2018-08-04 10:00:09', '0', '<i class=\"glyphicon glyphicon-piggy-bank nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('21', '充值与提现设置', '/menu/jumpCashValueSetMealList.htm', '2018-08-04 10:00:27', '0', '<i class=\"glyphicon glyphicon-btc nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('22', '三方设置', '#', '2018-08-04 10:00:51', '0', '<i class=\"glyphicon glyphicon-wrench nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('23', '短信配置', '/admin/jumpSmsList.htm', '2018-08-04 10:01:09', '22', '');
INSERT INTO `t_menu` VALUES ('24', '登陆配置', '/admin/loginSetUp.htm', '2018-08-04 10:01:23', '22', '');
INSERT INTO `t_menu` VALUES ('25', '对象存储配置', '/admin/jumpOssList.htm', '2018-08-04 10:01:45', '22', '');
INSERT INTO `t_menu` VALUES ('26', '推广设置', '/menu/jumpSpreadSetUp.htm', '2018-08-04 10:02:24', '32', '');
INSERT INTO `t_menu` VALUES ('27', '角色列表', '/menu/jumpRoleList.htm', '2018-08-04 10:02:44', '2', '');
INSERT INTO `t_menu` VALUES ('28', '账号列表', '/menu/jumpAdminList.htm', '2018-08-04 10:03:08', '2', '');
INSERT INTO `t_menu` VALUES ('29', '菜单列表', '/menu/jumpMenuList.htm', '2018-08-04 10:03:23', '2', '');
INSERT INTO `t_menu` VALUES ('30', '封号设置', '/menu/jumpBannedList.htm', '2018-08-08 17:33:14', '2', '');
INSERT INTO `t_menu` VALUES ('31', 'APP版本控制', '/menu/jumpVersion.htm', '2018-08-24 16:03:34', '2', '');
INSERT INTO `t_menu` VALUES ('32', '活动推广', '#', '2018-09-04 13:55:17', '0', '<i class=\"glyphicon glyphicon-folder-close nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('33', '活动项目', '/menu/jumpActivityList.htm', '2018-09-04 13:55:59', '32', '');
INSERT INTO `t_menu` VALUES ('34', '中奖明细', '/menu/jumpRewardDetail.htm', '2018-09-04 13:56:22', '32', '');
INSERT INTO `t_menu` VALUES ('35', '公会管理', '/menu/jumpGuildList.htm', '2018-09-12 13:55:25', '0', '<i class=\"glyphicon glyphicon-th nav-icon\"></i>');
INSERT INTO `t_menu` VALUES ('36', '注册红包', '/menu/jumpRegisterRedBag.htm', '2018-09-12 16:33:39', '32', '');
INSERT INTO `t_menu` VALUES ('37', '系统推送', '/menu/jumpPushMsgList.htm', '2018-09-13 09:17:29', '0', '<i class=\"glyphicon glyphicon-repeat nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('38', 'CPS联盟管理', '/menu/jumpCPSList.htm', '2018-09-14 15:17:25', '0', '<i class=\"glyphicon glyphicon-globe nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('39', '排行榜设置', '/menu/jumpRankingList.htm', '2018-09-27 14:15:14', '2', '');
INSERT INTO `t_menu` VALUES ('41', 'APP风格切换', '/menu/jumpStyleSetUp.htm', '2018-10-10 14:31:10', '2', '');
INSERT INTO `t_menu` VALUES ('42', '推广奖励', '/menu/jumpSpreadAward.htm', '2018-10-19 09:03:52', '32', '');
INSERT INTO `t_menu` VALUES ('43', 'IM消息', '/menu/jumpImLogList.htm', '2018-10-20 09:37:59', '3', '');
INSERT INTO `t_menu` VALUES ('45', '排行榜', '#', '2018-11-02 10:51:47', '0', '<i class=\"glyphicon glyphicon-flag nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('46', '推广用户排行榜', '/menu/jumpSpreadUserList.htm', '2018-11-02 10:58:34', '45', '');
INSERT INTO `t_menu` VALUES ('47', '在线时长排行榜', '/menu/jumpOnLineTime.htm', '2018-11-02 10:59:09', '45', '');
INSERT INTO `t_menu` VALUES ('48', '用户分布', '/menu/jumpMap.htm', '2018-11-16 09:54:24', '3', '');
INSERT INTO `t_menu` VALUES ('49', '代理管理', '#', '2018-11-26 16:46:27', '0', '<i class=\"glyphicon glyphicon-retweet nav-icon\" aria-hidden=\"true\"></i>');
INSERT INTO `t_menu` VALUES ('50', '个人中心', '/menu/jumpAddSpread.htm', '2018-11-26 16:46:53', '49', '');
INSERT INTO `t_menu` VALUES ('51', '代理列表', '/menu/jumpSpread.htm', '2018-11-26 16:47:07', '49', '');
INSERT INTO `t_menu` VALUES ('53', '系统设置', '/menu/jumpSystemSetUp.htm', '2018-12-07 13:41:10', '2', '');
INSERT INTO `t_menu` VALUES ('54', '模拟消息', '/menu/jumpSimulationList.htm', '2018-12-07 15:58:10', '2', '');
INSERT INTO `t_menu` VALUES ('55', '消费排行榜', '/menu/jumpConsumeNoticeList.htm', '2018-12-13 10:57:53', '45', '');
INSERT INTO `t_menu` VALUES ('56', '提现排行榜', '/menu/jumpExtractMoney.htm', '2018-12-13 10:58:12', '45', '');
INSERT INTO `t_menu` VALUES ('57', '动态列表', '/menu/jumpDynamicList.htm', '2018-12-18 09:29:56', '3', '');
INSERT INTO `t_menu` VALUES ('58', '评论列表', '/menu/jumpCommentList.htm', '2019-01-03 10:56:56', '3', '');
INSERT INTO `t_menu` VALUES ('59', '充值排行榜', '/menu/jumpBankRankingList.htm', '2019-01-04 13:48:38', '45', '');
INSERT INTO `t_menu` VALUES ('60', '余额排行榜', '/menu/jumpBalanList.htm', '2019-01-04 13:49:06', '45', '');
INSERT INTO `t_menu` VALUES ('61', '速配时长', '/menu/jumpSpeedDatingList.htm', '2019-02-15 10:32:20', '3', '');
INSERT INTO `t_menu` VALUES ('63', '速配管理', '/menu/jumpSpredManageList.htm', '2019-03-01 09:29:30', '3', '');
INSERT INTO `t_menu` VALUES ('64', '帮助中心', '/menu/jumpHelpCenter.htm', '2019-03-01 15:58:32', '2', '');
INSERT INTO `t_menu` VALUES ('65', '域名池', '/menu/jumpDomainList.htm', '2019-03-02 09:45:47', '49', '');
INSERT INTO `t_menu` VALUES ('66', '宣传海报', '/menu/jumpSpreadImgList.htm', '2019-03-02 09:48:00', '49', '');
INSERT INTO `t_menu` VALUES ('67', '我的收益', '/menu/jumpMyProfit.htm', '2019-03-02 15:19:09', '49', '');
INSERT INTO `t_menu` VALUES ('68', '后台充值', '/menu/jumpGiveList.htm', '2019-03-05 14:12:10', '4', '');
INSERT INTO `t_menu` VALUES ('70', '支付宝设置', '/menu/jumpAlipaySetUp.htm', '2019-03-25 19:11:53', '22', '');
INSERT INTO `t_menu` VALUES ('71', '微信支付设置', '/menu/jumpWeiXinPaySetUp.htm', '2019-03-25 19:12:07', '22', '');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_message_content` varchar(255) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_is_see` int(11) DEFAULT NULL COMMENT '0.未查看\r\n1.以查看',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_object_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_object_storage`;
CREATE TABLE `t_object_storage` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_app_id` varchar(100) DEFAULT NULL,
  `t_secret_id` varchar(100) DEFAULT NULL,
  `t_secret_key` varchar(255) DEFAULT NULL,
  `t_bucket` varchar(100) DEFAULT NULL,
  `t_region` varchar(100) DEFAULT NULL,
  `t_img_url` varchar(255) DEFAULT NULL,
  `t_state` int(11) DEFAULT NULL COMMENT '0.正常1.禁用',
  `t_type` int(11) DEFAULT NULL COMMENT '0.腾讯云1.阿里云2.网易云',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_object_storage
-- ----------------------------
INSERT INTO `t_object_storage` VALUES ('1', '1256929999', 'AKIDNgpocKHTyJTXQ8N3EE7rQo363qU1Mdie', '8LUSFrIl5JME4kFds1zmOwKgw45ygFe4', 'img-1256929999', 'ap-chengdu', 'https://img-1256929999.cos.ap-chengdu.myqcloud.com/', '0', '0');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_consume` int(11) DEFAULT NULL,
  `t_cover_consume` int(11) DEFAULT NULL,
  `t_consume_type` int(11) DEFAULT NULL COMMENT '0.充值\r\n1.聊天\r\n2.视频\r\n3.私密照片\r\n4.私密视频\r\n5.查看手机\r\n6.查看微信\r\n7.红包\r\n8.VIP\r\n9.礼物\r\n10.提现\r\n11.推荐分成 \r\n12.提现失败返回\r\n13.注册赠送\r\n14.公会收入',
  `t_consume_score` int(11) DEFAULT NULL COMMENT '消费来源ID(视频聊天和文字聊天为0)',
  `t_amount` decimal(10,2) DEFAULT NULL,
  `t_room_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_log_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_platform_income
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_income`;
CREATE TABLE `t_platform_income` (
  `t_id` int(11) NOT NULL,
  `t_gold` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_platform_income
-- ----------------------------
INSERT INTO `t_platform_income` VALUES ('1', '1555138.72');

-- ----------------------------
-- Table structure for t_praise
-- ----------------------------
DROP TABLE IF EXISTS `t_praise`;
CREATE TABLE `t_praise` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_praise_user` int(11) DEFAULT NULL,
  `t_cover_praise_user` int(11) DEFAULT NULL,
  `t_dynamic_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_praise
-- ----------------------------

-- ----------------------------
-- Table structure for t_private_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_private_collection`;
CREATE TABLE `t_private_collection` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_album_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_private_collection
-- ----------------------------

-- ----------------------------
-- Table structure for t_profit_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_profit_detail`;
CREATE TABLE `t_profit_detail` (
  `t_profit_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_profit_type` int(11) DEFAULT NULL COMMENT '1.聊天\r\n2.视频\r\n3.私密照片\r\n4.私密视频\r\n5.查看手机\r\n6.查看微信\r\n7.红包\r\n8.VIP\r\n9.礼物\r\n',
  `t_profit_gold` decimal(10,2) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_profit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_profit_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_put_forward
-- ----------------------------
DROP TABLE IF EXISTS `t_put_forward`;
CREATE TABLE `t_put_forward` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_order_no` varchar(100) DEFAULT NULL,
  `t_money` decimal(10,2) DEFAULT NULL,
  `t_setmeal_id` int(11) DEFAULT NULL,
  `t_describe` varchar(100) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_order_state` int(11) DEFAULT NULL COMMENT '0.待审核\r\n1.已审核待打款\r\n2.已打款\r\n3.打款失败',
  `t_handle_time` datetime DEFAULT NULL,
  `t_income_gold` decimal(10,2) DEFAULT NULL,
  `t_share_gold` decimal(10,2) DEFAULT NULL,
  `t_data_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_put_forward
-- ----------------------------

-- ----------------------------
-- Table structure for t_put_forward_data
-- ----------------------------
DROP TABLE IF EXISTS `t_put_forward_data`;
CREATE TABLE `t_put_forward_data` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_real_name` varchar(20) DEFAULT NULL,
  `t_nick_name` varchar(100) DEFAULT NULL,
  `t_head_img` varchar(255) DEFAULT NULL,
  `t_account_number` varchar(100) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_type` int(11) DEFAULT NULL COMMENT '0.支付宝\r\n1.微信',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_put_forward_data
-- ----------------------------

-- ----------------------------
-- Table structure for t_ranking_control
-- ----------------------------
DROP TABLE IF EXISTS `t_ranking_control`;
CREATE TABLE `t_ranking_control` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_charm_number` int(11) DEFAULT NULL,
  `t_consumption_number` int(11) DEFAULT NULL,
  `t_courtesy_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ranking_control
-- ----------------------------
INSERT INTO `t_ranking_control` VALUES ('1', '10', '10', '10');

-- ----------------------------
-- Table structure for t_reception_rate
-- ----------------------------
DROP TABLE IF EXISTS `t_reception_rate`;
CREATE TABLE `t_reception_rate` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_count` int(11) DEFAULT NULL,
  `t_reception_count` int(11) DEFAULT NULL,
  `t_refuse_count` int(11) DEFAULT NULL,
  `t_reception` float DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reception_rate
-- ----------------------------

-- ----------------------------
-- Table structure for t_recharge
-- ----------------------------
DROP TABLE IF EXISTS `t_recharge`;
CREATE TABLE `t_recharge` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_recharge_money` decimal(10,2) DEFAULT NULL,
  `t_order_no` varchar(100) DEFAULT NULL,
  `t_tripartite_order` varchar(100) DEFAULT NULL,
  `t_recharge_type` int(11) DEFAULT NULL COMMENT '0.VIP\r\n1.金币',
  `t_payment_type` int(11) DEFAULT NULL COMMENT '0.支付宝\r\n1.微信',
  `t_setmeal_id` int(11) DEFAULT NULL COMMENT '充值VIP不存在',
  `t_order_state` int(11) DEFAULT NULL COMMENT '0.创建订单\r\n1.订单已支付\r\n2.放弃支付',
  `t_create_time` datetime DEFAULT NULL,
  `t_fulfil_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for t_redpacket_log
-- ----------------------------
DROP TABLE IF EXISTS `t_redpacket_log`;
CREATE TABLE `t_redpacket_log` (
  `t_redpacket_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_hair_userId` int(11) DEFAULT NULL,
  `t_receive_userId` int(11) DEFAULT NULL,
  `t_redpacket_content` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `t_redpacket_gold` decimal(10,2) DEFAULT NULL,
  `t_redpacket_draw` int(11) DEFAULT NULL COMMENT '0.未领取\r\n1.已领取',
  `t_redpacket_type` int(11) DEFAULT NULL COMMENT '0.赠送红包\r\n1.贡献红包 \r\n2.主播认证红包\r\n3.后台添加红包',
  `t_create_time` datetime DEFAULT NULL,
  `t_order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_redpacket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_redpacket_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_cover_user_id` int(11) DEFAULT NULL,
  `t_comment` varchar(255) DEFAULT NULL,
  `t_img` varchar(500) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_is_handle` int(11) DEFAULT NULL COMMENT '0.未处理\r\n1.已处理\r\n',
  `t_handle_comment` varchar(255) DEFAULT NULL,
  `t_handle_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_role_name` varchar(50) DEFAULT NULL,
  `t_enable` int(11) DEFAULT NULL COMMENT '0.启用1.禁用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '0', '2018-08-03 14:26:45');
INSERT INTO `t_role` VALUES ('2', '财务', '0', '2019-01-27 18:07:06');
INSERT INTO `t_role` VALUES ('3', '代理', '0', '2018-11-21 10:28:33');
INSERT INTO `t_role` VALUES ('5', '工会', '0', '2018-12-03 12:07:51');

-- ----------------------------
-- Table structure for t_room_time
-- ----------------------------
DROP TABLE IF EXISTS `t_room_time`;
CREATE TABLE `t_room_time` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_room_id` int(11) DEFAULT NULL,
  `t_call_user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `t_answer_user_id` int(11) DEFAULT NULL COMMENT '主播编号',
  `t_an_vi_gold` decimal(11,2) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_room_time
-- ----------------------------

-- ----------------------------
-- Table structure for t_set_meal
-- ----------------------------
DROP TABLE IF EXISTS `t_set_meal`;
CREATE TABLE `t_set_meal` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_project_type` int(11) DEFAULT NULL COMMENT '1.充值\r\n2.提现',
  `t_gold` int(11) DEFAULT NULL,
  `t_money` decimal(7,2) DEFAULT NULL,
  `t_end_type` int(11) DEFAULT NULL COMMENT '0.Android 1.phone',
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.停用',
  `t_create_time` datetime DEFAULT NULL,
  `t_describe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_set_meal
-- ----------------------------
INSERT INTO `t_set_meal` VALUES ('1', '1', '140', '9.00', '1', '0', '2018-06-11 14:01:55', null);
INSERT INTO `t_set_meal` VALUES ('2', '1', '280', '28.00', '0', '0', '2018-06-11 14:02:12', null);
INSERT INTO `t_set_meal` VALUES ('3', '2', '600', '50.00', '1', '0', '2018-06-11 14:02:34', null);
INSERT INTO `t_set_meal` VALUES ('5', '2', '50', '50.00', '0', '0', '2018-06-11 14:04:14', null);
INSERT INTO `t_set_meal` VALUES ('6', '2', '1000', '100.00', '0', '0', '2018-06-11 14:04:35', null);
INSERT INTO `t_set_meal` VALUES ('7', '1', '1000', '100.00', '0', '0', '2018-07-20 14:50:07', null);
INSERT INTO `t_set_meal` VALUES ('21', '2', '1000', '100.00', '0', '0', '2018-09-04 14:40:22', null);
INSERT INTO `t_set_meal` VALUES ('30', '1', '10', '1.00', '0', '0', '2019-02-20 17:01:09', null);
INSERT INTO `t_set_meal` VALUES ('31', '1', '280', '28.00', '1', '0', '2019-02-20 17:01:22', null);
INSERT INTO `t_set_meal` VALUES ('32', '1', '1000', '100.00', '1', '0', '2019-02-20 17:01:31', null);
INSERT INTO `t_set_meal` VALUES ('33', '1', '2000', '200.00', '0', '0', '2019-02-22 09:43:33', null);
INSERT INTO `t_set_meal` VALUES ('34', '1', '5000', '500.00', '0', '0', '2019-02-22 09:43:44', null);
INSERT INTO `t_set_meal` VALUES ('35', '1', '10000', '1000.00', '0', '0', '2019-02-22 09:43:52', null);
INSERT INTO `t_set_meal` VALUES ('36', '1', '2000', '200.00', '1', '0', '2019-02-22 09:44:03', null);
INSERT INTO `t_set_meal` VALUES ('37', '1', '5000', '500.00', '1', '0', '2019-02-22 09:44:14', null);
INSERT INTO `t_set_meal` VALUES ('38', '1', '10000', '1000.00', '1', '0', '2019-02-22 09:44:26', null);
INSERT INTO `t_set_meal` VALUES ('39', '2', '30', '1.00', '1', '0', '2019-03-12 22:06:38', null);
INSERT INTO `t_set_meal` VALUES ('40', '1', '10', '1.00', '1', '0', '2019-03-13 09:40:37', null);

-- ----------------------------
-- Table structure for t_share_notes
-- ----------------------------
DROP TABLE IF EXISTS `t_share_notes`;
CREATE TABLE `t_share_notes` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_target` int(11) DEFAULT NULL COMMENT '0.QQ群\r\n1.微信群',
  `t_date` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_share_notes
-- ----------------------------

-- ----------------------------
-- Table structure for t_simulation
-- ----------------------------
DROP TABLE IF EXISTS `t_simulation`;
CREATE TABLE `t_simulation` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_centent` varchar(255) DEFAULT NULL,
  `t_sex` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_simulation
-- ----------------------------
INSERT INTO `t_simulation` VALUES ('1', '你好，在干什么呢？', '1', '2018-12-13 09:55:23');
INSERT INTO `t_simulation` VALUES ('2', '亲，这会方便吗？', '1', '2018-12-13 09:55:39');
INSERT INTO `t_simulation` VALUES ('3', '不套路，无忽悠，绝对满意！', '1', '2018-12-13 09:55:55');
INSERT INTO `t_simulation` VALUES ('4', '好无聊，想找个人聊聊！', '1', '2018-12-13 15:15:22');

-- ----------------------------
-- Table structure for t_sms_steup
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_steup`;
CREATE TABLE `t_sms_steup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(100) DEFAULT NULL,
  `appkey` varchar(255) DEFAULT NULL,
  `templateId` varchar(200) DEFAULT NULL,
  `smsSign` varchar(50) DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用1.停用',
  `t_platform_type` int(11) DEFAULT NULL COMMENT '0.腾讯云1.阿里云3.网易云',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sms_steup
-- ----------------------------

-- ----------------------------
-- Table structure for t_speed_manage
-- ----------------------------
DROP TABLE IF EXISTS `t_speed_manage`;
CREATE TABLE `t_speed_manage` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_begin_time` varchar(10) DEFAULT NULL,
  `t_end_time` varchar(10) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_speed_manage
-- ----------------------------

-- ----------------------------
-- Table structure for t_speeddating
-- ----------------------------
DROP TABLE IF EXISTS `t_speeddating`;
CREATE TABLE `t_speeddating` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_begin_time` datetime DEFAULT NULL,
  `t_end_time` datetime DEFAULT NULL,
  `t_duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_speeddating
-- ----------------------------

-- ----------------------------
-- Table structure for t_spread
-- ----------------------------
DROP TABLE IF EXISTS `t_spread`;
CREATE TABLE `t_spread` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_is_nominate` int(11) DEFAULT NULL,
  `t_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_spread
-- ----------------------------

-- ----------------------------
-- Table structure for t_spread_award
-- ----------------------------
DROP TABLE IF EXISTS `t_spread_award`;
CREATE TABLE `t_spread_award` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_gold` int(11) DEFAULT NULL,
  `t_sex` int(11) DEFAULT NULL COMMENT '0.女\r\n1.男',
  `t_rank` int(11) DEFAULT NULL COMMENT '1.一级推广\r\n2.二级推广',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_spread_award
-- ----------------------------

-- ----------------------------
-- Table structure for t_spread_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_spread_channel`;
CREATE TABLE `t_spread_channel` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_real_name` varchar(20) DEFAULT NULL,
  `t_settlement_type` int(11) DEFAULT NULL COMMENT '0.支付宝\r\n1.中国工商银行\r\n2.中国农业银行\r\n3.中国银行\r\n4.中国建设银行',
  `t_bank` varchar(50) DEFAULT NULL,
  `t_weixin` varchar(100) DEFAULT NULL,
  `t_qq` varchar(20) DEFAULT NULL,
  `t_phone` varchar(11) DEFAULT NULL,
  `t_gold_proportions` int(11) DEFAULT NULL,
  `t_vip_proportions` int(11) DEFAULT NULL,
  `t_spread_id` int(11) DEFAULT NULL,
  `t_spread_type` int(11) DEFAULT NULL COMMENT '0.正常 1.已下架',
  `t_create_time` datetime DEFAULT NULL,
  `t_short_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_spread_channel
-- ----------------------------

-- ----------------------------
-- Table structure for t_spread_login
-- ----------------------------
DROP TABLE IF EXISTS `t_spread_login`;
CREATE TABLE `t_spread_login` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_name` varchar(255) DEFAULT NULL,
  `t_pass_word` varchar(255) DEFAULT NULL,
  `t_role_id` int(11) DEFAULT NULL,
  `t_user_id` int(11) DEFAULT NULL,
  `t_is_disable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.禁用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_spread_login
-- ----------------------------

-- ----------------------------
-- Table structure for t_spreed_img
-- ----------------------------
DROP TABLE IF EXISTS `t_spreed_img`;
CREATE TABLE `t_spreed_img` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_img_path` varchar(255) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_spreed_img
-- ----------------------------

-- ----------------------------
-- Table structure for t_style_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_style_setup`;
CREATE TABLE `t_style_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_style_name` varchar(100) DEFAULT NULL,
  `t_mark` varchar(100) DEFAULT NULL,
  `t_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_style_setup
-- ----------------------------
INSERT INTO `t_style_setup` VALUES ('1', '大图', 'one', '0');
INSERT INTO `t_style_setup` VALUES ('2', '十字', 'two', '1');

-- ----------------------------
-- Table structure for t_system_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_system_setup`;
CREATE TABLE `t_system_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_channel` varchar(255) DEFAULT NULL,
  `t_union` varchar(255) DEFAULT NULL,
  `t_take_out_count` int(11) DEFAULT NULL,
  `t_scope` int(11) DEFAULT NULL COMMENT '范围(公里)',
  `t_android_download` varchar(255) DEFAULT NULL,
  `t_ios_download` varchar(255) DEFAULT NULL,
  `t_award_rules` varchar(500) DEFAULT NULL,
  `t_system_lang_girl` varchar(500) DEFAULT NULL,
  `t_system_lang_male` varchar(500) DEFAULT NULL,
  `t_default_text` decimal(11,2) DEFAULT NULL,
  `t_default_video` decimal(11,2) DEFAULT NULL,
  `t_default_phone` decimal(11,2) DEFAULT NULL,
  `t_default_weixin` decimal(11,2) DEFAULT NULL,
  `t_service_qq` varchar(255) DEFAULT NULL,
  `t_nickname_filter` varchar(255) DEFAULT NULL,
  `t_video_hint` varchar(255) DEFAULT NULL,
  `t_spreed_hint` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_setup
-- ----------------------------
INSERT INTO `t_system_setup` VALUES ('1', 'mmm500005', 'http://1-liao.com', null, '15', 'https://91apt.cn/K6poD1', 'https://fir.im/lzuk', '可以在后台填写\n1、注册奖励\n   每邀请一个真实男用户注册第一级获得20个金币，第二级获得5个金币。\n2、永久收益\n         你邀请的男用户充值,第一级获得消费的5%,第二级获得消费的3%;\n        邀请的女主播获得其收益的10%,第二级获得收益的5%。', '您好，欢迎女神', '你好。欢迎男神', '0.10', '10.00', '500.00', '500.00', '3110078168', '招主播,加QQ,加微信', '我是测试', '我是测试1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_idcard` int(11) DEFAULT NULL,
  `t_nickName` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `t_sex` int(11) DEFAULT NULL COMMENT '0.女\r\n1.男',
  `t_age` int(11) DEFAULT NULL,
  `t_handImg` varchar(200) DEFAULT NULL,
  `t_cover_img` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `t_phone` varchar(11) DEFAULT NULL,
  `t_height` int(11) DEFAULT NULL,
  `t_weight` float DEFAULT NULL,
  `t_constellation` varchar(50) DEFAULT NULL,
  `t_city` varchar(50) DEFAULT NULL,
  `t_synopsis` varchar(200) DEFAULT NULL,
  `t_autograph` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `t_vocation` varchar(40) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  `t_referee` int(11) DEFAULT NULL,
  `t_role` int(11) DEFAULT NULL COMMENT '0.普通用户\r\n1.主播',
  `t_disable` int(11) DEFAULT NULL COMMENT '0.正常\r\n1.封号\r\n2.禁用',
  `t_login_time` datetime DEFAULT NULL,
  `t_open_id` varchar(200) DEFAULT NULL,
  `t_qq_open_id` varchar(200) DEFAULT NULL,
  `t_pass_wrod` varchar(200) DEFAULT NULL,
  `t_is_vip` int(11) DEFAULT NULL COMMENT '0.是\r\n1.否',
  `t_is_not_disturb` int(11) DEFAULT NULL COMMENT '0.是\r\n1.否',
  `t_browse_sum` int(11) DEFAULT NULL,
  `t_onLine` int(11) DEFAULT NULL COMMENT '0.在线\r\n1.离线',
  `t_weixin` varchar(255) DEFAULT NULL,
  `t_alipay_id` varchar(100) DEFAULT NULL,
  `t_online_setup` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `t_user_evaluation`;
CREATE TABLE `t_user_evaluation` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_user_hand` varchar(255) DEFAULT NULL,
  `t_user_nick` varchar(100) DEFAULT NULL,
  `t_anchor_id` int(11) DEFAULT NULL,
  `t_comment` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `t_score` int(11) DEFAULT NULL COMMENT '评价分数',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_label
-- ----------------------------
DROP TABLE IF EXISTS `t_user_label`;
CREATE TABLE `t_user_label` (
  `t_user_lable_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_lable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_user_lable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_label
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_laud
-- ----------------------------
DROP TABLE IF EXISTS `t_user_laud`;
CREATE TABLE `t_user_laud` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_laud_user_id` int(11) DEFAULT NULL,
  `t_cover_user_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_laud
-- ----------------------------

-- ----------------------------
-- Table structure for t_vague_check
-- ----------------------------
DROP TABLE IF EXISTS `t_vague_check`;
CREATE TABLE `t_vague_check` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_img_url` varchar(200) DEFAULT NULL,
  `t_data_type` int(11) DEFAULT NULL COMMENT '0.头像图片\r\n1.封面图片\r\n2.相册图片',
  `t_user_id` int(11) DEFAULT NULL,
  `t_gold` int(11) DEFAULT NULL COMMENT '0.否\r\n1.是',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vague_check
-- ----------------------------

-- ----------------------------
-- Table structure for t_version
-- ----------------------------
DROP TABLE IF EXISTS `t_version`;
CREATE TABLE `t_version` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_download_url` varchar(255) DEFAULT NULL,
  `t_is_new` int(11) DEFAULT NULL,
  `t_version` varchar(100) DEFAULT NULL,
  `t_version_depict` varchar(500) DEFAULT NULL,
  `t_version_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_version
-- ----------------------------

-- ----------------------------
-- Table structure for t_video_record
-- ----------------------------
DROP TABLE IF EXISTS `t_video_record`;
CREATE TABLE `t_video_record` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_dial_user_id` int(11) DEFAULT NULL,
  `t_answer_user_id` int(11) DEFAULT NULL,
  `t_type` int(11) DEFAULT NULL COMMENT '0.收入\r\n1.支出',
  `t_time_length` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_video_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_vip`;
CREATE TABLE `t_vip` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_openUp_time` datetime DEFAULT NULL,
  `t_end_time` datetime DEFAULT NULL,
  `t_auto_renew` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip
-- ----------------------------

-- ----------------------------
-- Table structure for t_vip_setmeal
-- ----------------------------
DROP TABLE IF EXISTS `t_vip_setmeal`;
CREATE TABLE `t_vip_setmeal` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_setmeal_name` varchar(20) DEFAULT NULL,
  `t_cost_price` decimal(10,2) DEFAULT NULL,
  `t_money` decimal(10,2) DEFAULT NULL,
  `t_gold` int(11) DEFAULT NULL,
  `t_duration` int(11) DEFAULT NULL,
  `t_is_enable` int(11) DEFAULT NULL COMMENT '0.启用\r\n1.停用',
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip_setmeal
-- ----------------------------
INSERT INTO `t_vip_setmeal` VALUES ('1', '1个月', '30.00', '1.00', '0', '1', '0', '2018-07-13 09:58:36');
INSERT INTO `t_vip_setmeal` VALUES ('2', '3个月', '90.00', '68.00', '68', '3', '0', '2018-07-13 10:00:05');
INSERT INTO `t_vip_setmeal` VALUES ('3', '12个月', '360.00', '168.00', '168', '12', '0', '2018-07-20 15:16:03');

-- ----------------------------
-- Table structure for t_virtual
-- ----------------------------
DROP TABLE IF EXISTS `t_virtual`;
CREATE TABLE `t_virtual` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_virtual
-- ----------------------------

-- ----------------------------
-- Table structure for t_wallet_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet_detail`;
CREATE TABLE `t_wallet_detail` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_change_type` int(11) DEFAULT NULL COMMENT '0.收入\r\n1.支出',
  `t_change_category` int(11) DEFAULT NULL COMMENT '0.充值\r\n1.聊天\r\n2.视频\r\n3.私密照片\r\n4.私密视频\r\n5.查看手机\r\n6.查看微信\r\n7.红包\r\n8.VIP\r\n9.礼物\r\n10.提现\r\n11.推荐分成\r\n12.提现失败原路退回\r\n13.注册赠送\r\n14.公会收入\r\n',
  `t_change_front` decimal(11,2) DEFAULT NULL COMMENT '注意：\r\n如果为收益时，此结果为分项单独金额(如:充值，收益，分享)\r\n如果为支出时，此项结果为总金额\r\n',
  `t_value` decimal(11,2) DEFAULT NULL,
  `t_change_after` decimal(11,2) DEFAULT NULL COMMENT '注意：\r\n如果为收益时，此结果为分项金额(充值，收益，分享)\r\n如果为支出时，此项结果为总金额',
  `t_change_time` datetime DEFAULT NULL,
  `t_sorece_id` int(11) DEFAULT NULL COMMENT '消费为t_order主键\r\n充值为t_recharge 主键\r\n提现为t_put_forward 主键',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wallet_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_weixinpay_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_weixinpay_setup`;
CREATE TABLE `t_weixinpay_setup` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` varchar(100) DEFAULT NULL,
  `t_mchid` varchar(100) DEFAULT NULL,
  `t_mchid_key` varchar(255) DEFAULT NULL,
  `t_certificate_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_weixinpay_setup
-- ----------------------------
INSERT INTO `t_weixinpay_setup` VALUES ('1', 'wxa3ee8c0fb5fa52d1', '1518593541', '6B59962FB5EBE78255ECCF3B3C0773A6', '/secretkey/cert/apiclient_cert.p12');

-- ----------------------------
-- Table structure for t_yellowing_error
-- ----------------------------
DROP TABLE IF EXISTS `t_yellowing_error`;
CREATE TABLE `t_yellowing_error` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_user_id` int(11) DEFAULT NULL,
  `t_room_msg` varchar(1000) DEFAULT NULL,
  `t_content` varchar(1000) DEFAULT NULL,
  `t_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_yellowing_error
-- ----------------------------
