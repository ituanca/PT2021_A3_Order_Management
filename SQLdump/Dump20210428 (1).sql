-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: schooldb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Andrei Pop','str.Negreni','Sibiu','+40756123456','andreipop@gmail.com'),(2,'Denis Tudoran','str.Albinelor nr.43','Brasov','+40756243760','denistudoran@gmail.com'),(3,'Maria Iacob','str.Zorilor nr.28','Suceava','+40359246142','mariaIacob@gmail.com'),(4,'Lorena Nemes','str.Vasile Lucaciu nr.142','Constanta','+40745034125','lorenaNemes@yahoo.com'),(5,'Lucian Popescu','str.Remeti nr. 243','Alexandria','+40259876890','lucianpopescu@yahoo.com'),(6,'Alina Ivanescu','str.Ambroziei nr. 90','Remeti','+40756134067','alinaivanescu@gmail.com'),(8,'Maria Irina Lazar','str.Simion Barnutiu nr.56','Gheorgheni','+40743124567','mariaLazar@gmail.com'),(9,'Larisa Ionescu','str.Mierlei nr. 45','Timisoara','+40787654321','larisaIonescu@yahoo.com'),(11,'Alina Codrean','str.Iepurilor nr.67','Iasi','+40756341235','alinacodrean@gmail.com'),(12,'Ana Filipan','str.Calata nr 456B','Galati','+40756897865','anaFilipan@yahoo.com'),(13,'Mihai Cret','str.Aurel Vlaicu, nr.45A','Miercurea Ciuc','+40756123924','mihaicret@gmail.com'),(15,'Daria Conta ','str. Vasile Alecsandri nr. 45','Craiova','0756345123','dariaConta@yahooom'),(16,'Maria Popescu','str.Menumorut nr.45B','Cluj-Napoca','+40756456786','mariapopescu@gmail.com'),(17,'Daniel Cristea','str.Mircea Eliade nr. 89','Drobeta-Turnu Severin','+40743125487','danielCristea@yahoo.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer` varchar(45) DEFAULT NULL,
  `product` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'Lorena Nemes','Lava cake',4,42.32),(3,'Lorena Nemes','Video recorder',12,8140.08),(4,'Denis Tudoran','ladder',12,5406),(9,'Lorena Nemes','Sweet German Chcocolate',7,199.92),(10,'Maria Iacob','Paper shredder',0,0),(13,'Lucian Popescu','torque wrench',2,99.16),(14,'Maria Iacob','mattresses',7,1823.92),(15,'Mirela Conta','ladder',0,0),(16,'Mirela Conta','ladder',0,0),(17,'Mirela Conta','ladder',3,1351.5),(18,'Alina Ivanescu','ladder',9,4054.5),(19,'Maria Irina Lazar','Paper shredder',1,460.67),(20,'Maria Iacob','Video recorder',1,678.32),(21,'Lorena Nemes','Sweet German Chcocolate',2,57.12),(22,'Lorena Nemes','Paper shredder',3,1382.01),(23,'Maria Iacob','Video recorder',3,2034.96),(24,'Denis Tudoran','torque wrench',3,148.74),(25,'Maria Iacob','torque wrench',1,49.58),(26,'Maria Iacob','torque wrench',2,99.16),(27,'Lorena Nemes','torque wrench',7,347.06),(28,'Denis Tudoran','Paper shredder',6,2764.02),(29,'Maria Irina Lazar','ladder',4,1802),(30,'Daniel Cristea','DVD player',3,2041.5),(31,'Lucian Popescu','Video recorder',5,3391.6000000000004),(32,'Lucian Popescu','ladder',74,33337),(33,'Lucian Popescu','ladder',2,901);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `unitPrice` double DEFAULT NULL,
  `unitsInStock` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (4,'torque wrench',49.58,46),(6,'Paper shredder',460.61,39),(7,'Sweet German Chcocolate',28.59,35),(14,'safety glasses',34.45,153),(15,'mattresses',260.58,40),(16,'Video recorder',678.31,40),(18,'ladder',450.5,28),(19,'DVD player',680.5,12),(20,'vacuum cleaner',380,45),(21,'compressor',456,20);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'schooldb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-28 22:57:48
