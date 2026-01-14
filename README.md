# 🎬 Catálogo Cinematográfico & Quiz Interativo

![Spring Boot](https://img.shields.io/badge/Spring--Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Arduino](https://img.shields.io/badge/Arduino-00979D?style=for-the-badge&logo=arduino&logoColor=white)

Este projeto é um **Portfólio Web Fullstack** desenvolvido para gerenciar um catálogo pessoal de filmes e proporcionar uma experiência interativa através de um **Quiz Game** que integra Software (Spring Boot) e Hardware (Arduino).

---

## 🚀 O Projeto

O sistema permite que um administrador gerencie sua biblioteca de filmes favorita, enquanto usuários podem participar de um desafio interativo. O diferencial deste projeto é o uso de um **protoboard físico** para responder às perguntas do quiz.

### 🎯 Funcionalidades Principais
- **Gestão de Filmes:** CRUD completo (Criar, Ler, Atualizar, Deletar) via API REST.
- **Portfólio Visual:** Interface web moderna para exibição dos filmes e seus detalhes.
- **Quiz Interativo:** Sistema de perguntas sobre cinema com pontuação em tempo real.
- **Integração de Hardware:** Seleção de respostas e feedback visual (LEDs) via Arduino.

---

## 🛠️ Arquitetura do Sistema

O projeto segue o padrão de **Arquitetura em Camadas**, garantindo uma separação clara de responsabilidades:

- **Controller:** Gerencia os endpoints da API e a comunicação com o Front-end.
- **Service:** Contém as regras de negócio e lógica do Quiz.
- **Repository:** Interface de comunicação com o banco de dados MySQL via Spring Data JPA.
- **Model/Entity:** Representação das tabelas de Filmes e Jogadores.
- **Hardware Bridge:** Comunicação Serial/WebSocket entre o Java e o Arduino.

---

## 🔌 Integração com Arduino

O jogo funciona da seguinte maneira:
1. O site exibe uma cena ou pergunta sobre um filme.
2. O jogador utiliza botões físicos em um **Protoboard** para escolher a opção.
3. O **Arduino** envia o sinal para o Backend Spring Boot.
4. O Backend valida a resposta no banco de dados e retorna o resultado (acerto/erro) acionando LEDs físicos.

---

## 📦 Tecnologias Utilizadas

- **Backend:** Java 21, Spring Boot 3.4.1, Spring Data JPA.
- **Banco de Dados:** MySQL.
- **Hardware:** Arduino Uno, LEDs, Botões, Protoboard.
- **Comunicação:** JSerialComm / WebSockets.

---

## 👨‍💻 Autor

**Marcos G.**
*Praticando conhecimentos em Spring Boot, APIs e Internet das Coisas (IoT).*
