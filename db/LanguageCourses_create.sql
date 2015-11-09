-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-11-09 19:43:13.373




-- tables
-- Table audio
CREATE TABLE audio (
    id_audio int  NOT NULL  AUTO_INCREMENT,
    audio_name varchar(50)  NOT NULL,
    audio_file_path varchar(100)  NOT NULL,
    durability int  NOT NULL,
    id_audio_genre int  NOT NULL,
    popularity int  NOT NULL  DEFAULT 0,
    id_lang int  NOT NULL,
    CONSTRAINT audio_pk PRIMARY KEY (id_audio)
);

-- Table audio_genre
CREATE TABLE audio_genre (
    id_audio_genre int  NOT NULL  AUTO_INCREMENT,
    name varchar(20)  NOT NULL,
    CONSTRAINT audio_genre_pk PRIMARY KEY (id_audio_genre)
);

-- Table course
CREATE TABLE course (
    id_course int  NOT NULL  AUTO_INCREMENT,
    course_name varchar(20)  NOT NULL,
    id_lang int  NOT NULL,
    CONSTRAINT course_pk PRIMARY KEY (id_course)
);

-- Table exercise
CREATE TABLE exercise (
    id_exercise int  NOT NULL  AUTO_INCREMENT,
    name varchar(50)  NOT NULL,
    id_type int  NOT NULL,
    CONSTRAINT exercise_pk PRIMARY KEY (id_exercise)
);

-- Table exercise_type
CREATE TABLE exercise_type (
    id_type int  NOT NULL  AUTO_INCREMENT,
    name varchar(50)  NOT NULL,
    CONSTRAINT exercise_type_pk PRIMARY KEY (id_type)
);

-- Table language
CREATE TABLE language (
    id_lang int  NOT NULL  AUTO_INCREMENT,
    lang_name varchar(10)  NOT NULL,
    CONSTRAINT language_pk PRIMARY KEY (id_lang)
);

-- Table lesson
CREATE TABLE lesson (
    id_lesson int  NOT NULL  AUTO_INCREMENT,
    lesson_name varchar(10)  NOT NULL,
    id_course int  NOT NULL,
    lesson_text text  NOT NULL,
    status varchar(10)  NOT NULL,
    id_next int  NULL,
    CONSTRAINT lesson_pk PRIMARY KEY (id_lesson)
);

-- Table text
CREATE TABLE text (
    id_text int  NOT NULL  AUTO_INCREMENT,
    text_name varchar(50)  NOT NULL,
    text text  NOT NULL,
    capacity int  NOT NULL,
    id_text_genre int  NOT NULL,
    popularity int  NOT NULL,
    id_lang int  NOT NULL,
    CONSTRAINT text_pk PRIMARY KEY (id_text)
);

-- Table text_genre
CREATE TABLE text_genre (
    id_text_genre int  NOT NULL  AUTO_INCREMENT,
    name varchar(20)  NOT NULL,
    CONSTRAINT text_genre_pk PRIMARY KEY (id_text_genre)
);

-- Table user
CREATE TABLE user (
    id_user int  NOT NULL  AUTO_INCREMENT,
    user_name varchar(20)  NOT NULL,
    login varchar(20)  NOT NULL,
    password varchar(20)  NOT NULL,
    id_lang int  NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id_user)
);

-- Table user_audio
CREATE TABLE user_audio (
    id_user int  NOT NULL,
    id_audio int  NOT NULL,
    CONSTRAINT user_audio_pk PRIMARY KEY (id_user,id_audio)
);

-- Table user_course
CREATE TABLE user_course (
    id_user int  NOT NULL,
    id_course int  NOT NULL,
    CONSTRAINT user_course_pk PRIMARY KEY (id_user,id_course)
);

-- Table user_exercise
CREATE TABLE user_exercise (
    id_user int  NOT NULL,
    id_exercise int  NOT NULL,
    CONSTRAINT user_exercise_pk PRIMARY KEY (id_user,id_exercise)
);

-- Table user_text
CREATE TABLE user_text (
    id_user int  NOT NULL,
    id_text int  NOT NULL,
    CONSTRAINT user_text_pk PRIMARY KEY (id_user,id_text)
);

-- Table user_video
CREATE TABLE user_video (
    id_user int  NOT NULL,
    id_video int  NOT NULL,
    CONSTRAINT user_video_pk PRIMARY KEY (id_user,id_video)
);

-- Table user_word
CREATE TABLE user_word (
    id_user int  NOT NULL,
    id_word int  NOT NULL,
    CONSTRAINT user_word_pk PRIMARY KEY (id_user,id_word)
);

-- Table video
CREATE TABLE video (
    id_video int  NOT NULL  AUTO_INCREMENT,
    video_name varchar(50)  NOT NULL,
    video_file_path varchar(100)  NOT NULL,
    durability int  NOT NULL,
    id_video_genre int  NOT NULL,
    popularity int  NOT NULL  DEFAULT 0,
    id_lang int  NOT NULL,
    CONSTRAINT video_pk PRIMARY KEY (id_video)
);

-- Table video_genre
CREATE TABLE video_genre (
    id_video_genre int  NOT NULL  AUTO_INCREMENT,
    name varchar(20)  NOT NULL,
    CONSTRAINT video_genre_pk PRIMARY KEY (id_video_genre)
);

