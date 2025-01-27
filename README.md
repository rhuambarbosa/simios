# simios
Em um futuro distante, na cadeia de evolução, os símios e os humanos estão cada vez mais próximos. Por esse motivo ficou muito difícil distinguir quem é humano e quem é símio.
Você é um cientista contratado para desenvolver um projeto que detecta se uma sequência de DNA pertence a um humano ou a um símio

## Features
- Válida e armazena a sequência de DNA .
- Retorna estatísticas de verificações de DNA
## Tecnologias/Frameworks usados
Construido com:
- [Spring](https://spring.io/)
- [Java 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [Docker](https://docs.docker.com/install/)
- [Flyway](https://flywaydb.org/)
- [Postgresql](https://www.postgresql.org/)
- ...
## Iniciando
Clone o repositório
```shell
git clone https://github.com/rhuambarbosa/simios.git
```
Subindo o banco de dados
```shell
docker run -d --restart=always --name simiosdb -e POSTGRES_PASSWORD=simiosdb%#r2 -p 5433:5432 postgres:9.3
```
### Building
Local
```shell
mvn clean package
```
## Deploying
Local: Dentro da pasta raiz do projeto onde exite o maven
```shell
mvn spring-boot:run
```
## Tests
```shell
{
 "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA"]
 }
```
## Funcionalidades API
```shell
| Verbo | Funcionalidade               | Descrição
| ------|------------------------------|---------------------------------------
| Post  | http://localhost:8080/simian | Validação da cadeia de DNA de é um Símion. {"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}
| Get   | localhost:8080/stats         | Retorna de estatisticas de cadeias validadas

```
