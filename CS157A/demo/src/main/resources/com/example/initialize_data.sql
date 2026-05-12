-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1,1114.00,'Checking',0.00,'2026-05-11'),(2,2,1199.00,'Savings',2.50,'2026-05-11'),(3,3,1299.00,'Credit Card',18.00,'2026-05-11'),(4,4,1399.00,'Savings',2.50,'2026-05-11'),(5,5,1499.00,'Checking',0.00,'2026-05-11'),(6,6,1599.00,'Credit Card',18.00,'2026-05-11'),(7,7,1699.00,'Checking',0.00,'2026-05-11'),(8,8,1799.00,'Savings',2.50,'2026-05-11'),(9,9,1899.00,'Credit Card',18.00,'2026-05-11'),(10,10,1999.00,'Savings',2.50,'2026-05-11'),(11,11,2099.00,'Checking',0.00,'2026-05-11'),(12,12,2199.00,'Credit Card',18.00,'2026-05-11'),(13,13,2299.00,'Checking',0.00,'2026-05-11'),(14,14,2399.00,'Savings',2.50,'2026-05-11'),(15,15,2499.00,'Credit Card',18.00,'2026-05-11');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1,1,5500.00,6.00,5500.00,416.43,'2026-05-11',14),(2,2,6000.00,7.00,6000.00,401.25,'2026-05-11',16),(3,3,6500.00,5.00,6500.00,379.17,'2026-05-11',18),(4,4,7000.00,6.00,7000.00,371.00,'2026-05-11',20),(5,5,7500.00,7.00,7500.00,364.77,'2026-05-11',22),(6,6,8000.00,5.00,8000.00,350.00,'2026-05-11',24),(7,7,8500.00,6.00,8500.00,346.54,'2026-05-11',26),(8,8,9000.00,7.00,9000.00,343.93,'2026-05-11',28),(9,9,9500.00,5.00,9500.00,332.50,'2026-05-11',30),(10,10,10000.00,6.00,10000.00,331.25,'2026-05-11',32),(11,11,10500.00,7.00,10500.00,330.44,'2026-05-11',34),(12,12,11000.00,5.00,11000.00,320.83,'2026-05-11',36),(13,13,11500.00,6.00,11500.00,320.79,'2026-05-11',38),(14,14,12000.00,7.00,12000.00,321.00,'2026-05-11',40),(15,15,12500.00,5.00,12500.00,312.50,'2026-05-11',42);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,1,2,'2026-05-11 19:56:48',51.00),(2,2,3,'2026-05-11 19:56:48',52.00),(3,3,4,'2026-05-11 19:56:48',53.00),(4,4,5,'2026-05-11 19:56:48',54.00),(5,5,6,'2026-05-11 19:56:48',55.00),(6,6,7,'2026-05-11 19:56:48',56.00),(7,7,8,'2026-05-11 19:56:48',57.00),(8,8,9,'2026-05-11 19:56:48',58.00),(9,9,10,'2026-05-11 19:56:48',59.00),(10,10,11,'2026-05-11 19:56:48',60.00),(11,11,12,'2026-05-11 19:56:48',61.00),(12,12,13,'2026-05-11 19:56:48',62.00),(13,13,14,'2026-05-11 19:56:48',63.00),(14,14,15,'2026-05-11 19:56:48',64.00),(15,15,1,'2026-05-11 19:56:48',65.00);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user1','password1','Test User 1','user1@example.com','555-01001','1 Main St, City, Country','Customer'),(2,'user2','password2','Test User 2','user2@example.com','555-01002','2 Main St, City, Country','Customer'),(3,'user3','password3','Test User 3','user3@example.com','555-01003','3 Main St, City, Country','Customer'),(4,'user4','password4','Test User 4','user4@example.com','555-01004','4 Main St, City, Country','Customer'),(5,'user5','password5','Test User 5','user5@example.com','555-01005','5 Main St, City, Country','Admin'),(6,'user6','password6','Test User 6','user6@example.com','555-01006','6 Main St, City, Country','Customer'),(7,'user7','password7','Test User 7','user7@example.com','555-01007','7 Main St, City, Country','Customer'),(8,'user8','password8','Test User 8','user8@example.com','555-01008','8 Main St, City, Country','Customer'),(9,'user9','password9','Test User 9','user9@example.com','555-01009','9 Main St, City, Country','Customer'),(10,'user10','password10','Test User 10','user10@example.com','555-01010','10 Main St, City, Country','Admin'),(11,'user11','password11','Test User 11','user11@example.com','555-01011','11 Main St, City, Country','Customer'),(12,'user12','password12','Test User 12','user12@example.com','555-01012','12 Main St, City, Country','Customer'),(13,'user13','password13','Test User 13','user13@example.com','555-01013','13 Main St, City, Country','Customer'),(14,'user14','password14','Test User 14','user14@example.com','555-01014','14 Main St, City, Country','Customer'),(15,'user15','password15','Test User 15','user15@example.com','555-01015','15 Main St, City, Country','Admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-11 13:14:35
