
CREATE TABLE IF NOT EXISTS xyz.`registered_user` (
  `id_registered_user` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `surname` varchar(64) NOT NULL,
  `fiscal_code` varchar(64) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `born_date` date NOT NULL,
  `user_role` varchar(32) NOT NULL,
  PRIMARY KEY (`id_registered_user`)
)


CREATE TABLE IF NOT EXISTS xyz.`bo_user` (
  `id_bo_user` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(512) NOT NULL,
  `user_role` varchar(32) NOT NULL,
  PRIMARY KEY (`id_bo_user`)
);
 
CREATE TABLE IF NOT EXISTS xyz.course (
  `id_course` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(64) NOT null,
  PRIMARY KEY (`id_course`)
); 

CREATE table if not exists xyz.partecipant (
	id_partecipant BIGINT NOT NULL AUTO_INCREMENT,
	id_course BIGINT NOT NULL,
	id_registered_user BIGINT NOT NULL,
	CONSTRAINT partecipant_pk PRIMARY KEY (id_partecipant),
	CONSTRAINT partecipant_fk_cu FOREIGN KEY (id_course) REFERENCES xyz.course(id_course),
	CONSTRAINT partecipant_fk_ru FOREIGN KEY (id_registered_user) REFERENCES xyz.registered_user(id_registered_user)
);


-- dummy data
insert into xyz.course (name, description) values ('Java', 'Corso di java')

-- bo users
insert into xyz.bo_user (full_name, username, password, user_role) values ('admin', 'admin', '$2a$10$uQq7EiULkNIrIHlKsqIyh.NGy6LEmBFf25w/qWYIwoeD7L0i1.A7q', 'ADMIN');
insert into xyz.bo_user (full_name, username, password, user_role) values ('employee', 'employee', '$2a$10$uQq7EiULkNIrIHlKsqIyh.NGy6LEmBFf25w/qWYIwoeD7L0i1.A7q', 'READ_ALL');
insert into xyz.bo_user (full_name, username, password, user_role) values ('employee2', 'employee2', '$2a$10$uQq7EiULkNIrIHlKsqIyh.NGy6LEmBFf25w/qWYIwoeD7L0i1.A7q', 'WRITE_ALL');

-- registered user
insert into xyz.registered_user (name, surname, born_date, user_role) values ('Student', 'Student', now(), 'STUDENT');
insert into xyz.registered_user (name, surname, born_date, user_role) values ('Student2', 'Student2', now(), 'STUDENT');
insert into xyz.registered_user (name, surname, born_date, user_role) values ('Teacher', 'Teacher', now(), 'TEACHER');

