# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: vote
# ------------------------------------------------------
# Server version 5.5.48

DROP DATABASE IF EXISTS `vote`;
CREATE DATABASE `vote` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `vote`;

#
# Source for table barrage
#

DROP TABLE IF EXISTS `barrage`;
CREATE TABLE `barrage` (
  `UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `Data` date NOT NULL DEFAULT '0000-00-00' COMMENT '弹幕时间',
  `caption` varchar(64) NOT NULL DEFAULT '' COMMENT '弹幕内容',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table barrage
#
LOCK TABLES `barrage` WRITE;
/*!40000 ALTER TABLE `barrage` DISABLE KEYS */;

/*!40000 ALTER TABLE `barrage` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table battles
#

DROP TABLE IF EXISTS `battles`;
CREATE TABLE `battles` (
  `battleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '对战ID',
  `matchId` int(11) NOT NULL DEFAULT '0' COMMENT '比赛ID',
  `PlayerId` int(11) NOT NULL DEFAULT '0' COMMENT '选手ID',
  `SongName` text NOT NULL COMMENT '歌曲名',
  `TicketTrueCount` int(11) NOT NULL DEFAULT '0' COMMENT '支持票数',
  `TicketFalseCount` int(11) NOT NULL DEFAULT '0' COMMENT '反对票数',
  `NeedScoreCount` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '评委打分',
  `matchType` int(3) NOT NULL DEFAULT '0' COMMENT '比赛评分类型 观众投票、观众投票&评委打分、观众投正反对票、观众投正反对票&评委打分',
  `battleFlag` int(3) NOT NULL DEFAULT '0' COMMENT '比赛进行状况',
  PRIMARY KEY (`battleId`),
  KEY `matchId` (`matchId`),
  KEY `PlayerId` (`PlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对战表';

#
# Dumping data for table battles
#
LOCK TABLES `battles` WRITE;
/*!40000 ALTER TABLE `battles` DISABLE KEYS */;

/*!40000 ALTER TABLE `battles` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table battles
#

DROP TABLE IF EXISTS `battles`;
CREATE TABLE `battles` (
  `battleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '对战ID',
  `matchId` int(11) NOT NULL DEFAULT '0' COMMENT '比赛ID',
  `PlayerId` int(11) NOT NULL DEFAULT '0' COMMENT '选手ID',
  `SongName` text NOT NULL COMMENT '歌曲名',
  `TicketTrueCount` int(11) NOT NULL DEFAULT '0' COMMENT '支持票数',
  `TicketFalseCount` int(11) NOT NULL DEFAULT '0' COMMENT '反对票数',
  `NeedScoreCount` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '评委打分',
  `matchType` int(3) NOT NULL DEFAULT '0' COMMENT '比赛评分类型 观众投票、观众投票&评委打分、观众投正反对票、观众投正反对票&评委打分',
  `battleFlag` int(3) NOT NULL DEFAULT '0' COMMENT '比赛进行状况',
  PRIMARY KEY (`battleId`),
  KEY `matchId` (`matchId`),
  KEY `PlayerId` (`PlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对战表';

#
# Dumping data for table battles
#
LOCK TABLES `battles` WRITE;
/*!40000 ALTER TABLE `battles` DISABLE KEYS */;

/*!40000 ALTER TABLE `battles` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table matchs
#

DROP TABLE IF EXISTS `matchs`;
CREATE TABLE `matchs` (
  `matchId` int(11) NOT NULL AUTO_INCREMENT COMMENT '比赛ID',
  `matchName` text NOT NULL COMMENT '比赛名称',
  `matchAddress` text NOT NULL COMMENT '比赛地点',
  `matchTime` date NOT NULL DEFAULT '0000-00-00' COMMENT '比赛时间',
  `matchInfo` text NOT NULL COMMENT '比赛说明',
  PRIMARY KEY (`matchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比赛信息表';

#
# Dumping data for table matchs
#
LOCK TABLES `matchs` WRITE;
/*!40000 ALTER TABLE `matchs` DISABLE KEYS */;

/*!40000 ALTER TABLE `matchs` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table players
#

DROP TABLE IF EXISTS `players`;
CREATE TABLE `players` (
  `playerId` int(11) NOT NULL AUTO_INCREMENT COMMENT '选手ID',
  `playerTel` varchar(11) NOT NULL DEFAULT '' COMMENT '选手电话',
  `playerName` varchar(32) NOT NULL DEFAULT '' COMMENT '选手姓名',
  `playerImage` text NOT NULL COMMENT '选手照片',
  `playerText` text NOT NULL COMMENT '选手简介',
  PRIMARY KEY (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选手信息';

#
# Dumping data for table players
#
LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;

/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `Username` int(11) NOT NULL DEFAULT '0' COMMENT '登录ID',
  `UserId` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长类型',
  `Password` varchar(16) NOT NULL DEFAULT '' COMMENT '用户密码',
  `Name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别',
  `tel` varchar(11) NOT NULL DEFAULT '' COMMENT '电话',
  `scale` int(1) NOT NULL DEFAULT '0' COMMENT '等级',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table users
#
LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table votelists
#

DROP TABLE IF EXISTS `votelists`;
CREATE TABLE `votelists` (
  `matchId` int(11) NOT NULL DEFAULT '0' COMMENT '比赛ID',
  `battleId` int(11) NOT NULL DEFAULT '0' COMMENT '对战ID',
  `playerId` int(11) NOT NULL DEFAULT '0' COMMENT '选手ID',
  `Userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `Ticketbol` blob NOT NULL COMMENT '为true或false，为true代表支持，为false代表反对',
  KEY `matchId_V` (`matchId`),
  KEY `battleId_v` (`battleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票详情表';

#
# Dumping data for table votelists
#
LOCK TABLES `votelists` WRITE;
/*!40000 ALTER TABLE `votelists` DISABLE KEYS */;

/*!40000 ALTER TABLE `votelists` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table barrage
#

ALTER TABLE `barrage`
ADD CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`);

#
#  Foreign keys for table battles
#

ALTER TABLE `battles`
ADD CONSTRAINT `matchId` FOREIGN KEY (`matchId`) REFERENCES `matchlists` (`matchId`),
ADD CONSTRAINT `PlayerId` FOREIGN KEY (`PlayerId`) REFERENCES `players` (`playerId`);

#
#  Foreign keys for table battles
#

ALTER TABLE `battles`
ADD CONSTRAINT `matchId` FOREIGN KEY (`matchId`) REFERENCES `matchlists` (`matchId`),
ADD CONSTRAINT `PlayerId` FOREIGN KEY (`PlayerId`) REFERENCES `players` (`playerId`);

#
#  Foreign keys for table votelists
#

ALTER TABLE `votelists`
ADD CONSTRAINT `matchId_V` FOREIGN KEY (`matchId`) REFERENCES `matchlists` (`matchId`),
ADD CONSTRAINT `battleId_v` FOREIGN KEY (`battleId`) REFERENCES `matchs` (`battleId`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
