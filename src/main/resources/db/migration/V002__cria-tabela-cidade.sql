create table cidade (
	id bigint not null auto_increment,
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

insert into cidade (nome_cidade, nome_estado) values ('São Paulo'  , 'São Paulo');
insert into cidade (nome_cidade, nome_estado) values ('Santos'  , 'São Paulo');
insert into cidade (nome_cidade, nome_estado) values ('Atibaia'  , 'São Paulo');
insert into cidade (nome_cidade, nome_estado) values ('Londrina'  , 'Paraná');
insert into cidade (nome_cidade, nome_estado) values ('Curitiba'  , 'Paraná');
insert into cidade (nome_cidade, nome_estado) values ('Uberaba'  , 'Minas Gerais');
insert into cidade (nome_cidade, nome_estado) values ('Belo Horizonte'  , 'Minas Gerais');
insert into cidade (nome_cidade, nome_estado) values ('Rio de Janeiro'  , 'Rio de Janeiro');