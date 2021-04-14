insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Japonesa'); 

insert into estado (nome) values ('SP');
insert into estado (nome) values ('PR');
insert into estado (nome) values ('MG');


insert into cidade (nome, estado_id) values ('São Paulo' , 1);
insert into cidade (nome, estado_id) values ('São Roque' , 1);
insert into cidade (nome, estado_id) values ('Londrina' , 2);
insert into cidade (nome, estado_id) values ('Curitiba' , 2);
insert into cidade (nome, estado_id) values ('Uberaba' , 3);


insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp,  'Parada inglesa', '1244230', '5423','rua raposo tavares', '412', 'Pizzaria tomas' , 12 , 1, 2);
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp,'Santana', '11221122', '4512','rua caninda', '124', 'Pizzaria canono' , 12 , 1 ,1);
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp,'Imirim', '123123', '133','rua raposo  joroge', '124', 'veg sas' , 22 , 1 ,1);
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp,'Parada inglesa', '333344412', '333','rua jorege belho', '42', 'midori' , 0 , 2 , 2);
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp,'Santana Parana', '21414124', '123','rua anacleto campane', '412', 'ceef' , 2 , 3 , 1);
insert into restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) values (utc_timestamp, utc_timestamp,'Vila ipirang', '12412414', '222','rua augusta', '3', 'tuk tuk' , 0 , 3 ,3);


insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
 

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);