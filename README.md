## 📖 Descrição do Projeto

API REST para controle de orçamento. Projeto desenvolvido para o **Alura Challenge - Back-End 4ª edição**

API REST para cadastro de receitas, despesas e geração de relatórios mensais com total e saldo.

## 🔨 Funcionalidades

✔️ Adicionar, consultar, atualizar e deletar receitas.

✔️ Adicionar, consultar, atualizar e deletar despesas.

✔️ Resumo mensal com o total de despesas, receitas, saldo mensal e o total gasto por categoria.

✔️ Autenticação de usuário com Token JWT.

✔️ Uso da biblioteca SpringDoc no projeto para que faça a geração automatizada da documentação da API e uso do Swagger UI para visualizar e testar a API Rest.

✔️ Uso do Jacoco para fazer a cobertura de testes na aplicação.



## 🧰 Tecnologias utilizadas
* Java
* Spring Framework
* Maven
* JPA
* Banco de dados em memória H2
* Token JWT
* SpringDoc e Swagger UI
* Eclipse IDE
* Postman
* Trello
* Jacoco

* ## Rotas

### Receitas
| Rota | Método | Descrição | BODY PARAMS | QUERY PARAMS |
| --- | --- | --- | --- | --- |
| /receitas | POST | Cadastra uma receita | <pre> {<br> "descricao": "Salario",<br> "valor": 8.000,<br> "data": "01/01/2023"<br>} </pre> |  |
| /receitas | GET | Retorna todas as receitas |  | descricao (opcional) |
| /receitas/{ano}/{mes} | GET | Retorna todas as receitas do mês |  |  |
| /receitas/{id} | GET | Retorna receita por id |  |  |
| /receitas/{id} | PUT | Atualiza receita por id | <pre> {<br> "descricao": "Salario",<br> "valor": 8.000,<br> "data": "01/01/2023"<br>} </pre> |  |
| /receitas/{id} | DELETE | Remove receita por id |  |  |

### Despesas
| Rota | Método | Descrição | BODY PARAMS | QUERY PARAMS |
| --- | --- | --- | --- | --- |
| /despesas | POST | Cadastra uma despesa |  <pre> {<br> "descricao": "Condominio",<br> "valor": 100.00,<br> "data": "05/01/2023",<br> "categoria": "MORADIA"<br>} </pre> O campo categoria é opcional |  |
| /despesas | GET | Retorna todas as despesas |  | descricao (opcional) |
| /despesas/{ano}/{mes} | GET | Retorna todas as despesas do mês |  |  |
| /despesas/{id} | GET | Retorna despesa por id |  |  |
| /despesas/{id} | PUT | Atualiza despesa por id |  <pre> {<br> "descricao": "Condominio",<br> "valor": 100.00,<br> "data": "05/01/2023",<br> "categoria": "MORADIA"<br>} </pre> O campo categoria é opcional  |  |
| /despesas/{id} | DELETE | Remove despesa por id |  |  |

### Resumo
| Rota | Método | Descrição | BODY PARAMS | QUERY PARAMS |
| --- | --- | --- | --- | --- |
| /resumo/{ano}/{mes} | GET | Retorna resumo do mês |  |  |

### Categorias disponíveis
| Nome |
| --- |
| Alimentação |
| Saúde |
| Moradia |
| Transporte |
| Educação |
| Lazer |
| Imprevistos |
| Outras |

