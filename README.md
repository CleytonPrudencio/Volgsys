# ViaCep
Criado um novo servico para estudo. Integracoes de microservicos utilizando API`S publicas. 

Faca o Download do repositorio develop e realiza os ajustes que acha necessario. 

- Java 18
- SpringBoot
- Maven
- Query banco users: CREATE TABLE public.users (
  id int NOT NULL,
  name varchar(500) NULL,
  cpf float8 NOT NULL,
  dtnascimento date NOT NULL,
  endereco varchar(200) NULL,
  cep varchar(9) NOT NULL,
  CONSTRAINT users_pk PRIMARY KEY (id)
  );
- Mudanca query: ALTER TABLE public.users ALTER COLUMN cpf TYPE varchar(11) USING cpf::varchar(11);
- Adicionando dois campos novos: ALTER TABLE public.users ADD cnpj varchar(14) NULL;
  ALTER TABLE public.users ADD email varchar(100) NULL;


