


DROP SCHEMA IF EXISTS AGRICOLA;
CREATE SCHEMA AGRICOLA;
USE AGRICOLA;





create table Explotacion(
	Num_registro INTEGER,
	Direccion VARCHAR(100),
	PRIMARY KEY (Num_registro)
	)ENGINE=InnoDB;	



create table Parcelas(
	codigo INTEGER,
	coordenadas  VARCHAR(40),
	Num_registro_e INTEGER,
	PRIMARY KEY (codigo),
	FOREIGN KEY (Num_registro_e ) REFERENCES Explotacion(Num_registro) ON DELETE RESTRICT ON UPDATE CASCADE


	)ENGINE=InnoDB;	





create table Especies(
	identificacion INTEGER,
	nombre VARCHAR(40),
	PRIMARY KEY (identificacion)
	)ENGINE=InnoDB;	






create table Cultivan(
	cod_parcela INTEGER,
	iden_especie INTEGER,
	superficie INTEGER,
	PRIMARY KEY (cod_parcela,iden_especie),
	FOREIGN KEY (cod_parcela) REFERENCES Parcelas(codigo) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (iden_especie) REFERENCES Especies(identificacion) ON DELETE RESTRICT ON UPDATE CASCADE

	)ENGINE=InnoDB;	






create table Punto_Agua(
	identificacion INTEGER,
	caudal INTEGER,
	coordenadas VARCHAR(40),
	PRIMARY KEY (identificacion)
	)ENGINE=InnoDB;	







create table Existe(
	iden_punto_agua INTEGER,
	cod_parcela INTEGER,
	PRIMARY KEY (iden_punto_agua,cod_parcela),

	FOREIGN KEY (iden_punto_agua) REFERENCES Punto_Agua(identificacion) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (cod_parcela) REFERENCES Parcelas(codigo) ON DELETE RESTRICT ON UPDATE CASCADE
	

	)ENGINE=InnoDB;	








INSERT INTO Explotacion VALUES (100,'Boadilla del monte 28660');

INSERT INTO Parcelas VALUES (1,'coordenadas 1',100);

INSERT INTO Parcelas VALUES (2,'coordenadas 2',100);


INSERT INTO Parcelas VALUES (3,'coordenadas 3',100);

INSERT INTO Parcelas VALUES (4,'coordenadas 4',100);

INSERT INTO Parcelas VALUES (5,'coordenadas 5',100);
INSERT INTO Parcelas VALUES (6,'coordenadas 6',100);
INSERT INTO Parcelas VALUES (7,'coordenadas 7',100);




INSERT INTO Punto_Agua VALUES (1,1000,'PACoordenada1');

INSERT INTO Punto_Agua VALUES (2,2000,'PACoordenada2');


INSERT INTO Punto_Agua VALUES (3,3000,'PACoordenada3');

INSERT INTO Punto_Agua VALUES (4,4000,'PACoordenada4');


INSERT INTO Punto_Agua VALUES (5,1000,'PACoordenada5');

INSERT INTO Punto_Agua VALUES (6,2000,'PACoordenada6');


INSERT INTO Punto_Agua VALUES (7,3000,'PACoordenada7');

INSERT INTO Punto_Agua VALUES (8,4000,'PACoordenada8');


INSERT INTO Punto_Agua VALUES (9,3000,'PACoordenada9');

INSERT INTO Punto_Agua VALUES (10,4000,'PACoordenada10');

INSERT INTO Punto_Agua VALUES (11,8000,'PACoordenada11');

INSERT INTO Punto_Agua VALUES (12,9000,'PACoordenada12');




INSERT INTO Existe VALUES (1,1);

INSERT INTO Existe VALUES (2,1);



INSERT INTO Existe VALUES (3,2);

INSERT INTO Existe VALUES (4,2);




INSERT INTO Existe VALUES (5,3);

INSERT INTO Existe VALUES (6,3);




INSERT INTO Existe VALUES (7,4);

INSERT INTO Existe VALUES (8,4);




INSERT INTO Existe VALUES (9,5);

INSERT INTO Existe VALUES (10,5);



INSERT INTO Existe VALUES (11,6);

INSERT INTO Existe VALUES (12,7);





INSERT INTO Especies VALUES (1,'Alfalfa');
INSERT INTO Especies VALUES (2,'Trigo');
INSERT INTO Especies VALUES (3,'Cebada');
INSERT INTO Especies VALUES (4,'Avena');
INSERT INTO Especies VALUES (5,'Centeno');




INSERT INTO Cultivan VALUES (1,1,100);
INSERT INTO Cultivan VALUES (1,2,200);
INSERT INTO Cultivan VALUES (1,3,300);
INSERT INTO Cultivan VALUES (1,4,500);


INSERT INTO Cultivan VALUES (2,1, 200);
INSERT INTO Cultivan VALUES (2,2,100);
INSERT INTO Cultivan VALUES (2,3,50);
INSERT INTO Cultivan VALUES (2,4, 80);


INSERT INTO Cultivan VALUES (3,1, 100);
INSERT INTO Cultivan VALUES (3,2, 80);
INSERT INTO Cultivan VALUES (3,3, 800);
INSERT INTO Cultivan VALUES (3,4, 500);



INSERT INTO Cultivan VALUES (4,1, 200);
INSERT INTO Cultivan VALUES (4,2,300);
INSERT INTO Cultivan VALUES (4,3,400);
INSERT INTO Cultivan VALUES (4,4, 500);


INSERT INTO Cultivan VALUES (5,1, 100);
INSERT INTO Cultivan VALUES (5,2, 300);
INSERT INTO Cultivan VALUES (5,3, 300);
INSERT INTO Cultivan VALUES (5,4, 500);


INSERT INTO Cultivan VALUES (6,1, 100);
INSERT INTO Cultivan VALUES (6,2, 300);
INSERT INTO Cultivan VALUES (6,3, 300);
INSERT INTO Cultivan VALUES (6,4, 500);
INSERT INTO Cultivan VALUES (6,5, 500);

INSERT INTO Cultivan VALUES (7,1, 100);
INSERT INTO Cultivan VALUES (7,2, 300);
INSERT INTO Cultivan VALUES (7,3, 300);
INSERT INTO Cultivan VALUES (7,4, 500);
INSERT INTO Cultivan VALUES (7,5, 500);
