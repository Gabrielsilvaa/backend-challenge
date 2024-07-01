# Índice do Projeto JWT Validator

---

## Índice

1. [Introdução](#introdução)
2. [Pré-requisitos](#pré-requisitos)
3. [Conhecendo as classes do projeto](#conhecendo-as-classes-do-projeto)
    1. [JwtController.java](#jwtcontrollerjava)
    2. [JwtServiceIpml.java](#jwtserviceipmljava)
    3. [TokenStructureValidationHandler.java](#tokenstructurevalidationhandlerjava)
    4. [TokenClaimValidationHandler.java](#tokenclaimvalidationhandlerjava)
    5. [UserAdapter.java](#useradapterjava)
    6. [UserDto.java](#userdtojava)
    7. [RoleEnum.java](#roleenumjava)
4. [Conhecendo as classes de teste](#conhecendo-as-classes-de-teste)
    1. [JwtControllerIntegrationTest.java](#jwtcontrollerintegrationtestjava)
    2. [RoleEnumTest](#roleenumtest)
    3. [TokenClaimValidationHandlerTest](#tokenclaimvalidationhandlertest)
    4. [TokenStructureValidationHandlerTest](#tokenstructurevalidationhandlertest)
    5. [JwtServiceImplTest](#jwtserviceimpltest)
    6. [TestUtil](#testutil)
5. [Conhecendo Manifest](#conhecendo-manifest)
6. [Conhecendo .github](#conhecendo-github)
7. [Demais pontos](#demais-pontos)
8. [Inicializar aplicação](#inicializar-aplicação)
9. [Build docker](#build-docker)

---

## Introdução

Este projeto é uma aplicação baseada em Java que se concentra na validação de JWT (JSON Web Token). Ele é construído usando Spring Boot e Maven, e fornece um conjunto de APIs RESTful para decodificar tokens JWT.

---

## Pré-requisitos

- **Java 21**
- **Maven 3.3.1**
- **IDE (IntelliJ IDEA, Eclipse, etc.)**
- **Postman ou qualquer outra ferramenta para testar APIs REST**
- **Docker**
- **Docker Compose**

---

## Conhecendo as classes do projeto

### JwtController.java

É um controlador REST na aplicação Spring Boot que lida com as solicitações HTTP relacionadas à decodificação de tokens JWT (JSON Web Tokens). Ela possui dois métodos principais:

- `decodeJwtToken(@RequestBody String token)`: Este método recebe um token JWT como corpo da solicitação HTTP e o valida usando o serviço JwtServiceIpml. Se o token for nulo, ele lança uma exceção IllegalArgumentException. Se o token for válido, ele retorna uma resposta HTTP com o status 200 (OK) e o resultado da validação.

- `decodeJwtTokenv2(@RequestHeader(AUTH) String bearerToken)`: Este método é semelhante ao anterior, mas recebe o token JWT do cabeçalho 'Authorization' da solicitação HTTP. Ele verifica se o cabeçalho começa com "Bearer ", que é um esquema comum para passar tokens JWT. Se o cabeçalho for nulo ou não começar com "Bearer ", ele lança uma exceção IllegalArgumentException. Ele então extrai o token do cabeçalho, valida o token e retorna uma resposta HTTP com o status 200 (OK) e o resultado da validação.

### JwtServiceIpml.java

É uma implementação do serviço JwtService que fornece um método para validar tokens JWT (JSON Web Tokens). Aqui está uma descrição detalhada do que cada parte da classe faz:

- `validateToken(String token)`: Este é o método que valida um token JWT. Ele recebe um token JWT como uma string e retorna um booleano indicando se o token é válido ou não.

### TokenStructureValidationHandler.java

É uma implementação da interface TokenValidationHandler que valida a estrutura de um token JWT (JSON Web Token). Aqui está uma descrição detalhada do que cada parte da classe faz:

- `handle(String token) throws RuntimeException`: Este é o método que inicia a validação do token. Ele decodifica o token, verifica se a estrutura do token é válida, decodifica o token em um UserDto, valida os campos do UserDto e, se houver um próximo manipulador na cadeia de responsabilidade, passa o token para ele.

- `isTokenStructureValid(String token)`: Este é um método que verifica se a estrutura de um token JWT é válida. Ele tenta decodificar o token e, se falhar, lança uma RuntimeException.

### TokenClaimValidationHandler.java

É uma implementação da interface TokenValidationHandler que valida as reivindicações (claims) de um token JWT (JSON Web Token). Aqui está uma descrição detalhada do que cada parte da classe faz:

- `handle(String token)`: Este é o método que inicia a validação do token. Se houver um próximo manipulador na cadeia de responsabilidade, passa o token para ele.

- `validateFields(UserDto userDto)`: Este é um método que valida os campos de um UserDto. Ele usa a API de Validação do Jakarta para validar os campos do UserDto. Se houver violações, ele lança uma IllegalArgumentException com uma mensagem de erro que contém todas as mensagens de violação.

- `isValidSeed(int number)`: Este é um método que valida o campo seed de um UserDto. Ele verifica se o número é maior que 1, se não é par e se não é divisível por qualquer número ímpar maior que 2. Se qualquer uma dessas verificações falhar, ele lança uma IllegalArgumentException com uma mensagem de erro apropriada.

### UserAdapter.java

É uma classe utilitária que fornece um método para decodificar um token JWT (JSON Web Token) em um UserDto. Aqui está uma descrição detalhada do que cada parte da classe faz:

- `decodeToken(DecodedJWT decodedJWT)`: Este é o método que decodifica um token JWT em um UserDto. Ele é public e static, o que significa que pode ser chamado em qualquer lugar do projeto sem a necessidade de criar uma instância da classe UserAdapter. O método recebe um DecodedJWT, que é um token JWT que já foi decodificado.

### UserDto.java

É um objeto de transferência de dados (DTO) que representa um usuário na aplicação. Aqui está uma descrição detalhada do que cada parte da classe faz:

- `private RoleEnum role;, private Integer seed;, private String name;`: Estes são os campos da classe. Eles representam o papel, a semente e o nome do usuário, respectivamente.

### RoleEnum.java

É uma enumeração em Java que representa os diferentes papéis que um usuário pode ter na aplicação. Aqui está uma descrição detalhada do que cada parte da classe faz:

- `getRole()`: Este é um método getter que retorna o valor do campo role. A anotação @JsonValue do Jackson indica que o valor retornado por este método deve ser usado quando a constante da enumeração é serializada para JSON.

- `fromString(String value)`: Este é um método estático que recebe uma string e retorna a constante da enumeração que corresponde a essa string. A anotação @JsonCreator do Jackson indica que este método deve ser usado para deserializar uma string para uma constante da enumeração.

---

## Conhecendo as classes de teste

### JwtControllerIntegrationTest.java
É uma classe de teste de integração para o controlador `JwtController`. Ela possui vários métodos de teste:

- **`whenValidTokenIsProvided_thenReturnsStatusOkv2()`**: Este método testa o cenário em que um token JWT válido é fornecido para o método `decodeJwtTokenv2`. Ele espera que o status da resposta HTTP seja 200 (OK).
- **`whenInvalidTokenIsProvided_thenReturnsStatusBadRequestv2()`**: Este método testa o cenário em que um token JWT inválido é fornecido para o método `decodeJwtTokenv2`. Ele espera que o status da resposta HTTP seja 400 (Bad Request).
- **`whenValidTokenIsProvided_thenReturnsStatusOkv1()`**: Este método testa o cenário em que um token JWT válido é fornecido para o método `decodeJwtToken`. Ele espera que o status da resposta HTTP seja 200 (OK).
- **`whenInvalidTokenIsProvided_thenReturnsStatusBadRequestv1()`**: Este método testa o cenário em que um token JWT inválido é fornecido para o método `decodeJwtToken`. Ele espera que o status da resposta HTTP seja 400 (Bad Request).

### RoleEnumTest
É uma classe de teste unitário que verifica o comportamento correto da enumeração `RoleEnum`. Ela usa a biblioteca JUnit 5 para definir e executar os testes:

- **`testFromStringWithValidRole()`**: Este método testa o cenário em que um valor válido de string `"Admin"` é fornecido para o método `fromString` da enumeração `RoleEnum`. Ele espera que o método `fromString` retorne o valor correspondente da enumeração `RoleEnum.ADMIN`.
- **`testFromStringWithValidRoleExternal()`**: Este método testa o cenário em que um valor válido de string `"External"` é fornecido para o método `fromString` da enumeração `RoleEnum`. Ele espera que o método `fromString` retorne o valor correspondente da enumeração `RoleEnum.EXTERNAL`.
- **`testFromStringWithValidRoleMember()`**: Este método testa o cenário em que um valor válido de string `"Member"` é fornecido para o método `fromString` da enumeração `RoleEnum`. Ele espera que o método `fromString` retorne o valor correspondente da enumeração `RoleEnum.MEMBER`.
- **`testFromStringWithInvalidRole()`**: Este método testa o cenário em que um valor invál