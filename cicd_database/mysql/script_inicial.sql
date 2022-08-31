/*Cria o banco de dados MeuDatabase*/
CREATE DATABASE IF NOT EXISTS MeuDatabase;

/*Da privilegios para o usuario "user" ao MeuDatabase*/
GRANT ALL ON MeuDatabase.* TO 'MeuUsuario';