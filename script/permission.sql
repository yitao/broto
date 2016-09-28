-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `bro_module`;
CREATE TABLE `bro_module` (
  `id` varchar(64) NOT NULL,
  `ctime` datetime NOT NULL,
  `mtime` datetime DEFAULT NULL,
  `order` int DEFAULT 0,
  `icon` varchar(255) DEFAULT "",
  `label` varchar(255) DEFAULT "",
  `hint` varchar(255) DEFAULT "",
  `desc` varchar(255) DEFAULT "",
  `fa_module_id` varchar(64) DEFAULT NULL,
  `show` boolean DEFAULT FALSE ,
  `code`  varchar(64) DEFAULT NULL,
  `deleted` boolean DEFAULT FALSE ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `bro_action`;
CREATE TABLE `bro_action` (
  `id` varchar(64) NOT NULL,
  `ctime` datetime NOT NULL,
  `mtime` datetime DEFAULT NULL,
  `order` int DEFAULT 0,
  `icon` varchar(255) DEFAULT "",
  `label` varchar(255) DEFAULT "",
  `hint` varchar(255) DEFAULT "",
  `desc` varchar(255) DEFAULT "",
  `module_id` varchar(64) DEFAULT "",
  `action` varchar(255) NOT NULL,
  `show` boolean DEFAULT FALSE ,
  `code`  varchar(64) DEFAULT NULL,
  `exclude` boolean DEFAULT FALSE ,
  `deleted` boolean DEFAULT FALSE ,
  UNIQUE KEY (`module_id`,`action`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `bro_role`;
CREATE TABLE `bro_role` (
  `id` varchar(64) NOT NULL,
  `ctime` datetime NOT NULL,
  `mtime` datetime DEFAULT NULL,
  `order` int DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `inuse` boolean DEFAULT FALSE ,
  `code`  varchar(64) DEFAULT NULL,
  `ap` boolean DEFAULT FALSE,
  `deleted` boolean DEFAULT FALSE ,
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `bro_account`;
CREATE TABLE `bro_account` (
  `id` varchar(64) NOT NULL,
  `ctime` datetime NOT NULL,
  `mtime` datetime DEFAULT NULL,
  `order` int DEFAULT 0,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `inuse` boolean DEFAULT FALSE ,
  `code`  varchar(64) DEFAULT NULL,
  `deleted` boolean DEFAULT FALSE ,
  UNIQUE KEY (`account`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rma
-- ----------------------------
DROP TABLE IF EXISTS `bro_rma`;
CREATE TABLE `bro_rma` (
  `role_id` varchar(64) NOT NULL,
  `module_id` varchar(64) NOT NULL,
  `action_id` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id`,`module_id`,`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ar
-- ----------------------------
DROP TABLE IF EXISTS `bro_ar`;
CREATE TABLE `bro_ar` (
  `account_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for r2r
-- ----------------------------
DROP TABLE IF EXISTS `bro_r2r`;
CREATE TABLE `bro_r2r` (
  `opened_role_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`opened_role_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for r2ma
-- ----------------------------
DROP TABLE IF EXISTS `bro_r2ma`;
CREATE TABLE `bro_r2ma` (
  `role_id` varchar(64) NOT NULL,
  `module_id` varchar(64) NOT NULL,
  `action_id` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id`,`module_id`,`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建内置超级管理员账号
INSERT INTO  `bro_account`(`id`,`ctime`,`mtime`,`order`,`account`,`password`,`inuse`, `code`) VALUES (REPLACE(UUID(),"-",""),NOW(),NOW(),0,"notsuperadmin","",true,"SUPER_ADMIN_ACCOUNT");
INSERT INTO  `bro_account`(`id`,`ctime`,`mtime`,`order`,`account`,`password`,`inuse`, `code`) VALUES (REPLACE(UUID(),"-",""),NOW(),NOW(),0,"notadmin","",true,"");

-- 创建内置超级管理员角色
INSERT INTO  `bro_role`(`id`,`ctime`,`mtime`,`order`,`name`,`desc`,`inuse`,`code`,`ap`) VALUES (REPLACE(UUID(),"-",""),NOW(),NOW(),0,"superadmin","超级管理员",true,"SUPER_ADMIN_ROLE",true);
INSERT INTO  `bro_role`(`id`,`ctime`,`mtime`,`order`,`name`,`desc`,`inuse`,`code`,`ap`) VALUES (REPLACE(UUID(),"-",""),NOW(),NOW(),0,"admin","管理员",true,"",false);

-- 创建权限管理模块
-- 创建权限管理模块下的所有功能
-- 分配超级管理员角色给超级管理员账号
INSERT INTO `bro_ar` (`account_id`,`role_id`)  SELECT ac.id,r.id from `bro_account` ac,`bro_role` r WHERE ac.`code`="SUPER_ADMIN_ACCOUNT" and r.`code`="SUPER_ADMIN_ROLE" LIMIT 1
