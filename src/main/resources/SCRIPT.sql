create database dbexerciciovacina;
use dbexerciciovacina;


create table Pessoa(
	idPessoa int not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) unique not null,
    dataNascimento date not null,
    sexo varchar(1) not null,
    categoria varchar(50) not null,
    --		--		--
	primary key(idPessoa)
	
);


create table Vacina(
	idVacina int not null auto_increment,
    nome varchar(255) not null,
    pais_origem varchar(255) not null,
    id_pesquisador int not null,
    dataInicioPesquisa date not null,
    estagio int not null,
    --		--		--
    primary key(idVacina),
    foreign key(id_pesquisador) references Pessoa(idPessoa)
    
);


create table Aplicacao_Vacina(
	idAplicacao int not null auto_increment,
    id_pessoa int not null,
    id_vacina int not null,
    dataAplicacao date not null,
    avaliacao int not null,
    --		--		--
    primary key(idAplicacao),
    foreign key(id_Pessoa) references Pessoa(idPessoa),
    foreign key(id_vacina) references Vacina(idVacina)
    
);