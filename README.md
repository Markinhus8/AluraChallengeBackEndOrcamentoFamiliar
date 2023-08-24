# AluraChallengeBackEndOrcamentoFamiliar
Challenge Alura BackEnd Orcamento Familiar

Este é um projeto que fiz para o 4º Alura Backend Challenge . É uma API para gerenciamento de orçamento doméstico desenvolvida em Java usando o Spring Framework . Utilizei o banco de dados em memória H2.

O projeto possui funcionalidades CRUD para gerenciamento de receitas, despesas e possui resumo mensal.

Receitas - 
Suporta métodos GET, POST, PUT e DELETE para /receitas. Contém os seguintes campos:

"Id", (Auto generated)
"Data", (Format: dd/MM/yyyy)
"Descricao",
"Valor"

Despesas - 
Suporta métodos GET, POST, PUT e DELETE para /despesas. Contém os seguintes campos:

"Id", (//Auto generated)
"Data", //(Format: dd/MM/yyyy)
"Descricao", 
"Valor", 
"Categoria" // Enum(Alimentação, Saúde, Moradia, Transporte, Educação, Lazer, Imprevistos, Outras)

Resumo Mensal
Possui uma solicitação GET para /resumo/{year}/{month}
Ele retornará um JSON contendo o valor total das receitas, despesas, o saldo e as despesas totais por categoria.

Para execução das rotas foi executado o Postman.
