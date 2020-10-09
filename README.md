# Mundiale - Desafio Prático
ACERVO DE MÚSICAS

### Dependências
 - [Java 8 Development Kit](    https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
 - [MySQL (Already configured)](https://www.mysql.com/)

### Construído com
 - [Java](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
 - [Springboot](https://spring.io/projects/spring-boot)
 - [Spring Security](https://spring.io/projects/spring-security)
 - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
 - [Maven](https://maven.apache.org)
 - [Swagger](https://swagger.io)
 
 
### Iniciando
A aplicação pode ser inicializada usando o Maven no Windows.

> `mvn clean install`
>
> `mvn spring-boot:run`

Para obter um token você pode acessar diretamente o endpoint `http://localhost:5000/api/v1/authenticate` via método `POST` com um objeto `JSON` contendo username e password ou acessar `http://localhost:5000/swagger-ui.html` na aba `authentication`.

Criei uma credencial com perfil `ADMIN` para facilitar os testes.

 - **username**: _admin_ 
 - **password**: _1_

Observações: Não houve especificações sobre as regras para controle de acesso, então de forma autônoma adotei as seguintes:

Apenas usuários com perfil `ADMIN` podem:
 - Criar um novo usuário
 - obter todos os usuários
 - obter usuários através do `id`
 - obter usuários através do `username`

Usuários com perfis `ADMIN` ou `USER` têm acesso a todos os recursos.
 
 
### Links
 - Port - http://localhost:5000
 - Swagger - http://localhost:5000/swagger-ui.html
 
 ### Prints
Obtendo um Bearer token
![Obtendo um Bearer token](https://i.ibb.co/XLvbvgS/mundialechallenge-obtaining-token.png)

Recursos
![Apis](https://i.ibb.co/s15809p/mundialechallenge-resources.png)
