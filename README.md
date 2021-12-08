# RSO PROJECT 2021: Customers microservice

## Prerequisites

```bash
docker run -d --name pg-customers -e POSTGRES_USER=kolan51 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -p 5432:5432 postgres:13
```

```
CREATE TABLE customers ( id serial PRIMARY KEY, firstName VARCHAR ( 50 ) NOT NULL, lastName VARCHAR ( 50 ) NOT NULL, streetAddress VARCHAR ( 50 ) NOT NULL, postcode VARCHAR ( 50 ) NOT NULL, birthDate VARCHAR (11) NOT NULL);
```