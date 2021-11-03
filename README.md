# RSO PROJECT 2021: Customers microservice

## Prerequisites

```bash
docker run -d --name pg-customers -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -p 5432:5432 postgres:13
```