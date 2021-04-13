insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Japonesa'); 

insert into estado (nome) values ('SP');
insert into estado (nome) values ('PR');
insert into estado (nome) values ('MG');
insert into restaurante (nome,taxa_frete , cozinha_id) values ('Thai doidera', 12.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('Thailandia', 10.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('yukyuk', 1.1, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('lum', 0, 1);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('cef', 2.1, 2);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('leef', 0, 2);
insert into restaurante (nome,taxa_frete , cozinha_id) values ('maiori', 0, 2);

insert into cidade (nome, estado_id) values ('São Paulo' , 1);
insert into cidade (nome, estado_id) values ('São Roque' , 1);
insert into cidade (nome, estado_id) values ('Londrina' , 2);
insert into cidade (nome, estado_id) values ('Curitiba' , 2);
insert into cidade (nome, estado_id) values ('Uberaba' , 3);



insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
 

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);