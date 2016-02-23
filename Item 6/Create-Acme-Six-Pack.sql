start transaction;

create database `Acme-Six-Pack`;

use `Acme-Six-Pack`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete on `Acme-Six-Pack`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view on `Acme-Six-Pack`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Six-Pack
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (17,0,'Miguel','666777888','Rodriguez',1);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `canceled` bit(1) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `denied` bit(1) NOT NULL,
  `duration` double NOT NULL,
  `requestMoment` datetime DEFAULT NULL,
  `administrator_id` int(11) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `gym_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f8yk69exs5rrokhbs1ovnk0pv` (`administrator_id`),
  KEY `FK_g0byx2e4nv3fc4ghedm3n5051` (`customer_id`),
  KEY `FK_mmiyptv70h1ds6he9fsh20stk` (`gym_id`),
  KEY `FK_luj2rr6jrlywrksmvjsvnvrbg` (`service_id`),
  CONSTRAINT `FK_luj2rr6jrlywrksmvjsvnvrbg` FOREIGN KEY (`service_id`) REFERENCES `servicetable` (`id`),
  CONSTRAINT `FK_f8yk69exs5rrokhbs1ovnk0pv` FOREIGN KEY (`administrator_id`) REFERENCES `administrator` (`id`),
  CONSTRAINT `FK_g0byx2e4nv3fc4ghedm3n5051` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_mmiyptv70h1ds6he9fsh20stk` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (64,0,'\0','\0','2016-02-14 19:00:00','\0',1,'2016-02-15 17:00:00',17,18,46,49),(65,0,'','\0','2016-02-14 19:05:00','\0',1,'2016-02-16 17:00:00',17,18,46,49),(66,0,'\0','\0','2016-02-14 19:10:00','',1,'2016-02-17 17:00:00',17,18,46,49),(67,0,'\0','','2016-02-14 20:00:00','\0',1,'2016-02-18 17:00:00',17,18,46,49),(68,0,'','\0','2016-02-14 20:00:00','\0',1,'2016-02-18 17:00:00',17,19,47,50),(69,0,'','\0','2015-09-15 12:00:00','\0',1,'2015-09-16 17:00:00',17,20,48,49);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `starRating` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  `commentedEntity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (70,0,'\0','2016-02-09 18:30:00',2,'Buen Gym',18,46),(71,0,'\0','2016-02-08 18:30:00',2,'No está mal',19,46),(72,0,'\0','2016-02-10 18:30:00',0,'No me gusta',20,46),(73,0,'\0','2016-02-09 18:30:00',3,'Es muy bueno',18,47),(74,0,'\0','2016-02-08 18:30:00',1,'No es malo',19,47),(75,0,'\0','2016-02-08 18:30:00',3,'No lo probé, pero es el mas barato',18,48),(76,0,'\0','2016-02-08 18:30:00',2,'Buen servicio!',18,49),(77,0,'','2016-02-10 18:30:00',3,'La mejor clase de Fitness',20,49),(78,0,'\0','2016-02-11 18:30:00',2,'Buena clase de Karate',21,51);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `socialIdentity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  KEY `FK_ld1r3g5jtm4ktdbmtoju22gwt` (`socialIdentity_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_ld1r3g5jtm4ktdbmtoju22gwt` FOREIGN KEY (`socialIdentity_id`) REFERENCES `socialidentity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (18,0,'Manolo','612345789','Lopez',2,16),(19,0,'Ruben','612342289','Sanchez',3,NULL),(20,0,'Guillermo','633422897','Alcala',4,NULL),(21,0,'Mónica','626667791','Ordóñez',5,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_creditcards`
--

DROP TABLE IF EXISTS `customer_creditcards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_creditcards` (
  `Customer_id` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  KEY `FK_o19gw4n4g1iyqwsuc71w63lgn` (`Customer_id`),
  CONSTRAINT `FK_o19gw4n4g1iyqwsuc71w63lgn` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_creditcards`
--

LOCK TABLES `customer_creditcards` WRITE;
/*!40000 ALTER TABLE `customer_creditcards` DISABLE KEYS */;
INSERT INTO `customer_creditcards` VALUES (18,'visa',267,7,2016,'Manolo','4143672806408593'),(19,'visa',639,12,2016,'Ruben','4014226416609441'),(20,'visa',143,2,2016,'Guillermo','4019547713317569'),(21,'visa',683,9,2016,'Monica','4794199409160431');
/*!40000 ALTER TABLE `customer_creditcards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feepayment`
--

DROP TABLE IF EXISTS `feepayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feepayment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activeMoment` datetime DEFAULT NULL,
  `amount` double NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `inactiveMoment` datetime DEFAULT NULL,
  `paymentMoment` datetime DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `gym_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8pgjh62mj6aujjifjrthamwbf` (`customer_id`),
  KEY `FK_bgnr9f6puq2dvx5k0oqk9fw3g` (`gym_id`),
  CONSTRAINT `FK_bgnr9f6puq2dvx5k0oqk9fw3g` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`id`),
  CONSTRAINT `FK_8pgjh62mj6aujjifjrthamwbf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feepayment`
--

LOCK TABLES `feepayment` WRITE;
/*!40000 ALTER TABLE `feepayment` DISABLE KEYS */;
INSERT INTO `feepayment` VALUES (53,0,'2016-02-13 12:00:00',30,'visa',267,7,2016,'Manolo','4143672806408593','2016-03-13 12:00:00','2016-02-12 18:00:00',18,46),(54,0,'2016-01-12 12:00:00',30,'visa',267,7,2016,'Manolo','4143672806408593','2016-02-12 12:00:00','2016-01-11 18:00:00',18,46),(55,0,'2015-12-11 12:00:00',30,'visa',267,7,2016,'Manolo','4143672806408593','2016-01-11 12:00:00','2015-12-10 18:00:00',18,46),(56,0,'2016-02-15 12:00:00',20,'visa',639,12,2016,'Ruben','4014226416609441','2016-03-15 12:00:00','2016-02-14 18:00:00',19,47),(57,0,'2015-06-02 12:00:00',15,'visa',143,2,2016,'Guillermo','4019547713317569','2015-07-02 12:00:00','2015-06-01 18:00:00',20,48),(58,0,'2015-07-03 12:00:00',15,'visa',143,2,2016,'Guillermo','4019547713317569','2015-08-03 12:00:00','2015-07-02 18:00:00',20,48),(59,0,'2015-08-04 12:00:00',15,'visa',143,2,2016,'Guillermo','4019547713317569','2015-09-04 12:00:00','2015-08-03 18:00:00',20,48),(60,0,'2015-09-05 12:00:00',15,'visa',143,2,2016,'Guillermo','4019547713317569','2015-10-05 12:00:00','2015-09-04 18:00:00',20,48),(61,0,'2015-10-06 12:00:00',15,'visa',143,2,2016,'Guillermo','4019547713317569','2015-11-06 12:00:00','2015-10-05 18:00:00',20,48),(62,0,'2015-11-07 12:00:00',15,'visa',143,2,2016,'Guillermo','4019547713317569','2015-12-07 12:00:00','2015-11-06 18:00:00',20,48),(63,0,'2016-02-12 12:00:00',30,'visa',683,9,2016,'Monica','4794199409160431','2016-03-12 12:00:00','2016-02-11 18:00:00',21,46);
/*!40000 ALTER TABLE `feepayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `isSystem` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (26,0,'','InBox',17),(27,0,'','OutBox',17),(28,0,'','TrashBox',17),(29,0,'','SpamBox',17),(30,0,'','InBox',18),(31,0,'','OutBox',18),(32,0,'','TrashBox',18),(33,0,'','SpamBox',18),(34,0,'','InBox',19),(35,0,'','OutBox',19),(36,0,'','TrashBox',19),(37,0,'','SpamBox',19),(38,0,'','InBox',20),(39,0,'','OutBox',20),(40,0,'','TrashBox',20),(41,0,'','SpamBox',20),(42,0,'','InBox',21),(43,0,'','OutBox',21),(44,0,'','TrashBox',21),(45,0,'','SpamBox',21);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_message`
--

DROP TABLE IF EXISTS `folder_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_message` (
  `folders_id` int(11) NOT NULL,
  `messages_id` int(11) NOT NULL,
  KEY `FK_5nh3mwey9bw25ansh2thcbcdh` (`messages_id`),
  KEY `FK_o1e2m8i8khapsgpd6jg28dr7e` (`folders_id`),
  CONSTRAINT `FK_o1e2m8i8khapsgpd6jg28dr7e` FOREIGN KEY (`folders_id`) REFERENCES `folder` (`id`),
  CONSTRAINT `FK_5nh3mwey9bw25ansh2thcbcdh` FOREIGN KEY (`messages_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_message`
--

LOCK TABLES `folder_message` WRITE;
/*!40000 ALTER TABLE `folder_message` DISABLE KEYS */;
INSERT INTO `folder_message` VALUES (27,22),(30,22),(33,23),(33,24),(34,22),(35,25),(38,22),(41,25),(42,22),(43,23),(43,24);
/*!40000 ALTER TABLE `folder_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym`
--

DROP TABLE IF EXISTS `gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gym` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fee` double NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym`
--

LOCK TABLES `gym` WRITE;
/*!40000 ALTER TABLE `gym` DISABLE KEYS */;
INSERT INTO `gym` VALUES (46,0,'Gimnasio de Sevilla','Gym Sevilla',30,'666444777','http://www.casacartagena.com/archivo/files/gimnasio-1.jpg','Cuesta del Rosario, 8, 41004 Sevilla'),(47,0,'Gimnasio de Cádiz','Gym Cádiz',20,'666444766','http://www.casacartagena.com/archivo/files/gimnasio-1.jpg','Av. Alcalde Manuel de la Pinta, 24, 11011 Cádiz'),(48,0,'Gimnasio de Dos Hermanas','Gym Dos Hermanas',15,'654789123','http://www.casacartagena.com/archivo/files/gimnasio-1.jpg','Calle Dr. Fleming, 45, 41701 Dos Hermanas, Sevilla');
/*!40000 ALTER TABLE `gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `sentMoment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (22,0,'Registrado con exito, bienvenido a Acme-Six-Pack','2015-10-13 22:15:00','Bienvenido',17),(23,0,'Compra viagra','2016-02-05 22:15:00','Hola',21),(24,0,'Esto es spam','2016-02-05 22:15:00','Hola',21),(25,0,'Esto es spam','2016-02-05 22:15:00','Hola',19);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_actor`
--

DROP TABLE IF EXISTS `message_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_actor` (
  `received_id` int(11) NOT NULL,
  `recipients_id` int(11) NOT NULL,
  KEY `FK_s15b8cpmjbq3qqa55fool5tp7` (`received_id`),
  CONSTRAINT `FK_s15b8cpmjbq3qqa55fool5tp7` FOREIGN KEY (`received_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_actor`
--

LOCK TABLES `message_actor` WRITE;
/*!40000 ALTER TABLE `message_actor` DISABLE KEYS */;
INSERT INTO `message_actor` VALUES (22,18),(22,19),(22,20),(22,21),(23,18),(24,18),(25,20);
/*!40000 ALTER TABLE `message_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceentity_pictures`
--

DROP TABLE IF EXISTS `serviceentity_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serviceentity_pictures` (
  `ServiceEntity_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_jy0v1kvmxy41tfajmjrnmbkf3` (`ServiceEntity_id`),
  CONSTRAINT `FK_jy0v1kvmxy41tfajmjrnmbkf3` FOREIGN KEY (`ServiceEntity_id`) REFERENCES `servicetable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceentity_pictures`
--

LOCK TABLES `serviceentity_pictures` WRITE;
/*!40000 ALTER TABLE `serviceentity_pictures` DISABLE KEYS */;
INSERT INTO `serviceentity_pictures` VALUES (49,'http://guiafitness.com/wp-content/uploads/clase-zumba-fitness.jpg'),(49,'https://www.clubfluviallugo.com/web/media/rokgallery/0/052c7c45-45e9-408e-ba87-3b29c88ca085/clase_fitness_1.jpg'),(50,'http://www.adelgazaconenriqueangel.com/wp-content/uploads/2013/10/spinning-funciona.jpg'),(50,'http://blogs.okdiario.com/vida-sana/wp-content/uploads/2015/07/Spinning-Class-with-instructor.jpg'),(51,'https://pixabay.com/static/uploads/photo/2013/07/12/15/48/kickboxing-150331_960_720.png\"'),(51,'http://www.quevivaeldeporte.com/wp-content/uploads/2009/03/Karate_04-1024x686-980x610-2z9rt6l25kmyvumc7p7ymi.jpg'),(52,'http://www.entrenamiento.com/wp-content/uploads/2016/01/5-consejos-para-nadadores-principiantes.jpg'),(52,'https://i.ytimg.com/vi/xL2CSFj-VwM/maxresdefault.jpg');
/*!40000 ALTER TABLE `serviceentity_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicetable`
--

DROP TABLE IF EXISTS `servicetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicetable` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicetable`
--

LOCK TABLES `servicetable` WRITE;
/*!40000 ALTER TABLE `servicetable` DISABLE KEYS */;
INSERT INTO `servicetable` VALUES (49,0,'Servicio de Fitness','Fitness'),(50,0,'Servicio de Spinning','Spinning'),(51,0,'Servicio de Karate','Karate'),(52,0,'Servicio de Natación','Natación');
/*!40000 ALTER TABLE `servicetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicetable_gym`
--

DROP TABLE IF EXISTS `servicetable_gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicetable_gym` (
  `services_id` int(11) NOT NULL,
  `gyms_id` int(11) NOT NULL,
  KEY `FK_r9gamdmbt0f2kj461x50f7oif` (`gyms_id`),
  KEY `FK_x997iw7m82b9c2xqvdkmyk23` (`services_id`),
  CONSTRAINT `FK_x997iw7m82b9c2xqvdkmyk23` FOREIGN KEY (`services_id`) REFERENCES `servicetable` (`id`),
  CONSTRAINT `FK_r9gamdmbt0f2kj461x50f7oif` FOREIGN KEY (`gyms_id`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicetable_gym`
--

LOCK TABLES `servicetable_gym` WRITE;
/*!40000 ALTER TABLE `servicetable_gym` DISABLE KEYS */;
INSERT INTO `servicetable_gym` VALUES (49,46),(49,47),(49,48),(50,47),(51,48),(52,46);
/*!40000 ALTER TABLE `servicetable_gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialidentity`
--

DROP TABLE IF EXISTS `socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialidentity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `homePage` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialidentity`
--

LOCK TABLES `socialidentity` WRITE;
/*!40000 ALTER TABLE `socialidentity` DISABLE KEYS */;
INSERT INTO `socialidentity` VALUES (16,0,'http://Manolo.com','Manolo','@Manolo','http://static.betazeta.com/www.fayerwayer.com/up/2014/04/Bliss-XP-HD1-960x623.jpg');
/*!40000 ALTER TABLE `socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spamterm`
--

DROP TABLE IF EXISTS `spamterm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spamterm` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `term` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spamterm`
--

LOCK TABLES `spamterm` WRITE;
/*!40000 ALTER TABLE `spamterm` DISABLE KEYS */;
INSERT INTO `spamterm` VALUES (6,0,'viagra'),(7,0,'cialis'),(8,0,'sex'),(9,0,'scort'),(10,0,'money transfer'),(11,0,'lottery'),(12,0,'green card'),(13,0,'email quota'),(14,0,'click here'),(15,0,'spam');
/*!40000 ALTER TABLE `spamterm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(2,0,'ffbc4675f864e0e9aab8bdf7a0437010','customer1'),(3,0,'5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),(4,0,'033f7f6121501ae98285ad77f216d5e7','customer3'),(5,0,'55feb130be438e686ad6a80d12dd8f44','customer4');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (1,'ADMIN'),(2,'CUSTOMER'),(3,'CUSTOMER'),(4,'CUSTOMER'),(5,'CUSTOMER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-22 20:37:03

commit;
