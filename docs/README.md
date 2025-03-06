# GoalNotifier

[Link para o Projeto no GitHub](https://github.com/erosnoxx/GoalNotifier)

GoalNotifier é uma aplicação desenvolvida com Java e Spring Boot, focada no gerenciamento e monitoramento de metas pessoais ou de equipe. O principal objetivo do sistema é fornecer uma interface simples para o usuário criar, visualizar e acompanhar o progresso de suas metas, com notificações automáticas para alertar quando metas são atingidas ou alteradas.

### Funcionalidades Principais:

- **Gestão de Usuários**: Controle de usuários com diferentes papéis (admin e usuário comum).
- **Gestão de Metas**: Criação, visualização e acompanhamento das metas definidas pelos usuários.
- **Notificações Assíncronas**: O sistema utiliza RabbitMQ para enviar notificações de forma assíncrona, garantindo que as notificações sejam enviadas de forma eficiente sem sobrecarregar a aplicação principal.

### Arquitetura:

O RabbitMQ é integrado ao projeto para processar e enviar notificações de forma assíncrona. Isso permite que a aplicação principal continue leve e responsiva, enquanto a parte de notificações é tratada por jobs em background. A implementação do RabbitMQ permite uma comunicação desacoplada e escalável para o envio de mensagens entre os diferentes componentes do sistema.

### Objetivo:

Este projeto tem como objetivo ser uma solução simples e eficiente para quem deseja acompanhar e se motivar a atingir suas metas pessoais, com a vantagem de automatizar o processo de notificações usando tarefas assíncronas, sem afetar o desempenho da aplicação principal.
