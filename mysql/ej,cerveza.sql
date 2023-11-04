CREATE SCHEMA cerveza;
use cerveza;
CREATE TABLE socio(
ID_socio INT UNIQUE NOT NULL,
nombre varchar(50) not null,
direccion varchar(100),
telefono varchar(15),
primary key (ID_socio)

);
CREATE TABLE bar(
licencia char(5),
nombre varchar(50) not null,
direccion varchar(100),
primary key (licencia)

);
CREATE TABLE fabricante(
ID_fabricante INT,
nombre varchar(50) not null,
telefono varchar(15) unique not null,
email varchar(100) unique,
primary key (ID_fabricante)

);
CREATE TABLE cerveza(
ID_cerveza INT not null,
nombre varchar(50) not null,
caracteristicas varchar(100),
ID_fabricante int,
primary key (ID_cerveza),
foreign key (ID_fabricante) references fabricante(ID_fabricante)
	on delete set null on update cascade

);
create table gusta(
ID_socio int,
ID_cerveza int,
primary key (ID_socio,ID_cerveza),
foreign key (ID_socio) references socio(ID_socio)
	on delete cascade on update cascade,
foreign key (ID_cerveza) references cerveza(ID_cerveza)
	on delete cascade on update cascade
    
);
create table vende(

licencia char(5) ,
ID_cerveza int,
precio float,
primary key (licencia,ID_cerveza),
foreign key (licencia) references bar(licencia)
	on delete cascade on update cascade,
foreign key (ID_cerveza) references cerveza(ID_cerveza)
	on delete cascade on update cascade
);
INSERT INTO fabricante VALUES (1, 'Mahou', '910112233', 'mahou@mahou.es');
INSERT INTO fabricante VALUES (2, 'Cruzcampo', '950332211', 'cp@cruzcampo.es');
INSERT INTO fabricante VALUES (3, 'Boostels', '+3252001122', 'boostels@boostels.be');
INSERT INTO fabricante VALUES (4, 'Duvel', '+3232221100', 'duvel@duvel.be');
INSERT INTO cerveza VALUES (1, 'Cinco Estrellas', 'Rubia', 1);
INSERT INTO cerveza VALUES (2, 'Maestra', 'Tostada', 1);
INSERT INTO cerveza VALUES (3, 'Mahou Sin', 'Sin alcohol', 1);
INSERT INTO cerveza VALUES (4, 'Especial', 'Otra rubia', 2);
INSERT INTO cerveza VALUES (5, 'Radler', 'Con limón', 2);
INSERT INTO cerveza VALUES (6, 'Cero cero', 'Sin alcohol', 2);
INSERT INTO cerveza VALUES (7, 'Tripel Karmeliet', 'Para masticar, pero rica', 3);
INSERT INTO cerveza VALUES (8, 'Kwak', 'Típica belga, algo fuerte', 3);
INSERT INTO cerveza VALUES (9, 'DeuS', '', 3);
INSERT INTO cerveza VALUES (10, 'Duvel', 'Nada más belga que Duvel', 4);
INSERT INTO cerveza VALUES (11, 'La Chouffe', '', 4);
INSERT INTO bar VALUES ('ES-a1', 'La Pirata de Malasaña', 'C/ Manuela Malasaña, 20,
Madrid');
INSERT INTO bar VALUES ('ES-b2', 'Taproom Madrid', 'C/ Guzmán el Bueno, 52, Madrid');
INSERT INTO bar VALUES ('BE-c3', 'Dulle Griet', 'Vrijdagmarkt 50, Gante');
INSERT INTO bar VALUES ('BE-d4', 'Delirium Café', 'Impasse de la Fidélité 4, Bruselas');
INSERT INTO socio VALUES (1, 'Lucía García', 'C/ Norte, 1', '600000099');
INSERT INTO socio (ID_socio, nombre, direccion) VALUES (2, 'Miguel Aragón', 'C/ Sur, 2');
INSERT INTO socio(ID_socio, telefono, nombre) VALUES (3, '600000077', 'Luisa Pérez');
INSERT INTO socio VALUES (4, 'Ángel Collado', 'C/ Oeste, 4', '+34600000066');
INSERT INTO gusta VALUES (1, 1);
INSERT INTO gusta VALUES (1, 3);
INSERT INTO gusta VALUES (1, 4);
INSERT INTO gusta VALUES (1, 5);
INSERT INTO gusta VALUES (1, 6);
INSERT INTO gusta VALUES (2, 1);
INSERT INTO gusta VALUES (2, 2);
INSERT INTO gusta VALUES (2, 4);
INSERT INTO gusta VALUES (2, 9);
INSERT INTO gusta VALUES (2, 11);
INSERT INTO gusta VALUES (3, 2);
INSERT INTO gusta VALUES (3, 3);
INSERT INTO gusta VALUES (3, 7);
INSERT INTO gusta VALUES (3, 8);
INSERT INTO gusta VALUES (3, 9);
INSERT INTO gusta VALUES (3, 10);
INSERT INTO gusta VALUES (4, 1);
INSERT INTO gusta VALUES (4, 2);
INSERT INTO gusta VALUES (4, 4);
INSERT INTO gusta VALUES (4, 5);
INSERT INTO gusta VALUES (4, 7);
INSERT INTO gusta VALUES (4, 8);
INSERT INTO gusta VALUES (4, 10);
INSERT INTO gusta VALUES (4, 11);
alter table fabricante add column fecha_nacimiento date;


insert into vende values ('ES-a1',1, 2.50);
insert into vende values ('ES-a1' ,2 ,3.00);
insert into vende values ('ES-a1' ,3, 2.50);
insert into vende values ('ES-a1' ,10 ,3.50);
insert into vende values ('ES-a1' ,11, 4.50);
insert into vende values ('ES-b2' ,4,2.00);
insert into vende values ('ES-b2' ,5, 2.00);
insert into vende values ('ES-b2' ,6 ,2.00);
insert into vende values ('ES-b2' ,1,null);
insert into vende values ('ES-b2' ,3,null);

INSERT INTO vende VALUES ('BE-c3', 7, 5.50);
INSERT INTO vende VALUES ('BE-c3', 8, 5.00);
INSERT INTO vende VALUES ('BE-c3', 9, 5.50);
INSERT INTO vende VALUES ('BE-c3', 10, 4.50);
INSERT INTO vende VALUES ('BE-d4', 2, 4.00);
INSERT INTO vende VALUES ('BE-d4', 7, 6.00);
INSERT INTO vende VALUES ('BE-d4', 8, null);
INSERT INTO vende VALUES ('BE-d4', 9, 5.50);
INSERT INTO vende VALUES ('BE-d4', 10, 4.00);
INSERT INTO vende VALUES ('BE-d4', 11, 5.00);

select licencia as bar, precio as precio_euros,precio*0.95 as precio_dolares,ID_cerveza as cerveza from vende;