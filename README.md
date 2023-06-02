# Persistent-Service Repository

## Introduction

Welcome to the `persistent-service` repository! This service is responsible for reading events from a Kafka topic and persisting the data into a PostgreSQL database. It utilizes the Quarkus framework and Avro for schema definitions.

## Purpose

The purpose of this service is to ensure that event data from Kafka is efficiently and reliably stored into a PostgreSQL database, enabling further data analysis and operations.

## Features

- **Kafka Integration**: The service is capable of subscribing to and handling messages from a Kafka topic.
- **Avro Schemas**: Avro is used for defining data schemas, ensuring consistent data structure.
- **PostgreSQL Persistence**: Data is reliably persisted in a PostgreSQL database, allowing for robust data management and querying.

## Usage

### Docker

To start the service using Docker Compose, navigate to the repository root directory and run:

```bash
cd dev-tools/ && docker-compose up
```

Make sure you have Docker installed and running on your machine before executing this command.

### Local Development

For local development, we use Quarkus' development mode. Before starting, make sure you have Node Version Manager (nvm) installed on your system. Then run the following command:

```bash
nvm quarkus:dev
```

This will start the application in dev mode. The application is now ready to consume events from Kafka and persist the data in PostgreSQL.

**Note:** Ensure that the Kafka broker and PostgreSQL database are accessible and that the necessary environment variables are set appropriately for local development.

## Dependencies

- **Quarkus**: This service uses the Quarkus framework.
- **Kafka**: This service requires a Kafka broker to be available for consuming messages.
- **Avro**: Avro is used for schema definitions.
- **PostgreSQL**: This service requires a PostgreSQL database for persisting the data.

## Contributions

Contributions are always welcome! If you have any enhancements or bug fixes, feel free to make a pull request.

## Licensing

This project is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike (CC BY-NC-SA). This license lets others remix, tweak, and build upon this work non-commercially, as long as they credit the original creation and license their new creations under the identical terms. For more details, please refer to the [LICENSE](LICENSE) file in this repository.

## Contact

If you have any questions or issues, please raise an issue in this repository, or contact us directly.
