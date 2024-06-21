# Trabalho Final LPxPOO


## 🗃️ PROJETO  

O **Trabalho Final** desenvolvido foi baseado no tema de uma Biblioteca Online, com as funcionalidades:
- Login de usuário;
- Cadastro de usuário;
- Listagem de livros pré-cadastrados;
- Adição de livro escolhido ao carrinho;
- Acréscimo sobre a quantidade do livro no carrinho;
- Escolha da forma de pagamento e finalização do pedido;


## 🔧 TECNOLOGIAS 
- **IDE:** Código implementado com IntelliJ IDEA.
- **Java (versão 15):** Linguagem de programação que permite desenvolvimento Back-End e Front-End.
- **JDK (21.0.2):** Compilador e bibliotecas que permitem criar sistemas de software para Java.
- **SQLite (3.42.0.0):** Biblioteca que implementa uma base de dados SQL.


## 📐 ARQUITETURA
O sistema foi construído utilizando uma arquitetura conforme estrutura abaixo:
- _src_
  - _bd_
    - _bd.sqlite_ (armazena as tabelas de banco de dados construídas para o projeto)
  - _driverSQL_ (driver responsável por ligar o projeto ao banco de dados SQLite)
  - _main_
    - _java_ (contém toda as classes do projeto)


## 🖥️ NAVEGAÇÃO
- **Tela de Login**
  - Permite ao usuário já cadastrado realizar o login com usuário e senha;
  - Caso seja o primeiro acesso, é possível realizar cadastro na mesma tela.
- **Tela de Listagem**
  - Exibe os livros disponíveis para compra juntamente com suas informações;
  - Permite adicionar os livros desejados ao carrinho;
  - Permite ir a tela de carrinho.
- **Tela do Carrinho**
  - Exibe os livros adicionados ao carrinho e sua respectiva quantidade;
  - Exibe valor total do carrinho;
  - Permite ir a tela do pagamento.
- **Tela do Pagamento**
  - Exibe o valor total da compra;
  - Exibe formas de realizar o pagamento;
  - Finalizar compra.
 

## 🎞️ DEMONSTRAÇÃO
<img height="400" alt="GIF" src="https://github.com/Dianaaaf/Trabalho-Final-LPxPOO/blob/main/image/FuncionamentoProjeto.gif?raw=true"/>&nbsp;


## ⌨️ DESENVOLVIMENTO  
Diana Alves ([Linkedin](https://www.linkedin.com/in/diana-alves-6a99271b5/) | [GitHub](https://github.com/Dianaaaf)) 
