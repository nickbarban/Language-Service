-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: language_courses
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audio` (
  `id_audio` int(11) NOT NULL AUTO_INCREMENT,
  `audio_name` varchar(50) NOT NULL,
  `audio_file_path` varchar(100) NOT NULL,
  `durability` int(11) NOT NULL,
  `id_audio_genre` int(11) NOT NULL,
  `popularity` int(11) NOT NULL DEFAULT '0',
  `id_lang` int(11) NOT NULL,
  PRIMARY KEY (`id_audio`),
  KEY `audio_audio_genre` (`id_audio_genre`),
  KEY `audio_language` (`id_lang`),
  CONSTRAINT `audio_language` FOREIGN KEY (`id_lang`) REFERENCES `language` (`id_lang`),
  CONSTRAINT `audio_audio_genre` FOREIGN KEY (`id_audio_genre`) REFERENCES `audio_genre` (`id_audio_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audio_genre`
--

DROP TABLE IF EXISTS `audio_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audio_genre` (
  `id_audio_genre` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_audio_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio_genre`
--

LOCK TABLES `audio_genre` WRITE;
/*!40000 ALTER TABLE `audio_genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `audio_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id_course` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) NOT NULL,
  `id_lang` int(11) NOT NULL,
  PRIMARY KEY (`id_course`),
  KEY `course_language` (`id_lang`),
  CONSTRAINT `course_language` FOREIGN KEY (`id_lang`) REFERENCES `language` (`id_lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercise` (
  `id_exercise` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `id_type` int(11) NOT NULL,
  PRIMARY KEY (`id_exercise`),
  KEY `exercise_type` (`id_type`),
  CONSTRAINT `exercise_type` FOREIGN KEY (`id_type`) REFERENCES `exercise_type` (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_type`
--

DROP TABLE IF EXISTS `exercise_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercise_type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_type`
--

LOCK TABLES `exercise_type` WRITE;
/*!40000 ALTER TABLE `exercise_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercise_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id_lang` int(11) NOT NULL AUTO_INCREMENT,
  `lang_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id_lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id_lesson` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(10) NOT NULL,
  `id_course` int(11) NOT NULL,
  `lesson_text` text NOT NULL,
  `status` varchar(10) NOT NULL,
  `id_next` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_lesson`),
  KEY `course_lesson` (`id_course`),
  KEY `lesson_lesson` (`id_next`),
  CONSTRAINT `lesson_lesson` FOREIGN KEY (`id_next`) REFERENCES `lesson` (`id_lesson`),
  CONSTRAINT `course_lesson` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text`
--

DROP TABLE IF EXISTS `text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `text` (
  `id_text` int(11) NOT NULL AUTO_INCREMENT,
  `text_name` varchar(50) NOT NULL,
  `text` text NOT NULL,
  `capacity` int(11) NOT NULL,
  `id_text_genre` int(11) NOT NULL,
  `popularity` int(11) NOT NULL,
  `id_lang` int(11) NOT NULL,
  PRIMARY KEY (`id_text`),
  KEY `text_language` (`id_lang`),
  KEY `text_text_genre` (`id_text_genre`),
  CONSTRAINT `text_text_genre` FOREIGN KEY (`id_text_genre`) REFERENCES `text_genre` (`id_text_genre`),
  CONSTRAINT `text_language` FOREIGN KEY (`id_lang`) REFERENCES `language` (`id_lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text`
--

LOCK TABLES `text` WRITE;
/*!40000 ALTER TABLE `text` DISABLE KEYS */;
/*!40000 ALTER TABLE `text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_genre`
--

DROP TABLE IF EXISTS `text_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `text_genre` (
  `id_text_genre` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_text_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_genre`
--

LOCK TABLES `text_genre` WRITE;
/*!40000 ALTER TABLE `text_genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `text_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_lang` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `user_language` (`id_lang`),
  CONSTRAINT `user_language` FOREIGN KEY (`id_lang`) REFERENCES `language` (`id_lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_audio`
--

DROP TABLE IF EXISTS `user_audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_audio` (
  `id_user` int(11) NOT NULL,
  `id_audio` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_audio`),
  KEY `user_audio_audio` (`id_audio`),
  CONSTRAINT `user_user_audio` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `user_audio_audio` FOREIGN KEY (`id_audio`) REFERENCES `audio` (`id_audio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_audio`
--

LOCK TABLES `user_audio` WRITE;
/*!40000 ALTER TABLE `user_audio` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_course`
--

DROP TABLE IF EXISTS `user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_course` (
  `id_user` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_course`),
  KEY `course_user_course` (`id_course`),
  CONSTRAINT `user_course_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `course_user_course` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_course`
--

LOCK TABLES `user_course` WRITE;
/*!40000 ALTER TABLE `user_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_exercise`
--

DROP TABLE IF EXISTS `user_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_exercise` (
  `id_user` int(11) NOT NULL,
  `id_exercise` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_exercise`),
  KEY `user_exercise_exercise` (`id_exercise`),
  CONSTRAINT `user_exercise_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `user_exercise_exercise` FOREIGN KEY (`id_exercise`) REFERENCES `exercise` (`id_exercise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_exercise`
--

LOCK TABLES `user_exercise` WRITE;
/*!40000 ALTER TABLE `user_exercise` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_text`
--

DROP TABLE IF EXISTS `user_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_text` (
  `id_user` int(11) NOT NULL,
  `id_text` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_text`),
  KEY `user_text_text` (`id_text`),
  CONSTRAINT `user_text_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `user_text_text` FOREIGN KEY (`id_text`) REFERENCES `text` (`id_text`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_text`
--

LOCK TABLES `user_text` WRITE;
/*!40000 ALTER TABLE `user_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_video`
--

DROP TABLE IF EXISTS `user_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_video` (
  `id_user` int(11) NOT NULL,
  `id_video` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_video`),
  KEY `video_user_video` (`id_video`),
  CONSTRAINT `video_user_video` FOREIGN KEY (`id_video`) REFERENCES `video` (`id_video`),
  CONSTRAINT `user_video_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_video`
--

LOCK TABLES `user_video` WRITE;
/*!40000 ALTER TABLE `user_video` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_word`
--

DROP TABLE IF EXISTS `user_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_word` (
  `id_user` int(11) NOT NULL,
  `id_word` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_word`),
  KEY `user_word_word` (`id_word`),
  CONSTRAINT `user_word_word` FOREIGN KEY (`id_word`) REFERENCES `word` (`id_word`),
  CONSTRAINT `user_word_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_word`
--

LOCK TABLES `user_word` WRITE;
/*!40000 ALTER TABLE `user_word` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `id_video` int(11) NOT NULL AUTO_INCREMENT,
  `video_name` varchar(50) NOT NULL,
  `video_file_path` varchar(100) NOT NULL,
  `durability` int(11) NOT NULL,
  `id_video_genre` int(11) NOT NULL,
  `popularity` int(11) NOT NULL DEFAULT '0',
  `id_lang` int(11) NOT NULL,
  PRIMARY KEY (`id_video`),
  KEY `language_video` (`id_lang`),
  KEY `video_genre_video` (`id_video_genre`),
  CONSTRAINT `video_genre_video` FOREIGN KEY (`id_video_genre`) REFERENCES `video_genre` (`id_video_genre`),
  CONSTRAINT `language_video` FOREIGN KEY (`id_lang`) REFERENCES `language` (`id_lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_genre`
--

DROP TABLE IF EXISTS `video_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_genre` (
  `id_video_genre` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_video_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_genre`
--

LOCK TABLES `video_genre` WRITE;
/*!40000 ALTER TABLE `video_genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `video_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word`
--

DROP TABLE IF EXISTS `word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word` (
  `id_word` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) NOT NULL,
  `id_lang` int(11) NOT NULL,
  `translation` varchar(100) NOT NULL,
  `example` text,
  PRIMARY KEY (`id_word`),
  KEY `language_word` (`id_lang`),
  CONSTRAINT `language_word` FOREIGN KEY (`id_lang`) REFERENCES `language` (`id_lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word`
--

LOCK TABLES `word` WRITE;
/*!40000 ALTER TABLE `word` DISABLE KEYS */;
/*!40000 ALTER TABLE `word` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-09 22:21:05
