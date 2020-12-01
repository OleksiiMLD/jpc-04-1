# GL Java ProCamp JPC-004

## Simple Hibernate application

### Prerequisites
1. JRE 1.8 or higher
2. PostgreSQL 9.6 or higher

### Preparation
1. Install JRE according to instructions for your platform.

2. Install PostgreSQL RDBMS.

3. Create DB user and scheme and grant rights to it.
   - Run psql util and execute following commands. Change _yourdbname_, _youruser_ and _yourpass_ with the desired
    values.
   ```
   CREATE DATABASE yourdbname;
   CREATE USER youruser WITH ENCRYPTED PASSWORD 'yourpass';
   GRANT ALL PRIVILEGES ON DATABASE yourdbname TO youruser;
   ```

4. Execute DB scripts for table creation from file `db/schema-generation.sql`.
     - with psql (use credentials from step 3):
      ```
      psql youruser -h yourserver -d yourddbname -f db/schema-generation.sql
      ```

5. Fill in your PostgreSQL server and credentials (from step 3) to the 'hibernate.properties' file.
    - In such lines:
    ```
    hibernate.connection.url=jdbc:postgresql://yourhost:5432/yourdbname
    hibernate.connection.username=youruser
    hibernate.connection.password=yourpass
    ```
