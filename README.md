# Cadastro de usuario no banco de dado MySql
![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge)
![Hibernate](https://img.shields.io/badge/Hibernate-5.4.30.Final-green?style=for-the-badge)
![JPA](https://img.shields.io/badge/JPA-2.2-blue?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-8-orange?style=for-the-badge)

## 🎯Objetivo
O sistema tem como objetivo principal realizar o registro de usuários no banco de dados e gerenciar essas informações através das operações de CRUD.

O projeto foi desenvolvido com foco em:

- Persistência de dados
- Organização em camadas
- Padrão DAO
- Validações de entrada de dados
- Atualização parcial de registros

## ⚙️Logica do sistema
O sistema para mapear tabelas do Java no banco de dados é baseado no padrão de projeto da Java Persistence (JPA) (@Entity, @Id, etc.), implementado com a biblioteca Hibernate.

Para realizar o CRUD necessário para persistir os dados no banco, foi utilizado o padrão DAO (Data Access Object), para criar os processos de:
- CREATE
- READ
- UPDATE
- DELETE

Este processo é isolado das demais partes do sistema e implementado apenas na classe onde a logica de negocio sejá responsavel pela persistência dos dados.

## ✔️Validações
Uma validação importante implementada no sistema foi a possibilidade do usuário escolher quais dados deseja atualizar durante uma alteração de cadastro.
Essa abordagem permite que o usuario não seja obrigado a atualizar todos os dados, sempre que for realizar uma nova atualização especifica como em: 'Email', 'senha'...

Outras validações necessarias para a logica do sistema:
- Validar ação criticas, como o processo de exclusão de um cadastro
- Alertar o usuario quando houver uma entrada invalida
- Controle de operações via menu de opções

## 🛠️Tecnologias:
- JavaSE 21
- Hibernate 5.4.30
- JPA 2.2
- MySql 8
- JDBC
- IDE Eclipse
- DAO Pattern
- ORM (Object Relational Mapping)

## ⚠️ Compatibilidade de Versões
Caso deseje utilizar outra versão da Jakarta Persistence, verifique a compatibilidade com a biblioteca Hibernate, pois versões incompatíveis podem causar erros de execução no sistema.

Você pode entender melhor e obter informações sobre compatibilidade acessando este link:

https://hibernate.org/community/compatibility-policy/ 

https://hibernate.org/orm/releases/5.3/?utm_source=chatgpt.com


## Tabela de compatibilidade

| Hibernate | JPA               |
| --------- | ----------------- |
| 5.0       | JPA 2.1           |
| 5.2 – 5.6 | JPA 2.2           |
| 6.x       | JPA 3.x (Jakarta) |



## Autor
Edson Salles 
  

