# Resource Server API

## Visão Geral

Esta é uma API Spring Boot que atua como um **Resource Server** (Servidor de Recursos). O seu principal propósito é proteger e expor endpoints que só podem ser acessados mediante a apresentação de um token de acesso (JWT) válido.

A aplicação foi projetada para funcionar como um cliente do serviço de autenticação **SCAD**. O fluxo de operação é o seguinte:

1.  Um cliente (como uma aplicação web ou mobile) primeiro se autentica no serviço **SCAD**.
2.  O SCAD, após validar as credenciais, gera e retorna um token JWT para o cliente.
3.  O cliente, de posse do token, pode então fazer requisições para os endpoints protegidos desta API, incluindo o token no cabeçalho `Authorization` da requisição.
4.  Esta API (Resource Server) valida o token JWT recebido (verificando sua assinatura e o emissor configurado) antes de permitir o acesso ao recurso solicitado.

## Pré-requisitos

- Java 17 ou superior
- Maven
- Acesso ao serviço de autenticação SCAD em execução.

## Configuração

Antes de executar a aplicação, é necessário configurar a URI do emissor do token (o serviço SCAD). Isso é feito no arquivo `src/main/resources/application.properties`:

```properties
# Endereço do serviço SCAD que emite os tokens
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/api/v1

# Porta em que esta API irá rodar
server.port=8081
```

Certifique-se de que o valor de `spring.security.oauth2.resourceserver.jwt.issuer-uri` corresponde exatamente ao endereço do seu serviço SCAD.

## Como Executar

1.  Clone este repositório.
2.  Navegue até a pasta raiz do projeto.
3.  Execute o seguinte comando Maven para iniciar a aplicação:

```sh
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8081`.

## Endpoints da API

A API expõe dois endpoints para demonstrar o acesso público e o protegido.

### 1. Endpoint Público

Este endpoint não requer autenticação e pode ser acessado livremente.

- **URL**: `/public`
- **Método**: `GET`
- **Exemplo com cURL**:

```sh
curl http://localhost:8081/public
```

### 2. Endpoint Privado

Este endpoint é protegido e requer um token JWT válido gerado pelo serviço SCAD.

- **URL**: `/private`
- **Método**: `GET`
- **Exemplo com cURL**:

Substitua `<SEU_TOKEN_JWT>` pelo token obtido do serviço SCAD.

```sh
curl -H "Authorization: Bearer <SEU_TOKEN_JWT>" http://localhost:8081/private
```

Se o token for válido, a API retornará uma mensagem de sucesso. Caso contrário, retornará um erro `401 Unauthorized`.
