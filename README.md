### What is CQRS?
CQRS stands for Command and Query Responsibility Segregation, a pattern that separates read and update operations for 
a data store. Implementing CQRS in your application can maximize its performance, scalability, and security. 
The flexibility created by migrating to CQRS allows a system to better evolve over time and prevents update commands 
from causing merge conflicts at the domain level.

### CQRS Demo Project with Spring Boot, Kafka, MongoDB and MySQL
Sample bank account project that aims to use CQRS notion to segregate
* handling commands, namely, OpenAccountCommand, DepositFundsCommand, WithdrawFundsCommand, CloseAccountCommand and RestoreReadDbCommand
* handling queries, namely, FindAllAccountsQuery, FindAccountByIdQuery, FindAccountByHolderQuery and FindAccountWithBalanceQuery

### Preconditions
* Java 17 should be installed at your local machine.
* Docker and docker-compose are also needed.

### Running the containers
1. Navigate to 'local-env' folder using the terminal.
2. Run ```docker-compose up -d``` command to have MySQL, MongoDB and Kafka containers at your local machine.

### Test
The main classes of account.cmd and account.query projects should be run. A sample Postman collection was shared in local-env folder
to test the commands and queries using REST APIs. You can connect to MongoDB for command database and connect to MySQL for the queries.
*Replay Events* functionality can also be tested by using *Restore Read Database* request in sample Postman collection after deleting the bank_account table in MySQL.


### Architectural Overview
![Architectural Overview](./local-env/ArchitecturalOverview.png)


### Resources
1. https://www.udemy.com/course/java-microservices-cqrs-event-sourcing-with-kafka/
2. https://learn.microsoft.com/en-us/azure/architecture/patterns/cqrs