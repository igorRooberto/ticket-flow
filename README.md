# TicketFlow API

## Objetivo
Desenvolver uma API para venda de ingressos de eventos, garantindo que o sistema não reserve nem venda mais ingressos do que o estoque disponível, mesmo quando várias pessoas tentarem comprar ao mesmo tempo.

## Tecnologias
- Java
- Spring Boot
- PostgreSQL
- Flyway
- Docker

## Funcionalidades
- [ ] Cadastro e consulta de eventos
- [ ] Cadastro de lotes de ingressos
- [ ] Consulta de disponibilidade
- [ ] Reserva temporária de ingressos
- [ ] Confirmação e cancelamento de reservas
- [ ] Expiração de reservas

## Modelagem do banco de dados
### Modelo Entidade-Relacionamento (MER)

![MER do sistema de ingressos](docs/images/MER_sistema_ingressos.png)

### Modelo Relacional (MR)

![MR do sistema de ingressos](docs/images/MR_sistema_ingressos.png)
