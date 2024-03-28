create database dbexerciciovacina;
use dbexerciciovacina;
select  * from pessoa;
select  * from vacina;
select * from vacinacao;
select * from pais;

create table Pessoa(
	idPessoa int not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) unique not null,
    dataNascimento date not null,
    sexo varchar(1) not null,
    categoria int not null comment '1- Pesquisador; 2- Voluntário; 3- Público Geral',
    id_pais int not null,
    --		--		--
	primary key(idPessoa)
);


create table Vacina(
	idVacina int not null auto_increment,
    nome varchar(255) not null,
    pais_origem  int not null,
    id_pesquisador int not null,
    dataInicioPesquisa date not null,
    estagio int not null ,
    mediaAvaliacao decimal,
    --		--		--
    primary key(idVacina),
    foreign key(id_pesquisador) references Pessoa(idPessoa)
);

create table Vacinacao(
	idVacinacao int not null auto_increment,
    id_pessoa int not null,
    id_vacina int not null,
    dataAplicacao date not null,
    avaliacao int comment 'Valor entre 1 e 5',
    --		--		--
    primary key(idVacinacao),
    foreign key(id_Pessoa) references Pessoa(idPessoa),
    foreign key(id_vacina) references Vacina(idVacina)
);

create table Pais(
	idPais int not null auto_increment,
    nome varchar(255) not null,
    sigla varchar(2) not null,
    --		--		--
    primary key(idPais)
);



-- ####################### RASCUNHO ###################### --

-- 	alter table Pessoa modify column cpf varchar(11) unique not null;
-- alter table Pessoa add column cpf varchar(11) unique not null;
-- alter table pessoa modify column categoria int not null;
-- insert into vacina(nome, pais_origem, id_pesquisador, dataInicioPesquisa, estagio) values('vac','brasil',1, '2020-01-02',2);
-- update pessoa set categoria = 1 where idPessoa = 1; update pessoa set categoria = 3 where idPessoa = 3;
-- alter table Pessoa add column id_pais int not null;
-- update pessoa set id_pais = 24 where idPessoa in (1,3,4);
-- alter table Pessoa add constraint fk_pais foreign key(id_pais) references Pais(idPais);
-- update vacina set pais_origem = 33 where idVacina = 1;
-- alter table Vacina drop column pais_origem;
-- alter table Vacina add column pais_origem int not null;
-- alter table Vacina add constraint fk_pais_origem foreign key(pais_origem) references Pais(idPais);
-- alter table Vacina add column mediaAvaliacao decimal;



