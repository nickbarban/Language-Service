-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-11-16 07:19:18.473



-- foreign keys
ALTER TABLE audio DROP FOREIGN KEY audio_audio_genre;
ALTER TABLE audio DROP FOREIGN KEY audio_language;
ALTER TABLE course DROP FOREIGN KEY course_language;
ALTER TABLE lesson DROP FOREIGN KEY course_lesson;
ALTER TABLE user_course DROP FOREIGN KEY course_user_course;
ALTER TABLE exercise DROP FOREIGN KEY exercise_type;
ALTER TABLE video DROP FOREIGN KEY language_video;
ALTER TABLE word DROP FOREIGN KEY language_word;
ALTER TABLE lesson DROP FOREIGN KEY lesson_lesson;
ALTER TABLE text DROP FOREIGN KEY text_language;
ALTER TABLE text DROP FOREIGN KEY text_text_genre;
ALTER TABLE user_audio DROP FOREIGN KEY user_audio_audio;
ALTER TABLE user_course DROP FOREIGN KEY user_course_user;
ALTER TABLE user_exercise DROP FOREIGN KEY user_exercise_exercise;
ALTER TABLE user_exercise DROP FOREIGN KEY user_exercise_user;
ALTER TABLE user DROP FOREIGN KEY user_language;
ALTER TABLE user_text DROP FOREIGN KEY user_text_text;
ALTER TABLE user_text DROP FOREIGN KEY user_text_user;
ALTER TABLE user_audio DROP FOREIGN KEY user_user_audio;
ALTER TABLE user_video DROP FOREIGN KEY user_video_user;
ALTER TABLE user_word DROP FOREIGN KEY user_word_user;
ALTER TABLE user_word DROP FOREIGN KEY user_word_word;
ALTER TABLE video DROP FOREIGN KEY video_genre_video;
ALTER TABLE user_video DROP FOREIGN KEY video_user_video;

-- tables
-- Table audio
DROP TABLE audio;
-- Table audio_genre
DROP TABLE audio_genre;
-- Table course
DROP TABLE course;
-- Table exercise
DROP TABLE exercise;
-- Table exercise_type
DROP TABLE exercise_type;
-- Table language
DROP TABLE language;
-- Table lesson
DROP TABLE lesson;
-- Table text
DROP TABLE text;
-- Table text_genre
DROP TABLE text_genre;
-- Table user
DROP TABLE user;
-- Table user_audio
DROP TABLE user_audio;
-- Table user_course
DROP TABLE user_course;
-- Table user_exercise
DROP TABLE user_exercise;
-- Table user_text
DROP TABLE user_text;
-- Table user_video
DROP TABLE user_video;
-- Table user_word
DROP TABLE user_word;
-- Table video
DROP TABLE video;
-- Table video_genre
DROP TABLE video_genre;
-- Table word
DROP TABLE word;



-- End of file.

