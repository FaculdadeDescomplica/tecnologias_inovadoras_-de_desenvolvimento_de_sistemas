--Utilizar o usuário SYS, que possui privilegios administrativos

--A criacao de uma tablespace permite trabalharmos em um espaco segregado para nossas tabelas
CREATE TABLESPACE MeuTablespace DATAFILE 'MeuTablespace.dbf' SIZE 1m;

--A instrucao abaixo permite a execucao dos scripts administrativos de criacao de usuario e suas permissoes
ALTER SESSION SET "_ORACLE_SCRIPT" = True;

--A senha esta entre aspas duplas para o Oracle reconhecer letras maiusculas e minusculas
--Nao esquecer de atribuir a tablespace ao usuario, pois assim ao fazer login com o usuario, ja estara na tablespace correta
CREATE USER MEUUSUARIO IDENTIFIED BY "MinhaSenha" DEFAULT TABLESPACE MeuTablespace;

--De as permissoes para o usuario recem criado, neste exemplo permite inclusive a criacao de tabelas entre outros recursos
GRANT CREATE SESSION, CREATE TABLE, CREATE ANY INDEX, UNLIMITED TABLESPACE, RESOURCE TO MEUUSUARIO;