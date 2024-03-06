

# Back-end Java Spring Boot com Banco de Dados MongoDB

- Este é um guia completo para configurar e utilizar o back-end Java Spring Boot 
para realizar o upload de arquivos CNAB, validar sua estrutura e formatação, autorizar transações e cadastrar detalhes das transações em arquivos CNABs. O objetivo é integrar informações financeiras com precisão e eficiência, além de garantir um registro detalhado das transações autorizadas.
- O sistema é capaz de receber arquivos CNAB seguindo o formato posicional especificado e validar sua estrutura conforme o layout definido. Ele verifica se o arquivo é válido, não está vazio e não está corrompido antes de processá-lo. O sistema também é capaz de identificar os diferentes tipos de registros presentes no arquivo e registrar as transações no banco de dados. Além disso, ele valida as transações de acordo com as regras estabelecidas, autorizando as transações válidas e rejeitando as inválidas. Em caso de problemas, o sistema fornece mensagens de erro apropriadas. Através da API de upload, o sistema recebe e processa os arquivos CNAB, garantindo a integridade e a formatação correta. Os usuários podem consultar as transações autorizadas, e a interface de usuário intuitiva fornece feedback adequado durante todo o processo.

## Tecnologias Utilizadas

- Java versão 17
- Spring Boot
- Banco de dados de memória H2

## Estrutura do Arquivo CNAB

O arquivo CNAB possui uma estrutura posicional com os seguintes registros:

### Registro de Cabeçalho (001)
- Posições 1-3: Identificação do tipo de registro (001)
- Posições 4-33: Razão social da empresa
- Posições 34-47: Identificador da empresa
- Posições 48-80: Espaço reservado para uso futuro

### Registro de Transação (002)
- Posições 1-3: Identificação do tipo de registro (002)
- Posição 4: Tipo de transação (C para Crédito, D para Débito, T para Transferência)
- Posições 5-20: Valor da transação (formato decimal, sem ponto decimal)
- Posições 21-36: Conta origem
- Posições 37-52: Conta destino

### Registro de Rodapé (003)
- Posições 1-3: Identificação do tipo de registro (003)
- Posições 4-80: Espaço reservado para informações de totalização ou controle

## Tratamento de Erros

Durante o processamento do arquivo CNAB, podem ocorrer erros que devem ser tratados. Alguns exemplos de mensagens de erro são:

- Erro na linha X: Tipo de transação inválido.
- Erro na linha X: Valor da transação está fora do formato válido.
- Erro na linha X: Conta origem não está no formato correto.
- Erro na linha X: Conta destino não está no formato correto.
- Erro na linha X: Valor da transação não pode ser nulo.
- Erro na linha X: Conta origem é obrigatória.
- Erro na linha X: Conta destino é obrigatória.

## API - Upload de Arquivo CNAB

A API permite o upload de arquivos CNAB via método POST. A resposta da API pode ser:

### Sucesso (HTTP 200 OK)
```json
{
  "status": "success",
  "message": "Arquivo CNAB enviado e processado com sucesso.",
  "data": {
    "transactions": [
      {
        "type": "C",
        "value": 100.00,
        "accountOrigin": "123456",
        "accountDestination": "0012345"
      },
      {
        "type": "D",
        "value": 200.00,
        "accountOrigin": "123456",
        "accountDestination": "123456"
      }
    ]
  }
}
```

### Erro 1 - Dados Incorretos (HTTP 400 Bad Request)
```json

{
  "status": "error",
  "message": "O arquivo CNAB possui formato inválido.",
  "errors": [
    { "line": 5, "error": "Valor da transação está fora do formato válido." },
    { "line": 8, "error": "Tipo de transação inválido." }
  ]
}
```
### Erro 2 - Arquivo CNAB Inválido (HTTP 400 Bad Request)
```json

{
  "status": "error",
  "message": "Erro ao processar o arquivo CNAB. Certifique-se de que o arquivo esteja no formato posicional correto."
}
```
# Classes e Métodos
## Controller
### CnabFileController
 Método Java que lida com uma solicitação de upload de arquivo usando a anotação @PostMapping do Spring. Ele consome uma solicitação de dados de formulário multipart e retorna um ResponseEntity contendo um objeto CnabResponseDto após processar o arquivo carregado usando algum serviço e mapeador.

 | Endpoint          | Método | Descrição                           |
 |-------------------|--------|-------------------------------------|
 | /cnab-file/upload | POST   | Faz o upload de um arquivo CNAB     |

 #### Parâmetros da Solicitação

 | Parâmetro | Tipo                   | Descrição                              |
 |-----------|------------------------|----------------------------------------|
 | file      | MultipartFile (multipart) | O arquivo CNAB a ser enviado no upload |

