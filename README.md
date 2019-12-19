# BeverageStore

## Configuration

### PostgreSQL Database
1. Setup Postgres Database driver:
    - Get the correct JDBC driver JAR for your PostgreSQL version from https://jdbc.postgresql.org/
    - Copy `resources/postgresql-*.*.****.jar` inside `[GlassFish directory]/glassfish/domains/[domain dir]/lib/`

2. Setup of Postgres Database:
    - [PostGres directory]/pgAdmin4/bin/pgAdmin4.exe (default password: admin)
    - Add new login role (you have to use a password as GlassFish JDBC Resources will require one) [Name: tester, Password: tester, Privileges: can_login: true]
    - Add new database [Name: BeverageStore, Owner: tester]
    - Execute `resources/db_tables.sql` inside pgAdmin to create all necessary tables.

### Build project

1. Check settings in `gradle.properties`, especially the path to Glassfish

2. In the project's root folder:
    - Run target `gradlew startGlassfish`
    - Run target `gradlew initServer`
    - Run target `gradlew build`
    - Run target `gradlew deploy`

Configuration is done. Now see the followings for project overview.

## Functionalties:

#### Beverage Management (http://localhost:8080/frontend/beverages)
- Creation of new beverages
- Modification of existing Beverages
- Deletion of Beverages

#### Incentive Management (http://localhost:8080/frontend/incentives)
- Creation of new Incentive names 
- Modification of existing Incentives
- Deletion of Incentives

#### Create Order (http://localhost:8080/frontend/createorder)
- Displays list of available Beverages
- Orders can be created by selecting the necessary Beverage and its quantity

#### List Report (http://localhost:8080/frontend/report)
- Summary Report
- Report broken down to incentive type
