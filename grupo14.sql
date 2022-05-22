CREATE schema `grupo14`;
CREATE USER grupo14 identified with mysql_native_password by 'pa$$word';
GRANT ALL PRIVILEGES ON *.* to 'grupo14'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;