# Projeto JWT Validator

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
- **`testFromStringWithInvalidRole()`**: Este método testa o cenário em que um valor inválido de string `"InvalidRole"` é fornecido para o método `fromString` da enumeração `RoleEnum`. Ele espera que o método `fromString` lance uma exceção `IllegalArgumentException` com uma mensagem específica.

### TokenClaimValidationHandlerTest
É uma classe de teste unitário que verifica o comportamento correto do manipulador de validação de reivindicações de token `TokenClaimValidationHandler`. Ela usa a biblioteca JUnit 5 para definir e executar os testes:

- **`handleShouldPassTokenToNextHandlerWhenGivenValidToken()`**: Este método testa o cenário em que um token válido é fornecido para o método `handle` do `TokenClaimValidationHandler`. Ele espera que o método `handle` passe o token para o próximo manipulador na cadeia de responsabilidade.
- **`validateFieldsShouldThrowIllegalArgumentExceptionWhenGivenUserDtoWithInvalidFields()`**: Este método testa o cenário em que um `UserDto` com campos inválidos é fornecido para o método `validateFields` do `TokenClaimValidationHandler`. Ele espera que o método `validateFields` lance uma exceção `IllegalArgumentException`.
- **`validateFieldsShouldNotThrowAnyExceptionWhenGivenUserDtoWithValidFields()`**: Este método testa o cenário em que um `UserDto` com campos válidos é fornecido para o método `validateFields` do `TokenClaimValidationHandler`. Ele espera que o método `validateFields` não lance nenhuma exceção.
- **`isValidSeedShouldThrowIllegalArgumentExceptionWhenGivenInvalidNumber()`**: Este método testa o cenário em que um número inválido é fornecido para o método `isValidSeed` do `TokenClaimValidationHandler`. Ele espera que o método `isValidSeed` lance uma exceção `IllegalArgumentException`.
- **`isValidSeedShouldNotThrowAnyExceptionWhenGivenValidNumber()`**: Este método testa o cenário em que um número válido é fornecido para o método `isValidSeed` do `TokenClaimValidationHandler`. Ele espera que o método `isValidSeed` não lance nenhuma exceção.

### TokenStructureValidationHandlerTest
É uma classe de teste unitário que verifica o comportamento correto do manipulador de validação de estrutura de token `TokenStructureValidationHandler`. Ela usa a biblioteca JUnit 5 para definir e executar os testes:

- **`handleShouldThrowRuntimeExceptionWhenGivenTokenWithInvalidStructure()`**: Este método testa o cenário em que um token com estrutura inválida é fornecido para o método `handle` do `TokenStructureValidationHandler`. Ele espera que o método `handle` lance uma exceção `RuntimeException`.
- **`handleShouldNotThrowRuntimeExceptionWhenGivenTokenWithValidObjectStructure()`**: Este método testa o cenário em que um token com estrutura de objeto válida é fornecido para o método `handle` do `TokenStructureValidationHandler`. Ele espera que o método `handle` não lance nenhuma exceção.
- **`handleShouldNotThrowRuntimeExceptionWhenGivenTokenWithValidStructure()`**: Este método testa o cenário em que um token com estrutura válida é fornecido para o método `handle` do `TokenStructureValidationHandler`. Ele espera que o método `handle` não lance nenhuma exceção.
- **`handleShouldPassTokenToNextHandlerWhenGivenValidToken()`**: Este método testa o cenário em que um token válido é fornecido para o método `handle` do `TokenStructureValidationHandler`. Ele espera que o método `handle` passe o token para o próximo manipulador na cadeia de responsabilidade.
- **`handleShouldNotCallNextHandlerWhenGivenInvalidToken()`**: Este método testa o cenário em que um token inválido é fornecido para o método `handle` do `TokenStructureValidationHandler`. Ele espera que o método `handle` não chame o próximo manipulador na cadeia de responsabilidade.

### JwtServiceImplTest
É uma classe de teste unitário que verifica o comportamento correto da implementação do serviço JWT `JwtServiceImpl`. Ela usa a biblioteca JUnit 5 para definir e executar os testes:

- **`validateTokenShouldReturnTrueWhenGivenValidToken()`**: Este método testa o cenário em que um token válido é fornecido para o método `validateToken` do `JwtServiceImpl`. Ele espera que o método `validateToken` retorne true.
- **`validateTokenShouldReturnFalseWhenGivenInvalidToken()`**: Este método testa o cenário em que um token inválido é fornecido para o método `validateToken` do `JwtServiceImpl`. Ele espera que o método `validateToken` retorne false.
- **`validateTokenShouldReturnFalseWhenGivenNullToken()`**: Este método testa o cenário em que um token nulo é fornecido para o método `validateToken` do `JwtServiceImpl`. Ele espera que o método `validateToken` retorne false.
- **`validateTokenShouldReturnFalseWhenGivenEmptyToken()`**: Este método testa o cenário em que um token vazio é fornecido para o método `validateToken` do `JwtServiceImpl`. Ele espera que o método `validateToken` retorne false.

