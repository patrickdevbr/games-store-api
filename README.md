# Games Store API

## Sobre o Projeto
API com cadastro de jogos e processamento de pedidos de um ecommerce de games.

## Modelo de domínio
![Modelo de domínio](https://github.com/Patrick-Ribeiro/games-store-API/blob/master/assets/Domain%20Model.JPG)
## Domínio de instância
![Modelo de domínio](https://github.com/Patrick-Ribeiro/games-store-API/blob/master/assets/Domain%20Instance.JPG)
## Camadas lógicas
![Camadas lógicas](https://github.com/Patrick-Ribeiro/games-store-API/blob/master/assets/logical-layersJPG.JPG)

## Como executar os testes
O projeto usa o maven wrapper (mvnw).
Para executar os testes de exemplo basta o comando abaixo:
```sh
./mvnw clean test
```
## Como gerar o build da aplicação
Para gerar o build do projeto, basta o comando abaixo:
```sh
mvn clean install
```

## Como executar a aplicação
Para executar a aplicação, após gerar o build, o maven gerará um fat jar na pasta target, sendo assim, basta executar o jar com os seguintes comandos:
```sh
cd target/
java -jar games-store-0.0.1-SNAPSHOT
```

## Tecnologias usadas
### Linguagem
- Java 11.0.11.
### Frameworks e bibliotecas
- SpringBoot 2.5.1;
- Maven 3.8.1.
### Banco de dados
- H2 database;
- PostgresSQL 13.2.

## Endpoints REST
### GET buscar produto 🟢
Busca as informações de um produto na base de dados de acordo com o id informado na URL do endpoint.
#### URL do recurso
[/products/{id}](https://supera-games-store.herokuapp.com/products/1)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 200 Ok

### GET buscar lista de produtos 🟢
Busca por uma lista paginada de produtos.
#### URL do recurso
[/products](https://supera-games-store.herokuapp.com/products)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 200 Ok

#### Parâmetros (URL)
Nome | Necessário | Opções | Valor Padrão | Descrição
--- | --- | --- | --- | ---
sortby | não | id; name; price; score; image | name | Critério de ordenação dos produtos de acordo com a opção informada.
descending | não | true; false | false | Tipo de ordenação dos produtos. true para ordenação decrescente e false para ordenação crescente.
page | não | 0-&infin; | 0 | Número da página dos produtos.
limit | não | 0-&infin; | 10 | Limite de quantidade de produtos por página.

### POST novo produto 🟡
Insere um novo produto na base de dados.
#### URL do recurso
[/products](https://supera-games-store.herokuapp.com/products)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 201 Created
#### Parâmetros (body)
Nome | necessário | Tipo | Descrição
--- | --- | --- | ---
name | sim | string | Nome do produto a ser inserido.
price | sim | number | Preço do produto a ser inserido.
score | sim | number | Nível de popularidade do produto a ser inserido.
image | sim | string | Nome do arquivo de imagem do produto a ser inserido.

### PUT atualizar produto 🔵
Atualiza as informações de um produto de acordo com o id informado na URL.
#### URL do recurso
[/products/{id}](https://supera-games-store.herokuapp.com/products/1)
#### Informações do recurso
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 200 Ok

#### Parâmetros (body)
Nome | Necessário | Tipo | Descrição
--- | --- | --- | ---
name | sim | string | Nome atualizado do produto.
price | sim | number | Preço atualizado do produto.
score | sim | number | Nível de popularidade atualizado do produto.
image | sim | string | Nome do arquivo de imagem atualizado do produto.

### DELETE deletar produto 🔴
Deleta permanentemente um produto de acordo com o id informado na URL.
#### URL do recurso
[/products/{id}](https://supera-games-store.herokuapp.com/products/1)
#### Informações do recurso
|   |   |
|---|---|
__Código HTTP esperado__ | 204 No Content

### GET buscar pedido 🟢
Busca as informações de um pedido na base de dados de acordo com o id informado na URL.
#### URL do recurso
[/orders/{id}](https://supera-games-store.herokuapp.com/orders/1)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 200 Ok

### GET buscar lista de pedidos 🟢
Busca por uma lista paginada de pedidos.
#### URL do recurso
[/orders](https://supera-games-store.herokuapp.com/orders)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 200 Ok

#### Parâmetros (URL)
Nome | Necessário | Opções | Valor Padrão | Descrição
--- | --- | --- | --- | ---
page | não | 0-&infin; | 0 | Número da página dos produtos.
limit | não | 0-&infin; | 10 | Limite de quantidade de produtos por página.

### POST novo pedido 🟡
Insere um novo pedido na base de dados.
#### URL do recurso
[/orders](https://supera-games-store.herokuapp.com/products)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 201 Created
#### Parâmetros (body)
Nome | necessário | Tipo | Descrição
--- | --- | --- | ---
 [] | sim | orderItem[] | Lista de itens presentes no pedido a ser inserido.
 
### POST adicionar novo item no pedido 🟡
Adiciona um novo item em um pedido. Caso já exista um item com o mesmo produto e mesmo preço, este item terá a quantidade atualizada com a soma da quantidade anterior com a nova quantidade informada.
#### URL do recurso
[/orders/{id}/items](https://supera-games-store.herokuapp.com/orders/1/items)
|   |   |
|---|---|
__Formato de resposta__ | JSON
__Código HTTP esperado__ | 200 Ok
#### Parâmetros (body)
Nome | necessário | Tipo | Descrição
--- | --- | --- | ---
 [] | sim | orderItem[] | Lista de itens a serem adicionados no pedido.

### DELETE remover item de pedido 🔴
Deleta permanentemente um item de pedido de acordo com o id do pedido informado na URL.
#### URL do recurso
[/orders/{id}/items](https://supera-games-store.herokuapp.com/orders/1/items)
#### Informações do recurso
|   |   |
|---|---|
__Código HTTP esperado__ | 204 No Content

#### Parâmetros (body)
Nome | necessário | Tipo | Descrição
--- | --- | --- | ---
productId | sim | number | Id do produto do item de pedido.
price | não | number | Preço do item de pedido.

#### OrderItem
Nome | necessário | Tipo | Descrição
--- | --- | --- | ---
productId | sim | number | Id do produto do item de pedido.
quantity | sim | number | Quantidade do produto no pedido.
price | não | number | Preço aplicado para o item no pedido. Caso não seja informado, o preço será aplicado a partir do preço do produto cadastrado.
