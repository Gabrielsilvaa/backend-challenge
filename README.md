# java-itau

Este projeto é uma aplicação baseada em Java que se concentra na validação de JWT (JSON Web Token). Ele é construído usando Spring Boot e Maven, e fornece um conjunto de APIs RESTful para decodificar tokens JWT.

## Recursos

- **Decodificação JWT**: A aplicação fornece dois endpoints (`/api/decode` e `/api/decode/v2`) para decodificar tokens JWT. O primeiro endpoint aceita o token no corpo da solicitação, enquanto o segundo espera o token no cabeçalho `Authorization`, prefixado com `Bearer `.

- **Validação**: A aplicação valida os tokens JWT e lança uma `IllegalArgumentException` se o token for nulo ou se o token bearer não começar com `Bearer `.

- **Logging**: A aplicação usa SLF4J para logging. Ele registra uma mensagem de erro quando o token é nulo ou inválido.

- **Testes Unitários**: A aplicação inclui testes unitários para a classe `JwtController`, garantindo o comportamento correto da funcionalidade de decodificação JWT.

## Tecnologias Utilizadas

- **Java**: A aplicação é escrita em Java, uma linguagem de programação orientada a objetos popular.

- **Spring Boot**: Este projeto usa Spring Boot, um framework baseado em Java de código aberto usado para criar aplicações Spring autônomas de nível de produção.

- **Maven**: Maven é usado como a ferramenta de construção para este projeto.

- **JUnit e Mockito**: Estes são usados para testes unitários na aplicação.

- **java-jwt do Auth0**: Esta biblioteca é usada para decodificar tokens JWT.

- **Springdoc-openapi**: Esta dependência é usada para a documentação OpenAPI 3 das APIs REST.

- **Spring Boot Actuator**: Fornece recursos prontos para produção para monitorar e gerenciar a aplicação.

---

Para inicializar o projeto via linha de comando (CMD), você pode seguir os seguintes passos:

1. Navegue até o diretório do projeto usando o comando `cd`. Por exemplo:

ajustar o caminho do diretório conforme necessário para o seu ambiente.
```bash
cd C:\Users\g.silva.andrade\OneDrive - Accenture\Documents\git\java-itau
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

----
1. `JwtController`: Esta classe é um controlador REST que fornece dois endpoints para decodificar tokens JWT. O método `decodeJwtToken()` aceita o token no corpo da solicitação, enquanto o método `decodeJwtTokenv2()` espera o token no cabeçalho `Authorization`, prefixado com `Bearer `. Ambos os métodos lançam uma `IllegalArgumentException` se o token for nulo ou se o token bearer não começar com `Bearer `.

2. `JwtControllerTest`: Esta classe é responsável por realizar os testes unitários para a classe `JwtController`. Ela contém testes para verificar o comportamento correto dos métodos de decodificação de tokens JWT quando fornecidos com tokens válidos, nulos ou inválidos.

3. `UserDto`: Esta classe é um Data Transfer Object (DTO) que representa um usuário na aplicação. Ela contém campos para o papel do usuário (`role`), a semente (`seed`) e o nome do usuário (`name`). Cada campo tem anotações de validação para garantir que os dados sejam válidos. Por exemplo, o campo `name` deve ser uma string que contém apenas letras e não pode ser mais longa do que 256 caracteres.