### TestUtil
É uma classe utilitária usada para gerar dados de teste para os testes unitários. Ela usa a biblioteca JavaFaker para gerar dados falsos e a classe `Random` para gerar números aleatórios:

- **`generateRandomPrime(int min, int max)`**: Este método gera um número primo aleatório entre `min` e `max`. Ele faz isso gerando números aleatórios até que encontre um que seja primo.
- **`generateRandomNumber(int min, int max)`**: Este método gera um número aleatório entre `min` e `max`.
- **`isPrime(int num)`**: Este método verifica se um número é primo. Ele faz isso verificando se o número é divisível por qualquer número de 2 até a raiz quadrada do número. Se o número é divisível por qualquer um desses números, então não é primo.
- **`generateRandomRole()`**: Este método gera um valor aleatório da enumeração `RoleEnum`. Ele faz isso gerando um índice aleatório e usando esse índice para obter um valor da enumeração `RoleEnum`.
- **`generateRandomName()`**: Este método gera um nome completo aleatório que não contém números. Ele faz isso gerando nomes completos até que encontre um que não contenha números.
- **`containsNumber(String input)`**: Este método verifica se uma string contém um número. Ele faz isso usando uma expressão regular que corresponde a qualquer string que contenha um dígito.

---


## Conhecendo Manifest

No manifest do projeto contemos todos nossos configuraçoes kubernert, nela definimos os deploys e services 

---

## Conhecendo .github

No .github do projeto temos nossa esteira de CI/cd, nela temos os workflows que são responsaveis por rodar os testes e fazer o deploy da aplicação

---

## Demais pontos 

- Na raiz do projeto tambem se encontra nosso docker file e docker-compose onde realizamos a criação de imagem e container
- Tambem possuimos dois arquivos para importação no insomina ou plataformas similares, onde conseguimos testar os endpoints do projeto 
- Para conseguir ver questoes de metricas sera necessario executar o docker composer 
- No prometheus.yml temos as configuraçoes para monitoramento da aplicação

---
## Incializar aplicação

Para inicializar o projeto via linha de comando (CMD), você pode seguir os seguintes passos:

1. Navegue até o diretório do projeto usando o comando `cd`. Por exemplo:

ajustar o caminho do diretório conforme necessário para o seu ambiente.
```bash
cd seu_diretorio
```

2. Uma vez no diretório do projeto, você pode usar o Maven para compilar e executar o projeto. O comando a seguir irá compilar o projeto e empacotá-lo em um arquivo JAR:

```bash
mvn clean package
```

3. Após a compilação bem-sucedida, você pode executar o projeto usando o comando `java -jar` seguido pelo caminho do arquivo JAR gerado. Por exemplo:

```bash
java -jar target/backend-challenge-0.0.1-SNAPSHOT.jar
```

Para inicializar o projeto via IDE (IntelliJ IDEA), você pode seguir os seguintes passos:

1. Abra o IntelliJ IDEA e clique em `File > Open...`.
2. Navegue até o diretório do projeto e clique em `Open`.
3. Uma vez que o projeto é aberto, você pode executá-lo clicando com o botão direito do mouse no arquivo `BackendChallengeApplication.java` e selecionando `Run 'BackendChallengeApplication.main()'`.

O projeto está usando a versão 3.3.1 do Maven e a versão 21 do Java, conforme especificado no arquivo `pom.xml`.

---

## Build docker 

Para executar o Docker Compose, você precisa ter o Docker e o Docker Compose instalados em seu sistema. Se você já os instalou, pode seguir as etapas abaixo:

1. Abra o terminal (ou linha de comando) e navegue até o diretório do projeto onde o arquivo `docker-compose.yml` está localizado. Você pode fazer isso usando o comando `cd`. Por exemplo:

```bash
cd seu_diretorio
```

2. Uma vez no diretório correto, você pode iniciar todos os serviços definidos no arquivo `docker-compose.yml` com o seguinte comando:

```bash
docker-compose up
```

Este comando irá baixar as imagens necessárias (se ainda não tiverem sido baixadas), criar os contêineres e iniciar todos os serviços definidos no arquivo `docker-compose.yml`.

Se você quiser executar os serviços em segundo plano (ou seja, sem ocupar o terminal), pode usar a opção `-d`:

```bash
docker-compose up -d
```

Para parar todos os serviços, você pode usar o comando `down`:

```bash
docker-compose down
```

Este comando irá parar e remover os contêineres, redes e, opcionalmente, volumes e imagens definidos no arquivo `docker-compose.yml`.

Lembre-se de substituir `seu_diretorio` pelo caminho do diretório onde o arquivo `docker-compose.yml` está localizado.