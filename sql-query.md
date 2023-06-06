## Задача 7
В подключенном MySQL репозитории создать базу данных “Друзья человека”.

```sql
CREATE DATABASE friends_of_man;
```
## Задача 8
Создать таблицы с иерархией из диаграммы в БД.

```sql
USE friends_of_man;
CREATE TABLE animal_types
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Type_n VARCHAR(50)
);


INSERT INTO animal_types (Type_n)
VALUES ('Вьючные животные'), ('Домашние животные');


CREATE TABLE pack_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Kind_n VARCHAR(50),
    Type_id INT,
    FOREIGN KEY (Type_id) REFERENCES animal_types (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO pack_animals (Kind_n, Type_id)
VALUES ('Лошади', 1), ('Ослы', 1), ('Верблюды', 1);


CREATE TABLE pets
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Kind_n VARCHAR(50),
    Type_id INT,
    FOREIGN KEY (Type_id) REFERENCES animal_types (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO pets (Kind_n, Type_id)
VALUES ('Собаки', 2), ('Кошки', 2), ('Хомяки', 2);


CREATE TABLE dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(50), 
    Birthday DATE,
    Commands TINYTEXT,
    Kind_id INT,
    Foreign KEY (Kind_id) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(50), 
    Birthday DATE,
    Commands TINYTEXT,
    Kind_id INT,
    Foreign KEY (Kind_id) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(50), 
    Birthday DATE,
    Commands TINYTEXT,
    Kind_id INT,
    Foreign KEY (Kind_id) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(50), 
    Birthday DATE,
    Commands TINYTEXT,
    Kind_id INT,
    Foreign KEY (Kind_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(50), 
    Birthday DATE,
    Commands TINYTEXT,
    Kind_id INT,
    Foreign KEY (Kind_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE donkeys
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(50), 
    Birthday DATE,
    Commands TINYTEXT,
    Kind_id INT,
    Foreign KEY (Kind_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
```
## Задача 9
Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения

```sql
INSERT INTO dogs (Name, Birthday, Commands, Kind_id)
VALUES 
('Jack', '5010-06-06', 'сидеть, лежать, дай лапу, место', 1),
('Bad boy', '5015-01-01', 'апорт, гулять, ко мне', 1),  
('Manchester', '5022-02-03', 'сидеть, лежать, вверх, стоять', 1),  
('Willy', '5021-08-07', 'кушать', 1);


INSERT INTO cats (Name, Birthday, Commands, Kind_id)
VALUES 
('House', '5022-01-01', 'ко мне, кушать', 2),
('Mary', '5017-04-05', 'кис-кис', 2),  
('Glottany', '5019-09-08', 'кушать', 2),  
('Sky', '5050-10-10', 'кис-кис', 2);


INSERT INTO hamsters (Name, Birthday, Commands, Kind_id)
VALUES 
('Rat-1', '5023-01-01', NULL, 3),
('Rat-2', '5022-07-07', 'кушать', 3),  
('Rat-3', '5022-10-11', NULL, 3),  
('Rat-4', '5022-11-10', NULL, 3);


INSERT INTO horses (Name, Birthday, Commands, Kind_id)
VALUES 
('Apollo', '5015-01-02', 'кушать, пошли, тихо', 1),
('Wild', '5022-01-01', 'пошли, быстро, стоять, прыгай', 1),  
('Rust', '5023-02-03', 'пошли, стоять', 1),  
('Sun', '5013-03-07', 'тихо, ко мне, пошли', 1);


INSERT INTO donkeys (Name, Birthday, Commands, Kind_id)
VALUES 
('Unnkown', '5022-01-01', 'пошли, тихо', 2),
('Pappy', '5017-04-05', 'пошли, тихо, кушать', 2),  
('Flubber', '5019-09-08', 'пошли, тихо, ко мне', 2),  
('Sunrise', '5050-10-10', 'пошли, тихо', 2);

INSERT INTO camels (Name, Birthday, Commands, Kind_id)
VALUES 
('Two Hills', '5023-01-01', 'пошли, стоять, вниз, вверх, ко мне', 3),
('Bublegum', '5022-07-07', 'пошли, стоять, вниз, вверх', 3),  
('Habdala', '5022-10-11', 'пошли, стоять, вниз, вверх, кушать', 3),  
('Rage', '5022-11-10', 'пошли, стоять, вниз, вверх', 3);
```
## Задача 10
Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

```sql
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;


SELECT Name, Birthday, Commands, Kind_id  FROM horses
UNION SELECT  Name, Birthday, Commands, Kind_id  FROM donkeys;
```
## Задача 11
Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице

```sql
CREATE TABLE all_animals AS 
SELECT Name, Birthday, Commands, 'Собаки' AS Animal FROM dogs
UNION SELECT Name, Birthday, Commands, 'Кошки' AS Animal FROM cats
UNION SELECT Name, Birthday, Commands, 'Хомяки' AS Animal FROM hamsters
UNION SELECT Name, Birthday, Commands, 'Лошади' AS Animal FROM horses
UNION SELECT Name, Birthday, Commands, 'Ослы' AS Animal FROM donkeys;


CREATE TABLE young_animals AS
SELECT Name, Birthday, Commands, Animal, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM all_animals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR)


DROP TABLE all_animals;
```
## Задача 12
Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

```sql
SELECT dg.Name, dg.Birthday, dg.Commands, Kind.Kind_n, Types.Type_n, yng.Age_in_month  FROM dogs AS dg
LEFT JOIN pets AS Kind ON dg.Kind_id=Kind.Id
LEFT JOIN animal_types AS Types ON Kind.Type_id=Types.Id
LEFT JOIN young_animals As yng ON dg.Name=yng.Name

UNION SELECT c.Name, c.Birthday, c.Commands, Kind.Kind_n, Types.Type_n, yng.Age_in_month  FROM cats AS c
LEFT JOIN pets AS Kind ON c.Kind_id=Kind.Id
LEFT JOIN animal_types AS Types ON Kind.Type_id=Types.Id
LEFT JOIN young_animals As yng ON c.Name=yng.Name

UNION SELECT hm.Name, hm.Birthday, hm.Commands, Kind.Kind_n, Types.Type_n, yng.Age_in_month  FROM hamsters AS hm
LEFT JOIN pets AS Kind ON hm.Kind_id=Kind.Id
LEFT JOIN animal_types AS Types ON Kind.Type_id=Types.Id
LEFT JOIN young_animals As yng ON hm.Name=yng.Name

UNION SELECT hs.Name, hs.Birthday, hs.Commands, pack.Kind_n, Types.Type_n, yng.Age_in_month  FROM horses AS hs
LEFT JOIN pack_animals AS pack ON hs.Kind_id=pack.Id
LEFT JOIN animal_types AS Types ON pack.Type_id=Types.Id
LEFT JOIN young_animals As yng ON hs.Name=yng.Name

UNION SELECT dn.Name, dn.Birthday, dn.Commands, pack.Kind_n, Types.Type_n, yng.Age_in_month  FROM donkeys AS dn
LEFT JOIN pack_animals AS pack ON dn.Kind_id=pack.Id
LEFT JOIN animal_types AS Types ON pack.Type_id=Types.Id
LEFT JOIN young_animals As yng ON dn.Name=yng.Name;


```