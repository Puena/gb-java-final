# Задания 1-5 Linux
## Задача 1
Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).
```bash
test@test:~$ cat > Домашние_животные
собаки
кошки
хомяки

test@gb:~$ cat > Вьючные_животные
лошади
верблюды
ослы

test@gb:~$ cat Домашние_животные Вьючные_животные > Друзья_человека
test@gb:~$ cat Друзья_человека
собаки
кошки
хомяки
лошади
верблюды
ослы
```
## Задача 2
Создать директорию, переместить файл туда.
```bash
test@gb:~$ mkdir animals
test@gb:~$ mv Друзья_человека animals
test@gb:~$ cd animals
test@gb:~/animals$ ls
Друзья_человека
```
## Задача 3
Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
```bash
test@gb:~$ sudo apt-get update
test@gb:~$ sudo apt-get install mysql-server
```
## Задача 4
Установить и удалить deb-пакет с помощью dpkg.
```bash
test@gb:~$ wget https://download.docker.com/linux/ubuntu/dists/jammy/pool/stable/arm64/docker-ce-cli_24.0.2-1~ubuntu.22.04~jammy_arm64.deb
test@gb:~$ sudo dpkg -i docker-ce-cli_24.0.2-1~ubuntu.22.04~jammy_arm64.deb
test@gb:~$ sudo dpkg -r docker-ce-cli
```