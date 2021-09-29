
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

-- 0 student
-- 1 teacher
insert into xyz.registered_user_role (role_type) values (0);
insert into xyz.registered_user_role (role_type) values (1);

-- 0 admin
-- 1 read_all
-- 2 write_all
insert into xyz.bo_user_role (role_type) values (0);
insert into xyz.bo_user_role (role_type) values (1);
insert into xyz.bo_user_role (role_type) values (2);

select * from xyz.bo_user_role

-- bo users
insert into xyz.bo_user (full_name, username, password, id_bo_user_role) values ('admin', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1);
insert into xyz.bo_user (full_name, username, password, id_bo_user_role) values ('employee', 'employee', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 2);
insert into xyz.bo_user (full_name, username, password, id_bo_user_role) values ('employee2', 'employee2', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 3);

--
insert into xyz.registered_user (name, surname, born_date, id_registered_user_role) values ('Student', 'Student', now(), 2);
insert into xyz.registered_user (name, surname, born_date, id_registered_user_role) values ('Student2', 'Student2', now(), 2);
insert into xyz.registered_user (name, surname, born_date, id_registered_user_role) values ('Teacher', 'Teacher', now(), 1);

