# Agenda de Contatos

Sistema de agenda de contatos desenvolvido em Java, apresentando uma interface gráfica (Desktop) moderna e funcional, com foco em boas práticas de programação e separação de responsabilidades.

---

## Objetivo

Este projeto evoluiu de uma aplicação via terminal para praticar:

- **Interface Gráfica (Swing):** Criação de janelas, tabelas e gerenciamento de layouts.
- **Arquitetura MVC Simples:** Separação entre a visualização (GUI), lógica de negócio (Cadastro) e persistência de dados.
- **Event Handling:** Manipulação de eventos de botões e escuta de documentos em tempo real.
- **Tratamento de Erros e Validações:** Garantia de integridade dos dados (impede duplicatas e formatos inválidos).
- **Persistência em Arquivo:** Salvamento automático em arquivo de texto.

---

## Funcionalidades

- **Interface Visual:** Gerenciamento completo através de uma janela nativa do sistema operacional.
- **Pesquisa em Tempo Real:** Filtro inteligente na tabela que busca por nome ou telefone enquanto você digita.
- **Validação de Dados:** 
    - O sistema impede o cadastro de nomes ou telefones já existentes.
    - Validação rigorosa do campo telefone (aceita apenas números).
- **CRUD Completo:** Adicionar, listar, atualizar e remover contatos de forma intuitiva.
- **Auto-preenchimento:** Ao selecionar um contato na tabela, os dados são carregados automaticamente para edição.
- **Persistência:** Todos os dados são salvos em `contatos.txt`.

---

## Tecnologias Utilizadas

- Java
- Java Swing (Interface Gráfica)
- File I/O (Persistência de dados)
- Git
- GitHub

---

## Estrutura do Projeto

```text
src/
├── Main.java          # Ponto de entrada que inicia a interface gráfica.
├── AgendaGUI.java     # Camada de visualização e interação com o usuário.
├── Cadastro.java      # Camada de serviço/lógica (validações e regras de negócio).
├── Contato.java       # Modelo de dados (POJO).
├── Persistencia.java  # Gerenciamento de leitura e escrita de arquivos.
└── utils/             # Utilitários legados do sistema via terminal.
```

---

## Como Executar

1. Certifique-se de ter o **JDK 8** ou superior instalado.
2. Clone o repositório.
3. Compile os arquivos `.java` dentro da pasta `src`.
4. Execute a classe `Main`.
5. A interface gráfica será aberta e o arquivo `contatos.txt` será criado/lido automaticamente na raiz do projeto.

---
