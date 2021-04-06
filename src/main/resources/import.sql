insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Japonesa'); 

insert into estado (nome) values ('SP');
insert into estado (nome) values ('PR');
insert into estado (nome) values ('MG');
insert into restaurante (nome,taxa_frete , cozinha_id) values ('Thai doidera', 12.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('Thailandia', 10.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('yukyuk', 1.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('lum', 11.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('cef', 2.1, 2);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('leef', 22.1, 2);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('maiori', 112.1, 2);

insert into cidade (nome, estado_id) values ('São Paulo' , 1);
insert into cidade (nome, estado_id) values ('São Roque' , 1);
insert into cidade (nome, estado_id) values ('Londrina' , 2);
insert into cidade (nome, estado_id) values ('Curitiba' , 2);
insert into cidade (nome, estado_id) values ('Uberaba' , 3);