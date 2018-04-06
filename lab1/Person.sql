CREATE TABLE "persons" (
id bigserial NOT NULL,
name character varying(200),
surname character varying(200),
position character varying(200),
age integer,
salary integer,

CONSTRAINT "Persons_pkey" PRIMARY KEY (id)
);
Для добавления записей в таблицу следует выполнить:
INSERT INTO persons(name, surname, position, age, salary) values ('Иванов', 'Иван','Менеджер', 25, 1000);
INSERT INTO persons(name, surname, position, age, salary) values ('Петров', 'Петер','Менеджер', 21, 1100);
INSERT INTO persons(name, surname, position, age, salary) values ('Попов', 'Сергей','Менеджер', 22, 1200);
INSERT INTO persons(name, surname, position, age, salary) values ('Сидоров', 'Никита','Менеджер', 23, 1300);
INSERT INTO persons(name, surname, position, age, salary) values ('Ростков', 'Дмитий','Менеджер', 24, 2000);
INSERT INTO persons(name, surname, position, age, salary) values ('Смирнов', 'Констонтин','Менеджер', 27, 3000);