-- Table word
CREATE TABLE word (
    id_word int  NOT NULL  AUTO_INCREMENT,
    value varchar(50)  NOT NULL,
    id_lang int  NOT NULL,
    translation varchar(100)  NOT NULL,
    example text  NULL,
    CONSTRAINT word_pk PRIMARY KEY (id_word)
);





-- foreign keys
-- Reference:  audio_audio_genre (table: audio)


ALTER TABLE audio ADD CONSTRAINT audio_audio_genre FOREIGN KEY audio_audio_genre (id_audio_genre) REFERENCES audio_genre (id_audio_genre);
-- Reference:  audio_language (table: audio)


ALTER TABLE audio ADD CONSTRAINT audio_language FOREIGN KEY audio_language (id_lang) REFERENCES language (id_lang);
-- Reference:  course_language (table: course)


ALTER TABLE course ADD CONSTRAINT course_language FOREIGN KEY course_language (id_lang) REFERENCES language (id_lang);
-- Reference:  course_lesson (table: lesson)


ALTER TABLE lesson ADD CONSTRAINT course_lesson FOREIGN KEY course_lesson (id_course) REFERENCES course (id_course);
-- Reference:  course_user_course (table: user_course)


ALTER TABLE user_course ADD CONSTRAINT course_user_course FOREIGN KEY course_user_course (id_course) REFERENCES course (id_course);
-- Reference:  exercise_type (table: exercise)


ALTER TABLE exercise ADD CONSTRAINT exercise_type FOREIGN KEY exercise_type (id_type) REFERENCES exercise_type (id_type);
-- Reference:  language_video (table: video)


ALTER TABLE video ADD CONSTRAINT language_video FOREIGN KEY language_video (id_lang) REFERENCES language (id_lang);
-- Reference:  language_word (table: word)


ALTER TABLE word ADD CONSTRAINT language_word FOREIGN KEY language_word (id_lang) REFERENCES language (id_lang);
-- Reference:  lesson_lesson (table: lesson)


ALTER TABLE lesson ADD CONSTRAINT lesson_lesson FOREIGN KEY lesson_lesson (id_next) REFERENCES lesson (id_lesson);
-- Reference:  text_language (table: text)


ALTER TABLE text ADD CONSTRAINT text_language FOREIGN KEY text_language (id_lang) REFERENCES language (id_lang);
-- Reference:  text_text_genre (table: text)


ALTER TABLE text ADD CONSTRAINT text_text_genre FOREIGN KEY text_text_genre (id_text_genre) REFERENCES text_genre (id_text_genre);
-- Reference:  user_audio_audio (table: user_audio)


ALTER TABLE user_audio ADD CONSTRAINT user_audio_audio FOREIGN KEY user_audio_audio (id_audio) REFERENCES audio (id_audio);
-- Reference:  user_course_user (table: user_course)


ALTER TABLE user_course ADD CONSTRAINT user_course_user FOREIGN KEY user_course_user (id_user) REFERENCES user (id_user);
-- Reference:  user_exercise_exercise (table: user_exercise)


ALTER TABLE user_exercise ADD CONSTRAINT user_exercise_exercise FOREIGN KEY user_exercise_exercise (id_exercise) REFERENCES exercise (id_exercise);
-- Reference:  user_exercise_user (table: user_exercise)


ALTER TABLE user_exercise ADD CONSTRAINT user_exercise_user FOREIGN KEY user_exercise_user (id_user) REFERENCES user (id_user);
-- Reference:  user_language (table: user)


ALTER TABLE user ADD CONSTRAINT user_language FOREIGN KEY user_language (id_lang) REFERENCES language (id_lang);
-- Reference:  user_text_text (table: user_text)


ALTER TABLE user_text ADD CONSTRAINT user_text_text FOREIGN KEY user_text_text (id_text) REFERENCES text (id_text);
-- Reference:  user_text_user (table: user_text)


ALTER TABLE user_text ADD CONSTRAINT user_text_user FOREIGN KEY user_text_user (id_user) REFERENCES user (id_user);
-- Reference:  user_user_audio (table: user_audio)


ALTER TABLE user_audio ADD CONSTRAINT user_user_audio FOREIGN KEY user_user_audio (id_user) REFERENCES user (id_user);
-- Reference:  user_video_user (table: user_video)


ALTER TABLE user_video ADD CONSTRAINT user_video_user FOREIGN KEY user_video_user (id_user)    REFERENCES user (id_user);
-- Reference:  user_word_user (table: user_word)


ALTER TABLE user_word ADD CONSTRAINT user_word_user FOREIGN KEY user_word_user (id_user)    REFERENCES user (id_user);
-- Reference:  user_word_word (table: user_word)


ALTER TABLE user_word ADD CONSTRAINT user_word_word FOREIGN KEY user_word_word (id_word)    REFERENCES word (id_word);
-- Reference:  video_genre_video (table: video)


ALTER TABLE video ADD CONSTRAINT video_genre_video FOREIGN KEY video_genre_video (id_video_genre)    REFERENCES video_genre (id_video_genre);
-- Reference:  video_user_video (table: user_video)


ALTER TABLE user_video ADD CONSTRAINT video_user_video FOREIGN KEY video_user_video (id_video)    REFERENCES video (id_video);



-- End of file.

