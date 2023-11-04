use cerveza;
select socio.ID_socio,socio.nombre,gusta.ID_cerveza
from gusta,socio
where gusta.ID_socio=socio.ID_socio 

and socio.nombre like 'Lu%';

select licencia as bar, precio as precio_euros,precio*0.95 as precio_dolares,ID_cerveza as cerveza from vende;
select * from bar B,vende V,cerveza C
where B.licencia= V.licencia and C.ID_cerveza = V.ID_cerveza and C.nombre like 'Mahou%';
select count(*) as numero_bar
from bar;
select max(vende.precio)
from vende
where vende.ID_cerveza = '3';
select  vende.licencia,max(vende.precio),count(vende.ID_cerveza) as num_cerveza
from vende
group by vende.licencia
having count(*)>2
order by max(precio) desc;´º
select  cerveza.nombre,cerveza.ID_cerveza,vende.precio
from vende, cerveza
where vende.ID_cerveza = cerveza.ID_cerveza and precio > (select avg(precio) from vende);
