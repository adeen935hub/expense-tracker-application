-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: expense_management
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category_mst`
--

DROP TABLE IF EXISTS `category_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_mst` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_description` varchar(1000) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_mst`
--

LOCK TABLES `category_mst` WRITE;
/*!40000 ALTER TABLE `category_mst` DISABLE KEYS */;
INSERT INTO `category_mst` VALUES (1,'Housing','Housing','2024-03-28 12:10:07','2024-03-28 12:10:07'),(2,'Transportation','Transportation','2024-03-28 12:10:35','2024-03-28 12:10:35'),(3,'Vehicle Charges','Vehicle Charges','2024-03-28 12:10:49','2024-03-28 12:10:49'),(4,'Grocery','Grocery','2024-03-28 12:11:01','2024-03-28 12:11:01'),(5,'Restaurant','Restaurant','2024-03-28 12:11:16','2024-03-28 12:11:16'),(6,'Utilities','Utilities','2024-03-28 12:11:23','2024-03-28 12:11:23'),(7,'Clothing','Clothing','2024-03-28 12:11:33','2024-03-28 12:11:33'),(8,'Household','Household','2024-03-28 12:11:41','2024-03-28 12:11:41'),(9,'Personal','Personal','2024-03-28 12:11:48','2024-03-28 12:11:48'),(10,'Entertainment','Entertainment','2024-03-28 12:11:59','2024-03-28 12:11:59');
/*!40000 ALTER TABLE `category_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_detail_trn`
--

DROP TABLE IF EXISTS `expense_detail_trn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense_detail_trn` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expense_amount` decimal(10,2) NOT NULL,
  `expense_date` timestamp NOT NULL,
  `category_id` bigint NOT NULL,
  `sub_category_id` bigint DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `sub_category_id` (`sub_category_id`),
  CONSTRAINT `expense_detail_trn_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category_mst` (`id`),
  CONSTRAINT `expense_detail_trn_ibfk_2` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category_mst` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_detail_trn`
--

LOCK TABLES `expense_detail_trn` WRITE;
/*!40000 ALTER TABLE `expense_detail_trn` DISABLE KEYS */;
INSERT INTO `expense_detail_trn` VALUES (1,15000.00,'2024-03-14 00:00:00',4,NULL,'2024-03-28 12:34:53','2024-03-28 12:34:53'),(2,10000.00,'2024-03-20 00:00:00',8,18,'2024-03-28 12:36:02','2024-03-28 12:36:02'),(3,5000.00,'2024-03-20 00:00:00',8,19,'2024-03-28 12:36:37','2024-03-28 12:36:37'),(4,3000.00,'2024-03-20 00:00:00',8,20,'2024-03-28 12:37:07','2024-03-28 12:37:07');
/*!40000 ALTER TABLE `expense_detail_trn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_mst`
--

DROP TABLE IF EXISTS `role_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_mst` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_mst`
--

LOCK TABLES `role_mst` WRITE;
/*!40000 ALTER TABLE `role_mst` DISABLE KEYS */;
INSERT INTO `role_mst` VALUES (1,'USER','USER','2023-04-11 08:36:49','2023-04-11 08:36:49'),(2,'ADMIN','ADMIN','2023-04-11 08:36:49','2023-04-11 08:36:49');
/*!40000 ALTER TABLE `role_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_category_mst`
--

DROP TABLE IF EXISTS `sub_category_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_category_mst` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sub_category_name` varchar(255) NOT NULL,
  `sub_category_description` varchar(1000) DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_sub_category_name_category_id` (`sub_category_name`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `sub_category_mst_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category_mst` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_category_mst`
--

LOCK TABLES `sub_category_mst` WRITE;
/*!40000 ALTER TABLE `sub_category_mst` DISABLE KEYS */;
INSERT INTO `sub_category_mst` VALUES (1,'rent','rent',1,'2024-03-28 12:12:37','2024-03-28 12:12:37'),(2,'EMI','EMI',1,'2024-03-28 12:13:09','2024-03-28 12:13:09'),(3,'property taxes','property taxes',1,'2024-03-28 12:13:29','2024-03-28 12:13:29'),(4,'bus','bus',2,'2024-03-28 12:13:43','2024-03-28 12:13:43'),(5,'train','train',2,'2024-03-28 12:13:54','2024-03-28 12:13:54'),(6,'plane','plane',2,'2024-03-28 12:14:02','2024-03-28 12:14:02'),(8,'EMI','EMI',3,'2024-03-28 12:20:18','2024-03-28 12:20:18'),(9,'petrol','petrol',3,'2024-03-28 12:20:26','2024-03-28 12:20:26'),(10,'car maintenance','car maintenance',3,'2024-03-28 12:20:34','2024-03-28 12:20:34'),(11,'electricity','electricity',6,'2024-03-28 12:21:02','2024-03-28 12:21:02'),(12,'cable','cable',6,'2024-03-28 12:21:11','2024-03-28 12:21:11'),(13,'phones','phones',6,'2024-03-28 12:21:20','2024-03-28 12:21:20'),(14,'internet','internet',6,'2024-03-28 12:21:28','2024-03-28 12:21:28'),(15,'personal','personal',7,'2024-03-28 12:21:43','2024-03-28 12:21:43'),(16,'sibling','sibling',7,'2024-03-28 12:21:53','2024-03-28 12:21:53'),(17,'spouse and partner','spouse and partner',7,'2024-03-28 12:22:19','2024-03-28 12:22:19'),(18,'maid','maid',8,'2024-03-28 12:22:40','2024-03-28 12:22:40'),(19,'cleaning','cleaning',8,'2024-03-28 12:22:47','2024-03-28 12:22:47'),(20,'laundry','laundry',8,'2024-03-28 12:22:55','2024-03-28 12:22:55'),(21,'gym','gym',9,'2024-03-28 12:23:09','2024-03-28 12:23:09'),(22,'salon','salon',9,'2024-03-28 12:23:18','2024-03-28 12:23:18'),(23,'cosmetics','cosmetics',9,'2024-03-28 12:23:25','2024-03-28 12:23:25'),(24,'vacations','vacations',10,'2024-03-28 12:23:42','2024-03-28 12:23:42'),(25,'games','games',10,'2024-03-28 12:23:51','2024-03-28 12:23:51'),(26,'movies','movies',10,'2024-03-28 12:23:58','2024-03-28 12:23:58'),(27,'concerts','concerts',10,'2024-03-28 12:24:05','2024-03-28 12:24:05');
/*!40000 ALTER TABLE `sub_category_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_mst`
--

DROP TABLE IF EXISTS `user_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_mst` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_mst`
--

LOCK TABLES `user_mst` WRITE;
/*!40000 ALTER TABLE `user_mst` DISABLE KEYS */;
INSERT INTO `user_mst` VALUES (1,1,'admin@gmail.com','$2a$10$WGZPJKhRINR/6E3/DUfAhuoreLBT5iAw..T2EtNc2WZl2siT3zPtK','admin','2023-04-11 08:36:49','2023-04-11 08:36:49');
/*!40000 ALTER TABLE `user_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_trn`
--

DROP TABLE IF EXISTS `user_role_trn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_trn` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_trn_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_mst` (`id`),
  CONSTRAINT `user_role_trn_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role_mst` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_trn`
--

LOCK TABLES `user_role_trn` WRITE;
/*!40000 ALTER TABLE `user_role_trn` DISABLE KEYS */;
INSERT INTO `user_role_trn` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `user_role_trn` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-28 18:09:44
