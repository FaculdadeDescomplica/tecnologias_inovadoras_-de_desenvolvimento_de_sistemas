--Este script cria nossas tabelas de exemplo no Oracle

CREATE TABLE Artista 
(
    Id NUMBER NOT NULL, 
    Nome_Artista VARCHAR(100) NOT NULL, 
    PRIMARY KEY (Id) 
);

CREATE TABLE Album 
(
    Id NUMBER NOT NULL, 
    Nome_Album VARCHAR(100) NOT NULL, 
    Ano SMALLINT NOT NULL, 
    Id_Artista NUMBER NOT NULL, 
    PRIMARY KEY (Id), 
    CONSTRAINT FK_Artista FOREIGN KEY (Id_Artista) REFERENCES Artista (Id) 
);

CREATE TABLE Musica 
(
    Id NUMBER NOT NULL, 
    Nome_Musica VARCHAR(200) NOT NULL, 
    Duracao NUMERIC (4, 2) NOT NULL, 
    Id_Album NUMBER NOT NULL, 
    PRIMARY KEY (Id), 
    CONSTRAINT FK_Album FOREIGN KEY (Id_Album) REFERENCES Album (Id) 
);

--Estes objetos do tipo Sequence serao necessarias para atribuir o Id das tabelas, o valor das chaves primarias
--Foi necessario como artificio para a geracao automatica via aplicacao em Java SpringBoot
--Mas poderia ser utilizado naturalmente em qualquer situacao, em substituicao ao uso de Identity automatico na tabela
--A vantagem da Sequence nota-se no controle de configuracao possivel de se fazer, conforme scripts abaixo

CREATE SEQUENCE Artista_Seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE Album_Seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE Musica_Seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;