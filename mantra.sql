-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 30, 2024 at 03:57 PM
-- Server version: 10.11.7-MariaDB-cll-lve
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u949606062_mantra`
--

-- --------------------------------------------------------

--
-- Table structure for table `cabania`
--

CREATE TABLE `cabania` (
  `cabania_id` int(11) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `imagen_id` int(11) DEFAULT NULL,
  `reserva_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `cabania`
--

INSERT INTO `cabania` (`cabania_id`, `capacidad`, `descripcion`, `estado`, `nombre`, `imagen_id`, `reserva_id`) VALUES
(1, 5, 'Una hermosa cabaña rodeada de naturaleza, y la belleza natural de nuestro departamento, contaras con acceso al bosque local, un lago pequeño y una muy comoda zona de camping para que disfrutes tu con tus amigos o pareja', b'1', 'Mantra - Cabaña', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cabania_seq`
--

CREATE TABLE `cabania_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `cabania_seq`
--

INSERT INTO `cabania_seq` (`next_val`) VALUES
(301);

-- --------------------------------------------------------

--
-- Table structure for table `imagenes`
--

CREATE TABLE `imagenes` (
  `imagen_id` int(11) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `ruta` varchar(255) DEFAULT NULL,
  `tamano` bigint(20) DEFAULT NULL,
  `cabania_id` int(11) DEFAULT NULL,
  `informacion_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `imagenes`
--

INSERT INTO `imagenes` (`imagen_id`, `file_name`, `file_type`, `ruta`, `tamano`, `cabania_id`, `informacion_id`) VALUES
(2, 'Foto cabaña noche.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\Foto cabaña noche.jpeg', 154276, 1, NULL),
(3, 'foto bosque.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto bosque.jpeg', 305271, 1, NULL),
(4, 'foto interior cabaña.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto interior cabaña.jpeg', 174623, 1, NULL),
(5, 'foto baño cabaña.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto baño cabaña.jpeg', 134077, 1, NULL),
(6, 'foto baño cabaña.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto baño cabaña.jpeg', 134077, 1, NULL),
(7, 'foto cabaña atardecer.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto cabaña atardecer.jpeg', 128565, 1, NULL),
(8, 'foto cabaña dia.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto cabaña dia.jpeg', 172503, 1, NULL),
(9, 'foto cuarto.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto cuarto.jpeg', 145708, 1, NULL),
(10, 'foto amaca tarde.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto amaca tarde.jpeg', 84899, 1, NULL),
(11, 'foto amaca tarde 2.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto amaca tarde 2.jpeg', 137030, 1, NULL),
(12, 'foto balcon.jpeg', 'image/jpeg', 'src\\main\\resources\\img\\foto balcon.jpeg', 130463, 1, NULL),
(52, 'MANTRA-removebg-preview.png', 'image/png', 'src\\main\\resources\\img\\MANTRA-removebg-preview.png', 124517, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `imagenes_seq`
--

CREATE TABLE `imagenes_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `imagenes_seq`
--

INSERT INTO `imagenes_seq` (`next_val`) VALUES
(451);

-- --------------------------------------------------------

--
-- Table structure for table `informacion`
--

CREATE TABLE `informacion` (
  `id` int(11) NOT NULL,
  `seccion` varchar(255) DEFAULT NULL,
  `texto` varchar(2550) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `icono_servicio` varchar(255) DEFAULT NULL,
  `imagen_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `informacion`
--

INSERT INTO `informacion` (`id`, `seccion`, `texto`, `titulo`, `icono_servicio`, `imagen_id`) VALUES
(1, 'Titulo-pagina', 'Cabaña de campo', 'MANTRA', NULL, NULL),
(2, 'Titulo-principal', 'Cabaña de campo', 'MANTRA', NULL, NULL),
(4, 'Conocenos', 'Somos un glamping ubicado en el hermoso corregimiento de Santa Elena, contamos con una hermosa vista, zonas naturales donde podrás conectar con la naturaleza, con una hermosa cabaña, una zona de camping, un bosque e incluso un pequeño riachuelo del cual podrás disfrutas, además somos pet friendly así que anímate a traer a tus mascotas contigo', 'Conocenos', NULL, NULL),
(52, 'Servicios', 'Ofrecemes un hermoso espacio para que descanses y disfrutes', 'Hospedaje', 'fa fa-h-square', NULL),
(53, 'Servicios', 'Ofrecemos la oportunidad para que conectes con la naturaleza', 'Naturaleza', 'fa-solid fa-sun', NULL),
(102, 'Servicios', 'Somos un lugar pet friendly para que traigas a tus peluditos y disfrutes con ellos', 'Pet Friendly', 'fa-solid fa-dog', NULL),
(103, 'Servicios', 'Aparte de nuestra cabaña, contamos con un espacio excelente para que establezcas tu zona de camping', 'Camping', 'fa-solid fa-tents', NULL),
(152, 'Contactanos', 'https://www.instagram.com/cabinmantra?igsh=NGxqNTZqYjZzbXgx', 'Instagram', 'fa-brands fa-instagram', NULL),
(153, 'Contactanos', 'https://www.facebook.com/profile.php?id=100094562552877&locale=es_LA', 'Facebook', 'fa-brands fa-facebook', NULL),
(154, 'Contactanos', 'https://api.whatsapp.com/send/?phone=573052126060&text&type=phone_number&app_absent=0', 'Whatsapp', 'fa-brands fa-whatsapp', NULL),
(202, 'Servicios', 'Ofrecemos una caminata por toda nuestra zona natural', 'Caminatas', 'fa-solid fa-shoe-prints', NULL),
(203, 'Servicios', 'Ofrecemos una aventura en biciletas por nuestra zona natural', 'Bicicletas', 'fa-solid fa-person-biking', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `informacion_seq`
--

CREATE TABLE `informacion_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `informacion_seq`
--

INSERT INTO `informacion_seq` (`next_val`) VALUES
(351);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_token`
--

CREATE TABLE `password_reset_token` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `usuario_usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `password_reset_token`
--

INSERT INTO `password_reset_token` (`id`, `email`, `expiration_date`, `token`, `usuario_usuario_id`) VALUES
(1, 'josedavids1234@gmail.com', '2024-05-19 23:45:10.240397', '9b2faea4-206d-4509-a428-28cdb531f7d1', NULL),
(2, 'josedavids1234@gmail.com', '2024-05-20 00:02:46.431147', 'dac41a8e-a0ef-4b36-8a0c-22ee08695da2', NULL),
(52, 'josedavids1234@gmail.com', '2024-05-20 00:05:48.437265', '368e0c2c-6f39-4628-9d31-68d27ddd91ee', NULL),
(102, 'josedavids1234@gmail.com', '2024-05-20 00:24:07.810181', 'd50a224b-3e81-40c7-9983-6f195540fc45', NULL),
(152, 'josedavids1234@gmail.com', '2024-05-20 00:25:58.788028', '1fac7522-6b96-4e52-8367-4ec41f0088c2', NULL),
(202, 'josedavids1234@gmail.com', '2024-05-20 01:11:29.474895', 'c5acf007-d942-4177-8cfb-731dd8e6f7b6', NULL),
(252, 'josedavids1234@gmail.com', '2024-05-20 01:21:23.271945', '9c56fd32-b911-4174-ad0d-19aa4eb21db2', NULL),
(302, 'josedavids1234@gmail.com', '2024-05-20 01:31:55.840281', 'd02e3e3e-e48b-481f-bb65-3fc693358427', NULL),
(352, 'josedavids1234@gmail.com', '2024-05-20 01:33:26.540794', '5ed467fd-8c2f-4a92-98ea-da8491391778', NULL),
(402, 'josedavids1234@gmail.com', '2024-05-20 01:39:24.854658', 'ff396d58-f882-4125-bf93-cc98300a4517', NULL),
(452, 'josedavids1234@gmail.com', '2024-05-20 01:42:37.789799', '78199ed4-f38e-4908-9c88-76d02810daca', NULL),
(502, 'josedavids1234@gmail.com', '2024-05-20 01:43:50.587639', '2eee20f8-12e5-4388-9708-eb11e38e7242', NULL),
(552, 'josedavids1234@gmail.com', '2024-05-20 01:47:13.654511', '35ed26af-ea52-4ad4-8bea-f81d3ed8310b', NULL),
(602, 'josedavids1234@gmail.com', '2024-05-20 01:49:31.809317', 'e5f312e4-ae64-4e46-bb67-a99f627e21a5', NULL),
(652, 'josedavids1234@gmail.com', '2024-05-20 01:52:14.290606', 'a3d99012-3998-48b1-8e6e-250ba1a69115', NULL),
(702, 'josedavids1234@gmail.com', '2024-05-20 19:55:22.643983', '9489cd94-81e4-40e8-9049-203e54f72206', NULL),
(752, 'josedavids1234@gmail.com', '2024-05-20 20:06:06.673114', '5b519f19-2880-4a10-9bff-c71951f5657e', NULL),
(802, 'josedavids1234@gmail.com', '2024-05-20 20:09:42.499482', '17b1c232-9320-4b22-b52d-3b503cd70918', NULL),
(852, 'josedavids1234@gmail.com', '2024-05-20 20:10:39.156683', '4793c1db-ce34-44b8-830e-a5b6ffd3a1c3', NULL),
(902, 'josedavids1234@gmail.com', '2024-05-20 20:11:55.683732', 'cbcec715-8c12-4bd6-873c-79db60f2a48b', NULL),
(952, 'josedavids1234@gmail.com', '2024-05-20 20:28:43.833726', 'eec24b24-4522-4ef4-91b2-05ddf42e028b', NULL),
(1002, 'josedavids1234@gmail.com', '2024-05-20 20:30:10.462286', '23bac992-b86f-40a1-abd8-7e3c5f42182f', NULL),
(1052, 'josedavids1234@gmail.com', '2024-05-20 20:34:03.743382', '03a45a54-c6bd-4787-b108-6e1c43b28ccc', NULL),
(1102, 'josedavids1234@gmail.com', '2024-05-20 21:14:03.812903', '58f1a0c5-9849-494a-8729-360073fa9973', NULL),
(1152, 'josedavids1234@gmail.com', '2024-05-20 21:20:14.623356', '0d982e4c-5bc5-4b50-bf53-267812bacf1d', NULL),
(1202, 'josedavids1234@gmail.com', '2024-05-20 21:28:23.207576', 'fd0180fb-7a3f-49e0-9e55-8182048150bf', NULL),
(1252, 'josedavids1234@gmail.com', '2024-05-20 21:33:37.187432', '16714846-c839-4c38-b10b-a0c94d7eda2c', NULL),
(1302, 'josedavids1234@gmail.com', '2024-05-20 21:45:40.445182', '48ed2143-0e07-42f4-bcce-818981e6b26c', NULL),
(1352, 'josedavids1234@gmail.com', '2024-05-20 21:50:01.304350', '541d7a62-dd47-41e4-b56a-be7a91988f89', NULL),
(1402, 'josedavids1234@gmail.com', '2024-05-20 21:54:33.089998', '38d94199-7cc2-4014-b1f5-3ac905b8d9de', NULL),
(1452, 'josedavids1234@gmail.com', '2024-05-20 21:59:56.694325', 'd5b8934b-2a31-4efd-ae55-8e4cff6297c6', NULL),
(1502, 'josedavids1234@gmail.com', '2024-05-20 22:27:53.358466', 'a2610af3-7f65-427f-8896-3fc7782c1cbe', NULL),
(1552, 'josedavids1234@gmail.com', '2024-05-20 22:47:46.944924', 'c00311f3-8a2f-41c1-90b4-34a68d066bd8', NULL),
(1602, 'josedavids1234@gmail.com', '2024-05-20 22:50:50.364722', '9565419c-2fa4-4380-b869-63a19976243f', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_token_seq`
--

CREATE TABLE `password_reset_token_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `password_reset_token_seq`
--

INSERT INTO `password_reset_token_seq` (`next_val`) VALUES
(1701);

-- --------------------------------------------------------

--
-- Table structure for table `reserva`
--

CREATE TABLE `reserva` (
  `reserva_id` int(11) NOT NULL,
  `fecha_final` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `cabania_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `reserva`
--

INSERT INTO `reserva` (`reserva_id`, `fecha_final`, `fecha_inicio`, `nombre`, `usuario_id`, `cabania_id`) VALUES
(252, '2024-06-03', '2024-06-02', 'jose', 1, 1),
(302, '2024-06-05', '2024-06-04', 'jose', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `reserva_seq`
--

CREATE TABLE `reserva_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `reserva_seq`
--

INSERT INTO `reserva_seq` (`next_val`) VALUES
(401);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `rol_id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`rol_id`, `nombre`) VALUES
(1, 'usuario'),
(2, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `roles_seq`
--

CREATE TABLE `roles_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `roles_seq`
--

INSERT INTO `roles_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL,
  `nombre_contacto_emergencia` varchar(255) DEFAULT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `contacto_emergencia` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `parentesco` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `roles_rol_id` int(11) DEFAULT NULL,
  `reserva_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`usuario_id`, `nombre_contacto_emergencia`, `contacto`, `contacto_emergencia`, `email`, `fecha_nacimiento`, `nombre`, `parentesco`, `password`, `username`, `roles_rol_id`, `reserva_id`) VALUES
(1, NULL, NULL, NULL, 'josedavids123@live.com', NULL, 'jose', NULL, '$2a$10$ag3JnNH8QSo5SNpJsgJkM.A9iUaCfrm5ZTzBhSeD1oc4v9CTkk/eG', 'user', 1, 0),
(2, NULL, NULL, NULL, 'josedavids123@gmail.com', NULL, 'jose', NULL, '$2a$10$m5IDnUww8J9fUfHdgiSFrOz4Y7zhEtfapcmIgqOJ3hI5jnqbnoW9O', 'admin', 2, 0),
(56, 'Jose', '3242780208', '3242780208', 'josedavids1234@gmail.com', '2024-05-22', 'asdasdasd', 'papa', '$2a$10$c022/TloOSo9yDDd29rDY.KSuBm9i4J5GiqTf82tL/MEMVh1135/y', 'sauma02', 1, 0),
(102, '213123', '1231231231', '123123123123123', 'asdasdasdasdasd@gmail.com', '2024-05-23', 'nombreasdsadasd', 'papa', '$2a$10$NEu4pnKFIX3H9GBIRa65GOtMqqVoljV2g.LuT7qtHefzwktHNB/Fu', 'asdasdasdasd', 1, 0),
(153, 'Jose', '3242780208', '3242780208', 'josedavids12345@gmail.com', '2024-06-06', 'asdasdasd', 'papa', '$2a$10$JEGWt5.XhaDZn2O4A5sFGuhglas1EGPu9hEvpjnITUUIkV2bm5p8u', 'sauma023', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `usuario_seq`
--

CREATE TABLE `usuario_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `usuario_seq`
--

INSERT INTO `usuario_seq` (`next_val`) VALUES
(251);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cabania`
--
ALTER TABLE `cabania`
  ADD PRIMARY KEY (`cabania_id`),
  ADD KEY `reserva_id_idx` (`reserva_id`),
  ADD KEY `imagen_id_idx` (`imagen_id`);

--
-- Indexes for table `imagenes`
--
ALTER TABLE `imagenes`
  ADD PRIMARY KEY (`imagen_id`),
  ADD KEY `FKpxv1scesq902h35ct5sdj09iw` (`cabania_id`),
  ADD KEY `FKshhkif4mtup0p1vioh8btsfgx` (`informacion_id`);

--
-- Indexes for table `informacion`
--
ALTER TABLE `informacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `imagen_id_idx` (`imagen_id`);

--
-- Indexes for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8n5lc1easqulvmavao41qabnt` (`usuario_usuario_id`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`reserva_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`rol_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`),
  ADD KEY `rol_id_idx` (`roles_rol_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cabania`
--
ALTER TABLE `cabania`
  ADD CONSTRAINT `imagen_id` FOREIGN KEY (`imagen_id`) REFERENCES `imagenes` (`imagen_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `reserva_id` FOREIGN KEY (`reserva_id`) REFERENCES `reserva` (`reserva_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `imagenes`
--
ALTER TABLE `imagenes`
  ADD CONSTRAINT `FK1gyburih3j767modsod29j7xn` FOREIGN KEY (`imagen_id`) REFERENCES `imagenes` (`imagen_id`),
  ADD CONSTRAINT `FKpxv1scesq902h35ct5sdj09iw` FOREIGN KEY (`cabania_id`) REFERENCES `cabania` (`cabania_id`),
  ADD CONSTRAINT `FKshhkif4mtup0p1vioh8btsfgx` FOREIGN KEY (`informacion_id`) REFERENCES `informacion` (`id`);

--
-- Constraints for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD CONSTRAINT `FKh9irix8gbtu4tvm25ygtchpgf` FOREIGN KEY (`usuario_usuario_id`) REFERENCES `usuario` (`usuario_id`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKmfhnf9hl19kc6bsuqckvfs5jd` FOREIGN KEY (`roles_rol_id`) REFERENCES `roles` (`rol_id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
