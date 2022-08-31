/*Este script cria nossas tabelas de exemplo no MySQL*/

CREATE TABLE Playlist
(
	Id SMALLINT NOT NULL AUTO_INCREMENT, 
    Descricao VARCHAR(100) NOT NULL, 
    PRIMARY KEY (Id)
);

CREATE TABLE Item
(
	Id INT NOT NULL AUTO_INCREMENT, 
    Id_Musica INT NOT NULL, 
    Id_Playlist SMALLINT NOT NULL, 
    PRIMARY KEY (Id), 
    FOREIGN KEY (Id_Playlist) REFERENCES Playlist(Id) 
);