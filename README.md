# TransactionalStack

## Overview

The project is built with a focus on ensuring data especially during operations that involve sensitive data like authentication.

## Project Structure

- **aop**: Contains aspect-oriented programming (AOP) components that manage transaction synchronization.

- **config**: Configuration classes for transaction management.

- **service**: Contains service interfaces and implementations focused on authentication and transaction management.

- **repository**: Data access layer, managing CRUD operations on entities.

- **model**: Defines the domain entities used throughout the application.

## Transaction Management

The core focus of this project is on transaction management, which is implemented through both declarative and programmatic approaches:

### Declarative Transaction Management

Implemented using the `@Transactional` annotation in the `AuthenticationServiceImpl` class. This allows the framework to automatically manage transaction boundaries around the `authenticate` method, handling commit and rollback operations based on the method's execution and exceptions.

### Programmatic Transaction Management

In the `AuthenticationManualTransactionImpl` class, transactions are managed programmatically using `TransactionTemplate`. This approach provides finer control over transaction behavior, such as setting isolation levels, timeouts, and marking transactions for rollback in case of errors.

### Transaction Synchronization

The `TransactionSynchronizationAspect` class demonstrates how to hook into the transaction lifecycle using AOP. It registers custom synchronization logic before and after the execution.

### Key Features

- **Automatic and Manual Transaction Management**: Choose between declarative transactions with `@Transactional` or manual transaction management using `TransactionTemplate`.
- **Custom Rollback Logic**: Handle complex rollback scenarios, ensuring data consistency even when operations fail.

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Yaaesthetic/TransactionalStack.git
   ```
2. **Build and run the application**:
   ```bash
   cd TransactionalStack
   mvn clean install
   mvn spring-boot:run
   ```
3. **Explore the code**: Dive into the `service`, `aop`, and `config` packages to understand how transaction management is implemented.

## Conclusion

**TransactionalStack** is a powerful demonstration of how to manage transactions effectively in a Spring Boot application. Whether you're dealing with simple CRUD operations or complex multi-step processes, this project provides a robust foundation for building reliable, transactional microservices.
