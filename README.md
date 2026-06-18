#  GitVet - Sistema de Clínica Veterinária

O **GitVet** é um sistema completo de gerenciamento para clínicas veterinárias desenvolvido em **Java**. O projeto nasceu originalmente como uma aplicação rodando puramente em console (com persistência de dados em memória) e evoluiu para uma API robusta integrada ao ecossistema **Spring Boot** com banco de dados relacional **PostgreSQL**.

Para manter a simplicidade e o formato de estudos original, o sistema conta com uma interface interativa via terminal que se comunica diretamente com o banco de dados através de camadas de serviços modernas, unindo o conforto do menu clássico ao poder do armazenamento definitivo.

---

##  Evolução do Projeto

O projeto foi construído em duas etapas principais ao longo do período de estudos:
1. **Fase Inicial (Console & Memória):** Criação das regras de negócio, orientação a objetos (`Tutor`, `Animal`, `Consulta`), coleções para armazenamento temporário e validações de fluxo com `Scanner`.
2. **Fase Atual (Spring Boot & Banco de Dados):** Substituição do gerenciamento em memória por persistência real utilizando **Spring Data JPA** e **PostgreSQL**. Toda a estrutura visual do menu clássico foi mantida, mas agora os dados são salvos permanentemente no banco.

---

##  Tecnologias Utilizadas

* **Linguagem:** Java (JDK 17 ou superior)
* **Framework Principal:** Spring Boot 3.x
* **Persistência de Dados (ORM):** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Ferramenta de Build:** Maven

---

##  Arquitetura do Sistema (Para Desenvolvedores)

O sistema foi estruturado utilizando a **Arquitetura em Camadas** (Multi-tier Architecture), que é o padrão de mercado para aplicações corporativas com Spring Boot. Essa divisão garante a separação de responsabilidades, facilitando a manutenção e a escalabilidade do código.

### As Camadas do Projeto

* **Camada de Apresentação (`MenuConsole.java`):** É a interface com o usuário. Utiliza um menu interativo via terminal com capturas validadas (`Scanner`). Ela roda em uma *Thread* paralela para não travar a inicialização do ecossistema Spring, capturando as entradas do teclado e delegando as ações para a camada de serviço.
* **Camada de Negócio / Serviço (`package service`):** Onde ficam as regras de negócio e validações (ex: verificar se um tutor existe antes de vincular um animal). Faz a ponte entre a interface do console e o banco de dados.
* **Camada de Acesso a Dados / Repositório (`package repository`):** Interfaces que herdam de `JpaRepository`. Graças ao Spring Data JPA, os métodos de persistência (salvar, buscar, deletar, alterar) são gerados automaticamente em tempo de execução, dispensando a escrita manual de código SQL.
* **Camada de Modelo / Entidades (`package model`):** Classes Java puras (`Tutor`, `Animal`, `Consulta`) mapeadas como tabelas do banco de dados através das anotações do **Hibernate/JPA** (`@Entity`, `@Table`, `@Id`, `@ManyToOne`, `@OneToMany`).

### Fluxo de Dados (Exemplo: Cadastro de Tutor)
`MenuConsole` (Lê os dados) ➔ `TutorService` (Processa e valida) ➔ `TutorRepository` (Traduz para SQL) ➔ `PostgreSQL` (Insere a linha permanentemente).

### Estrutura de Pastas
```text
src/main/java/com/example/demo/
│
├── model/              # Classes de Entidade (Tutor, Animal, Consulta)
├── repository/         # Interfaces de Acesso ao Banco de Dados (Spring Data JPA)
├── service/            # Regras de Negócio e Lógica do Sistema (Services)
│
├── MenuConsole.java    # Interface de Linha de Comando (CommandLineRunner)
└── DemoApplication.java# Inicializador do Spring Boot (Método Main)
