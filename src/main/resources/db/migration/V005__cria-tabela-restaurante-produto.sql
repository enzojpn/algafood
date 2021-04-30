
create table restaurante_produto(
restaurante_id bigint not null,
produto_id bigint not null
)engine=InnoDB default charset=utf8mb4;

alter table restaurante_produto add constraint fk_restauratne_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table restaurante_produto add constraint fk_restaurante_produto_produto
foreign key (produto_id) references produto (id);