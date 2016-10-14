# Back-End-Zup-Test


No desenvolvimento foram utilizados os frameworks Spring, Hibernate/JPA, CDI e Jackson.

Criacao da Tabela Utilizada:

CREATE TABLE PONTO_INTERESSE (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(150) DEFAULT NULL,
  COORDENADA_X INT(11) DEFAULT NULL,
  COORDENADA_Y INT(11) DEFAULT NULL,
  PRIMARY KEY (ID)
);

Servicos como:

1) Consulta POIs por proximidade
http://localhost:8080/xy-inc/rest/pontointeresse/coordenadas/{x}/{y}/{dmax}
Sendo: {x} - Coordenada X, {y} - Coordenada Y, {dmax} - Distancia maxima

Request:
http://localhost:8080/xy-inc/rest/pontointeresse/coordenadas/20/10/10

Response:
[ {
  "id" : 1,
  "nome" : "Lanchonete",
  "coordenadaX" : 27,
  "coordenadaY" : 12
}, {
  "id" : 3,
  "nome" : "Joalheria",
  "coordenadaX" : 15,
  "coordenadaY" : 12
}, {
  "id" : 5,
  "nome" : "Pub",
  "coordenadaX" : 12,
  "coordenadaY" : 8
}, {
  "id" : 6,
  "nome" : "Supermercado",
  "coordenadaX" : 23,
  "coordenadaY" : 6
} ]

- Listar todos POIs

Request:
http://localhost:8080/xy-inc/rest/pontointeresse/list

Response:
[ {
  "id" : 1,
  "nome" : "Lanchonete",
  "coordenadaX" : 27,
  "coordenadaY" : 12
}, {
  "id" : 2,
  "nome" : "Posto",
  "coordenadaX" : 31,
  "coordenadaY" : 18
}, {
  "id" : 3,
  "nome" : "Joalheria",
  "coordenadaX" : 15,
  "coordenadaY" : 12
}, {
  "id" : 4,
  "nome" : "Floricultura",
  "coordenadaX" : 19,
  "coordenadaY" : 21
}, {
  "id" : 5,
  "nome" : "Pub",
  "coordenadaX" : 12,
  "coordenadaY" : 8
}, {
  "id" : 6,
  "nome" : "Supermercado",
  "coordenadaX" : 23,
  "coordenadaY" : 6
}, {
  "id" : 7,
  "nome" : "Churrascaria",
  "coordenadaX" : 28,
  "coordenadaY" : 2
} ]

3) Serviço para adicionar POI

Request:
http://localhost:8080/xy-inc/rest/pontointeresse/add/Sorveteria/21/11

Response:
{
  "id" : 8,
  "nome" : "Sorveteria",
  "coordenadaX" : 21,
  "coordenadaY" : 11
}
