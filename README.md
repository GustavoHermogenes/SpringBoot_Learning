<p align="center">
  <img src="banner.svg" width="100%" alt="Sistema Bancário"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-003366?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.5-003366?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Thymeleaf-003366?style=for-the-badge&logo=thymeleaf&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-003366?style=for-the-badge&logo=mysql&logoColor=white"/>
</p>

---

## <img src="https://img.shields.io/badge/-%20-1A75FF?style=flat-square" height="15" align="absmiddle"/> Sobre o projeto

Projeto acadêmico desenvolvido com **Spring Boot** que simula um **sistema bancário**. A aplicação cadastra clientes, contas e cartões e realiza **transferências via Pix** com **regras de segurança e prevenção a fraudes**. As páginas são renderizadas com **Thymeleaf**, e os dados ficam em **MySQL** por meio do **Spring Data JPA**.

---

## <img src="https://img.shields.io/badge/-%20-1A75FF?style=flat-square" height="15" align="absmiddle"/> Funcionalidades

**Contas e clientes**
- Cadastro de clientes e abertura de contas
- Login de acesso à conta
- Página inicial com os dados da conta

**Cartões**
- Geração de número de cartão único, válido pelo **algoritmo de Luhn**
- Geração de CVV

**Pix e antifraude**
- Transferência entre contas, com validação de conta de origem, conta de destino e saldo
- Limite de **R$ 1.000,00** por transferência
- Confirmação extra quando há **3 ou mais transferências em 24 horas**
- Confirmação extra quando a conta recebe **mais de 10 valores idênticos**
- Alerta para **contas marcadas como suspeitas**

---

## <img src="https://img.shields.io/badge/-%20-1A75FF?style=flat-square" height="15" align="absmiddle"/> Arquitetura

```
src/main/java/com/example/Bradesco
├── controller   # Rotas: login, cadastro, página inicial e Pix
├── model        # Entidades: Cliente, Conta, Cartao, Transacao, ContaSuspeita, RegraLimite
├── repository   # Interfaces Spring Data JPA
└── service      # Regras de negócio (geração de cartões)

src/main/resources
├── templates    # Páginas Thymeleaf
└── static       # CSS e JavaScript
```

---

## <img src="https://img.shields.io/badge/-%20-1A75FF?style=flat-square" height="15" align="absmiddle"/> Identidade visual

| Cor | Hex | Uso |
|:---:|:---:|:---|
| <img src="https://img.shields.io/badge/-%20%20%20%20%20%20-1A75FF?style=flat-square"/> | `#1A75FF` | Cor principal (botões, destaques) |
| <img src="https://img.shields.io/badge/-%20%20%20%20%20%20-003366?style=flat-square"/> | `#003366` | Azul escuro (cabeçalhos) |
| <img src="https://img.shields.io/badge/-%20%20%20%20%20%20-CC092F?style=flat-square"/> | `#CC092F` | Vermelho de destaque |
| <img src="https://img.shields.io/badge/-%20%20%20%20%20%20-FFFFFF?style=flat-square"/> | `#FFFFFF` | Fundos claros |

---

## <img src="https://img.shields.io/badge/-%20-1A75FF?style=flat-square" height="15" align="absmiddle"/> Como executar

**Pré-requisitos:** Java 21 e MySQL. O Maven Wrapper já vem no projeto.

```bash
# Clonar o repositório
git clone https://github.com/GustavoHermogenes/SpringBoot_Learning.git
cd SpringBoot_Learning

# Criar o banco de dados no MySQL
#   CREATE DATABASE bradesco;
# Ajuste usuário e senha em src/main/resources/application.properties

# Iniciar a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`. As tabelas são criadas automaticamente pelo JPA (`ddl-auto=update`).

---

## <img src="https://img.shields.io/badge/-%20-1A75FF?style=flat-square" height="15" align="absmiddle"/> Autor

<p>
  <b>Gustavo Hermogenes</b> — Desenvolvedor Full Stack<br/><br/>
  <a href="https://github.com/GustavoHermogenes" target="_blank"><img src="https://img.shields.io/badge/GitHub-003366?style=for-the-badge&logo=github&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/gustavohermogenes/" target="_blank"><img src="https://img.shields.io/badge/LinkedIn-003366?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
</p>

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:1A75FF,100:003366&height=100&section=footer" width="100%"/>
</p>
