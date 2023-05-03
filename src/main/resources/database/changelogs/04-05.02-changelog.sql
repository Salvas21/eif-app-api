-- Levels
insert into "Level" (name)
values ('Débutant');

insert into "Level" (name)
values ('Intermédiaire');

insert into "Level" (name)
values ('Avancé');

-- Admin
insert into "Admin" (email, password, ce, first_name, last_name)
values ('admin@admin.com', '$2a$10$6YJfO8qqxQWH0JrJ7Sjf8Op4jaJ154/MMZZ84UfAtpwOpuk.wE0A6', 'AADM12312312', 'Admin', 'Name');

-- Students
insert into "Student" (email, password, cp, first_name, last_name, level_id)
values ('martin@sandwich.com', '$2a$10$6YJfO8qqxQWH0JrJ7Sjf8Op4jaJ154/MMZZ84UfAtpwOpuk.wE0A6', 'SANM12345678', 'Martin', 'Sandwich', 2);

insert into "Student" (email, password, cp, first_name, last_name, level_id)
values ('bob@bob.com', '$2a$10$6YJfO8qqxQWH0JrJ7Sjf8Op4jaJ154/MMZZ84UfAtpwOpuk.wE0A6', 'BOBB87654321', 'Bob', 'Bob', 1);

-- Activities
insert into "Activity" (code, name, description, level_id)
values ('EIFR10', 'Pièce de théâtre', 'Faire l''écriture d''une pièce de théâtre, pour finalement la présenter', 1);

insert into "Activity" (code, name, description, level_id)
values ('EIFR11', 'Lecture d''un poème', 'Faire la lecture d''un roman de votre choix pour mettre en pratique votre interprétation des émotions', 2);

insert into "Activity" (code, name, description, level_id)
values ('EIFR12', 'Interprétation d''une pièce de théâtre', 'Faire l''interprétation d''une pièce de théâtre historique, il faudra pratiquer la diction, et les émotions de l''interprétation', 3);

insert into "Activity" (code, name, description, level_id)
values ('EIFR09', 'Analyse d''une pièce de théâtre', 'Faire l''analyse d''une pièce de théâtre, sortir les différentes émotions des personnages, leurs motifs', 1);

insert into "Activity" (code, name, description, level_id)
values ('TEST1', 'Test Débutant', 'Description d''une activité débutante', 1);

insert into "Activity" (code, name, description, level_id)
values ('TEST2', 'Test Intermédiaire', 'Description d''une activité intermédiaire', 2);

insert into "Activity" (code, name, description, level_id)
values ('TEST3', 'Test Avancé', 'Description d''une activité avancée', 3);

-- Choices
insert into "Choice" (preference, activity_id, student_id)
values (1, 2, 1);

insert into "Choice" (preference, activity_id, student_id)
values (2, 6, 1);

insert into "Choice" (preference, activity_id, student_id)
values (3, 4, 1);


insert into "Choice" (preference, activity_id, student_id)
values (1, 1, 2);

insert into "Choice" (preference, activity_id, student_id)
values (2, 4, 2);

insert into "Choice" (preference, activity_id, student_id)
values (3, 5, 2);

-- Attendance
insert into "Attendance" (session, activity_id, student_id)
values ('AUT22', 1, 1);

insert into "Attendance" (session, activity_id, student_id)
values ('HIV22', 5, 1);

insert into "Attendance" (session, activity_id, student_id)
values ('AUT22', 5, 2)