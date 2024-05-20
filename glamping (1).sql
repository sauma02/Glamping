-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: glamping
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `cabania`
--

DROP TABLE IF EXISTS `cabania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabania` (
  `cabania_id` int NOT NULL,
  `capacidad` int DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cabania_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabania`
--

LOCK TABLES `cabania` WRITE;
/*!40000 ALTER TABLE `cabania` DISABLE KEYS */;
INSERT INTO `cabania` VALUES (1,5,'Una hermosa cabaña rodeada de naturaleza, y la belleza natural de nuestro departamento, contaras con acceso al bosque local, un lago pequeño y una muy comoda zona de camping para que disfrutes tu con tus amigos o pareja',_binary '','Mantra - Cabaña');
/*!40000 ALTER TABLE `cabania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabania_seq`
--

DROP TABLE IF EXISTS `cabania_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabania_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabania_seq`
--

LOCK TABLES `cabania_seq` WRITE;
/*!40000 ALTER TABLE `cabania_seq` DISABLE KEYS */;
INSERT INTO `cabania_seq` VALUES (51);
/*!40000 ALTER TABLE `cabania_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `imagen_id` int NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `ruta` varchar(255) DEFAULT NULL,
  `tamano` bigint DEFAULT NULL,
  `cabania_id` int DEFAULT NULL,
  `informacion_id` int DEFAULT NULL,
  PRIMARY KEY (`imagen_id`),
  KEY `FKpxv1scesq902h35ct5sdj09iw` (`cabania_id`),
  KEY `FKshhkif4mtup0p1vioh8btsfgx` (`informacion_id`),
  CONSTRAINT `FKpxv1scesq902h35ct5sdj09iw` FOREIGN KEY (`cabania_id`) REFERENCES `cabania` (`cabania_id`),
  CONSTRAINT `FKshhkif4mtup0p1vioh8btsfgx` FOREIGN KEY (`informacion_id`) REFERENCES `informacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (2,'Foto cabaña noche.jpeg','image/jpeg','src\\main\\resources\\img\\Foto cabaña noche.jpeg',154276,1,NULL),(3,'foto bosque.jpeg','image/jpeg','src\\main\\resources\\img\\foto bosque.jpeg',305271,1,NULL),(4,'foto interior cabaña.jpeg','image/jpeg','src\\main\\resources\\img\\foto interior cabaña.jpeg',174623,1,NULL),(5,'foto baño cabaña.jpeg','image/jpeg','src\\main\\resources\\img\\foto baño cabaña.jpeg',134077,1,NULL),(6,'foto baño cabaña.jpeg','image/jpeg','src\\main\\resources\\img\\foto baño cabaña.jpeg',134077,1,NULL),(7,'foto cabaña atardecer.jpeg','image/jpeg','src\\main\\resources\\img\\foto cabaña atardecer.jpeg',128565,1,NULL),(8,'foto cabaña dia.jpeg','image/jpeg','src\\main\\resources\\img\\foto cabaña dia.jpeg',172503,1,NULL),(9,'foto cuarto.jpeg','image/jpeg','src\\main\\resources\\img\\foto cuarto.jpeg',145708,1,NULL),(10,'foto amaca tarde.jpeg','image/jpeg','src\\main\\resources\\img\\foto amaca tarde.jpeg',84899,1,NULL),(11,'foto amaca tarde 2.jpeg','image/jpeg','src\\main\\resources\\img\\foto amaca tarde 2.jpeg',137030,1,NULL),(12,'foto balcon.jpeg','image/jpeg','src\\main\\resources\\img\\foto balcon.jpeg',130463,1,NULL),(52,'MANTRA-removebg-preview.png','image/png','src\\main\\resources\\img\\MANTRA-removebg-preview.png',124517,NULL,1);
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes_seq`
--

DROP TABLE IF EXISTS `imagenes_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes_seq`
--

LOCK TABLES `imagenes_seq` WRITE;
/*!40000 ALTER TABLE `imagenes_seq` DISABLE KEYS */;
INSERT INTO `imagenes_seq` VALUES (151);
/*!40000 ALTER TABLE `imagenes_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion`
--

DROP TABLE IF EXISTS `informacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacion` (
  `id` int NOT NULL,
  `seccion` varchar(255) DEFAULT NULL,
  `texto` varchar(2550) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `icono_servicio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion`
--

LOCK TABLES `informacion` WRITE;
/*!40000 ALTER TABLE `informacion` DISABLE KEYS */;
INSERT INTO `informacion` VALUES (1,'Titulo-pagina','Cabaña de campo','MANTRA',NULL),(2,'Titulo-principal','Cabaña de campo','MANTRA',NULL),(4,'Conocenos','Somos un glamping ubicado en el hermoso corregimiento de Santa Elena, contamos con una hermosa vista, zonas naturales donde podrás conectar con la naturaleza, con una hermosa cabaña, una zona de camping, un bosque e incluso un pequeño riachuelo del cual podrás disfrutas, además somos pet friendly así que anímate a traer a tus mascotas contigo','Conocenos',NULL),(52,'Servicios','Ofrecemes un hermoso espacio para que descanses y disfrutes','Hospedaje','fa fa-h-square'),(53,'Servicios','Ofrecemos la oportunidad para que conectes con la naturaleza','Naturaleza','fa-solid fa-sun'),(102,'Servicios','Somos un lugar pet friendly para que traigas a tus peluditos y disfrutes con ellos','Pet Friendly','fa-solid fa-dog'),(103,'Servicios','Aparte de nuestra cabaña, contamos con un espacio excelente para que establezcas tu zona de camping','Camping','fa-solid fa-tents'),(152,'Contactanos','https://www.instagram.com/cabinmantra?igsh=NGxqNTZqYjZzbXgx','Instagram','fa-brands fa-instagram'),(153,'Contactanos','https://www.facebook.com/profile.php?id=100094562552877&locale=es_LA','Facebook','fa-brands fa-facebook'),(154,'Contactanos','https://api.whatsapp.com/send/?phone=573052126060&text&type=phone_number&app_absent=0','Whatsapp','fa-brands fa-whatsapp'),(202,'Servicios','Ofrecemos una caminata por toda nuestra zona natural','Caminatas','fa-solid fa-shoe-prints'),(203,'Servicios','Ofrecemos una aventura en biciletas por nuestra zona natural','Bicicletas','fa-solid fa-person-biking');
/*!40000 ALTER TABLE `informacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion_seq`
--

DROP TABLE IF EXISTS `informacion_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacion_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion_seq`
--

LOCK TABLES `informacion_seq` WRITE;
/*!40000 ALTER TABLE `informacion_seq` DISABLE KEYS */;
INSERT INTO `informacion_seq` VALUES (301);
/*!40000 ALTER TABLE `informacion_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `usuario_usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8n5lc1easqulvmavao41qabnt` (`usuario_usuario_id`),
  CONSTRAINT `FKh9irix8gbtu4tvm25ygtchpgf` FOREIGN KEY (`usuario_usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
INSERT INTO `password_reset_token` VALUES (1,'josedavids1234@gmail.com','2024-05-19 23:45:10.240397','9b2faea4-206d-4509-a428-28cdb531f7d1',NULL),(2,'josedavids1234@gmail.com','2024-05-20 00:02:46.431147','dac41a8e-a0ef-4b36-8a0c-22ee08695da2',NULL),(52,'josedavids1234@gmail.com','2024-05-20 00:05:48.437265','368e0c2c-6f39-4628-9d31-68d27ddd91ee',NULL),(102,'josedavids1234@gmail.com','2024-05-20 00:24:07.810181','d50a224b-3e81-40c7-9983-6f195540fc45',NULL),(152,'josedavids1234@gmail.com','2024-05-20 00:25:58.788028','1fac7522-6b96-4e52-8367-4ec41f0088c2',NULL),(202,'josedavids1234@gmail.com','2024-05-20 01:11:29.474895','c5acf007-d942-4177-8cfb-731dd8e6f7b6',NULL),(252,'josedavids1234@gmail.com','2024-05-20 01:21:23.271945','9c56fd32-b911-4174-ad0d-19aa4eb21db2',NULL),(302,'josedavids1234@gmail.com','2024-05-20 01:31:55.840281','d02e3e3e-e48b-481f-bb65-3fc693358427',NULL),(352,'josedavids1234@gmail.com','2024-05-20 01:33:26.540794','5ed467fd-8c2f-4a92-98ea-da8491391778',NULL),(402,'josedavids1234@gmail.com','2024-05-20 01:39:24.854658','ff396d58-f882-4125-bf93-cc98300a4517',NULL),(452,'josedavids1234@gmail.com','2024-05-20 01:42:37.789799','78199ed4-f38e-4908-9c88-76d02810daca',NULL),(502,'josedavids1234@gmail.com','2024-05-20 01:43:50.587639','2eee20f8-12e5-4388-9708-eb11e38e7242',NULL),(552,'josedavids1234@gmail.com','2024-05-20 01:47:13.654511','35ed26af-ea52-4ad4-8bea-f81d3ed8310b',NULL),(602,'josedavids1234@gmail.com','2024-05-20 01:49:31.809317','e5f312e4-ae64-4e46-bb67-a99f627e21a5',NULL),(652,'josedavids1234@gmail.com','2024-05-20 01:52:14.290606','a3d99012-3998-48b1-8e6e-250ba1a69115',NULL),(702,'josedavids1234@gmail.com','2024-05-20 19:55:22.643983','9489cd94-81e4-40e8-9049-203e54f72206',NULL),(752,'josedavids1234@gmail.com','2024-05-20 20:06:06.673114','5b519f19-2880-4a10-9bff-c71951f5657e',NULL),(802,'josedavids1234@gmail.com','2024-05-20 20:09:42.499482','17b1c232-9320-4b22-b52d-3b503cd70918',NULL),(852,'josedavids1234@gmail.com','2024-05-20 20:10:39.156683','4793c1db-ce34-44b8-830e-a5b6ffd3a1c3',NULL),(902,'josedavids1234@gmail.com','2024-05-20 20:11:55.683732','cbcec715-8c12-4bd6-873c-79db60f2a48b',NULL),(952,'josedavids1234@gmail.com','2024-05-20 20:28:43.833726','eec24b24-4522-4ef4-91b2-05ddf42e028b',NULL),(1002,'josedavids1234@gmail.com','2024-05-20 20:30:10.462286','23bac992-b86f-40a1-abd8-7e3c5f42182f',NULL),(1052,'josedavids1234@gmail.com','2024-05-20 20:34:03.743382','03a45a54-c6bd-4787-b108-6e1c43b28ccc',NULL),(1102,'josedavids1234@gmail.com','2024-05-20 21:14:03.812903','58f1a0c5-9849-494a-8729-360073fa9973',NULL),(1152,'josedavids1234@gmail.com','2024-05-20 21:20:14.623356','0d982e4c-5bc5-4b50-bf53-267812bacf1d',NULL),(1202,'josedavids1234@gmail.com','2024-05-20 21:28:23.207576','fd0180fb-7a3f-49e0-9e55-8182048150bf',NULL),(1252,'josedavids1234@gmail.com','2024-05-20 21:33:37.187432','16714846-c839-4c38-b10b-a0c94d7eda2c',NULL),(1302,'josedavids1234@gmail.com','2024-05-20 21:45:40.445182','48ed2143-0e07-42f4-bcce-818981e6b26c',NULL),(1352,'josedavids1234@gmail.com','2024-05-20 21:50:01.304350','541d7a62-dd47-41e4-b56a-be7a91988f89',NULL),(1402,'josedavids1234@gmail.com','2024-05-20 21:54:33.089998','38d94199-7cc2-4014-b1f5-3ac905b8d9de',NULL),(1452,'josedavids1234@gmail.com','2024-05-20 21:59:56.694325','d5b8934b-2a31-4efd-ae55-8e4cff6297c6',NULL),(1502,'josedavids1234@gmail.com','2024-05-20 22:27:53.358466','a2610af3-7f65-427f-8896-3fc7782c1cbe',NULL),(1552,'josedavids1234@gmail.com','2024-05-20 22:47:46.944924','c00311f3-8a2f-41c1-90b4-34a68d066bd8',NULL),(1602,'josedavids1234@gmail.com','2024-05-20 22:50:50.364722','9565419c-2fa4-4380-b869-63a19976243f',NULL);
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token_seq`
--

DROP TABLE IF EXISTS `password_reset_token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token_seq`
--

LOCK TABLES `password_reset_token_seq` WRITE;
/*!40000 ALTER TABLE `password_reset_token_seq` DISABLE KEYS */;
INSERT INTO `password_reset_token_seq` VALUES (1701);
/*!40000 ALTER TABLE `password_reset_token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `reserva_id` int NOT NULL,
  `fecha_final` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `cabania_id` int DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`reserva_id`),
  KEY `FK79y2m4w90hfwltq6nx7xrw2sd` (`cabania_id`),
  KEY `FKiad9w96t12u3ms2ul93l97mel` (`usuario_id`),
  CONSTRAINT `FK79y2m4w90hfwltq6nx7xrw2sd` FOREIGN KEY (`cabania_id`) REFERENCES `cabania` (`cabania_id`),
  CONSTRAINT `FKiad9w96t12u3ms2ul93l97mel` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_seq`
--

DROP TABLE IF EXISTS `reserva_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_seq`
--

LOCK TABLES `reserva_seq` WRITE;
/*!40000 ALTER TABLE `reserva_seq` DISABLE KEYS */;
INSERT INTO `reserva_seq` VALUES (1);
/*!40000 ALTER TABLE `reserva_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `rol_id` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'usuario'),(2,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_seq`
--

DROP TABLE IF EXISTS `roles_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_seq`
--

LOCK TABLES `roles_seq` WRITE;
/*!40000 ALTER TABLE `roles_seq` DISABLE KEYS */;
INSERT INTO `roles_seq` VALUES (101);
/*!40000 ALTER TABLE `roles_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuario_id` int NOT NULL,
  `nombre_contacto_emergencia` varchar(255) DEFAULT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `contacto_emergencia` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `parentesco` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `roles_rol_id` int DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  KEY `rol_id_idx` (`roles_rol_id`),
  CONSTRAINT `rol_id` FOREIGN KEY (`roles_rol_id`) REFERENCES `roles` (`rol_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,NULL,NULL,NULL,'josedavids123@live.com',NULL,'jose',NULL,'$2a$10$ag3JnNH8QSo5SNpJsgJkM.A9iUaCfrm5ZTzBhSeD1oc4v9CTkk/eG','user',1),(2,NULL,NULL,NULL,'josedavids123@gmail.com',NULL,'jose',NULL,'$2a$10$m5IDnUww8J9fUfHdgiSFrOz4Y7zhEtfapcmIgqOJ3hI5jnqbnoW9O','admin',2),(56,'Jose','3242780208','3242780208','josedavids1234@gmail.com','2024-05-22','asdasdasd','papa','$2a$10$c022/TloOSo9yDDd29rDY.KSuBm9i4J5GiqTf82tL/MEMVh1135/y','sauma02',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_seq`
--

DROP TABLE IF EXISTS `usuario_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_seq`
--

LOCK TABLES `usuario_seq` WRITE;
/*!40000 ALTER TABLE `usuario_seq` DISABLE KEYS */;
INSERT INTO `usuario_seq` VALUES (151);
/*!40000 ALTER TABLE `usuario_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-19 23:20:46
