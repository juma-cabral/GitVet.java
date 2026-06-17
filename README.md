#  Sistema de Gestão de Clínica Veterinária (GitVet)

O **GitVet** é uma aplicação Java robusta desenvolvida para o gerenciamento eficiente de clínicas veterinárias. O sistema permite realizar operações de CRUD (Criar, Ler, Atualizar, Deletar) para **Tutores**, **Animais** e **Consultas**, mantendo o relacionamento entre as entidades de forma organizada e segura em memória.

## Funcionalidades

O sistema é dividido em três módulos principais:

* **Gerenciamento de Tutores:** Cadastro, listagem, busca e edição de dados de clientes.
* **Gerenciamento de Animais:** Registro de pets com vínculo obrigatório a um tutor (via ID), permitindo controle preciso de histórico.
* **Gerenciamento de Consultas:** Agendamento de consultas médicas vinculadas a animais, com histórico de atendimentos, veterinário responsável e observações.

##  Arquitetura do Projeto

O código foi estruturado seguindo boas práticas de Orientação a Objetos (POO):

1. **Entidades (Models):** Representam os dados (`Tutor`, `Animal`, `Consulta`).
2. **Gerenciadores (Services):** Contêm a lógica de negócio e manipulação das coleções de dados (`GerenciamentoTutores`, `GerenciamentoAnimais`, `GerenciamentoConsultas`).
3. **Interface de Usuário (Main):** Interface de console menu-driven que interage com o usuário e valida as entradas de dados.

##  Como Executar

### Pré-requisitos
* **JDK 11** ou superior instalado.
* Qualquer IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

### Passos
1. Clone este repositório para sua máquina local.
2. Importe o projeto na sua IDE.
3. Compile as classes dentro do pacote `main`.
4. Execute a classe `Main.java`.
5. O sistema iniciará automaticamente com **dados de teste** populados para facilitar a demonstração das funcionalidades.

##  Tecnologias Utilizadas
* **Linguagem:** Java (Standard Edition).
* **Estruturas de Dados:** `ArrayList` e `List` para armazenamento em memória.
* **Buscas:** Utilização de `Streams` para consultas eficientes por ID.
* **Interface:** Console interativo com tratamento de erros em entradas de usuário.

## 🤝 Contribuições
Este projeto foi desenvolvido como parte de um estudo sobre modelagem de sistemas. Sinta-se à vontade para sugerir melhorias ou realizar um *fork* para adicionar novas funcionalidades.

---
*Desenvolvido por Julianna Cabral.*