## Service
### CnabFileService
####  cnabFilePosicional(final MultipartFile cnabFile)
O método **cnabFilePosicional** Java que recebe um arquivo CNAB posicional como entrada, processa seu conteúdo e retorna a classe CnabDat. Ele analisa o arquivo em registros de cabeçalho, transação e rodapé, e trata casos de erro lançando exceções.
## Mapper
### CnabMapper
 A interface CnabMapper e  um mapeador para converter objetos CnabDat em objetos CnabResponseDto. O método toDto recebe um objeto CnabDat e retorna um novo objeto CnabResponseDto com os mesmos dados.

### Configuração do Banco de Não Relacional
O uso do MongoDB com a anotação @Document(collection = "cnab") para a classe CnabDat é justificado pela flexibilidade na modelagem de dados, desempenho escalável, capacidade de lidar com estruturas de dados complexas e aninhadas, e suporte a consultas ágeis em dados CNAB. Esses fatores podem ser vantajosos para lidar com o formato CNAB e suas características específicas.

A configuração do banco MongoDb está definida no arquivo application.properties:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=cnab
```


### Como Executar a Aplicação
- Clone o repositório para sua máquina local.
Importe o projeto em sua IDE (por exemplo, IntelliJ ou Eclipse).
- Certifique-se de ter o Java JDK 17 instalado em sua máquina.
- Execute o aplicativo por meio da classe CnabFileApplication.
- Acesse a documentação da API em http://localhost:8080/swagger-ui.html.

## Collection Postman
- criar arquivo **C:/cnabFile.txt**
```file
001Empresa A              1900010000010000          Empresa A
002C10000000010000000001234560000012345
002D200000000200000000012345612345600
002T300000000300000000012345623456789
003

```
- **Collection**

```json
{
	"info": {
		"_postman_id": "35aa2dae-c6db-4efc-a38e-2c590113b757",
		"name": "CNAB",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "Send-FileCNAB",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "file",
							"type": "file",
							"src": "/C:/cnabFile.txt"
						}
					]
				},
				"url": {
					"raw": "http://localhost:8080/cnab-file/upload",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"cnab-file",
						"upload"
					]
				}
			},
			"response": []
		}
	]
}
```
## Testes Unitários
Testes Unitários com Mockito e JUnit 5
Descrição da Aplicação
A aplicação utiliza testes unitários para garantir a qualidade do código e verificar se as partes individuais do software estão funcionando corretamente.

### Benefícios dos Testes Unitários
- Detecção precoce de bugs.
- Melhoria da qualidade do código.
- Facilita a refatoração.
- Documentação viva do código.
### Mockito e JUnit 5
O Mockito é uma biblioteca de mocking amplamente utilizada em conjunto com o framework de testes JUnit 5. O Mockito permite criar objetos simulados (mocks) de dependências externas, permitindo que os testes sejam executados de forma isolada.

### Principais benefícios do uso do Mockito e JUnit 5:

- Isolamento de dependências.
- Controle total sobre o comportamento dos mocks.
- Facilidade de uso.

### Coleta de méticas
```file
# Habilitar os endpoints do Actuator
management.endpoints.web.exposure.include=prometheus
management.endpoint.metrics.enabled=true
management.endpoint.prometheus.enabled=true
```
Essas configurações habilitam endpoints específicos do Actuator para expor métricas e dados no formato Prometheus, facilitando o monitoramento e a coleta de métricas em sistemas distribuídos.

http://localhost:8080/actuator/prometheus

## Documentação
Para visualizar a documentação da API, acesse http://localhost:8080/swagger-ui/index.html após iniciar a aplicação.
# Contribuição
1. Faça um fork do repositório.
2. Crie uma nova branch com o nome descritivo da sua contribuição.
3. Realize as alterações e melhorias necessárias.
4. Faça um pull request com uma descrição clara das alterações realizadas.
5. Link gitHub: https://github.com/MarceloMacedo02/Crdc-desafio.git

## Contato

Em caso de dúvidas ou sugestões, sinta-se à vontade para entrar em contato através do e-mail marcelo_macedo01@hotmail.com.

Esperamos que este aplicativo seja útil para o gerenciamento eficiente dos associados e atividades financeiras do seu Centro Espírita. Obrigado por utilizar esta aplicação!