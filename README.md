# AutoLoc — corrected project

Java 17+, Spring Boot 4.0.8, Maven Wrapper 3.9.16, springdoc 3.0.2.
You do not need to install Maven separately. Internet access is needed the first
time to download Maven and the project dependencies.

## 1. Open and run (Windows / IntelliJ)

1. Extract this ZIP to a new folder. Use this corrected project rather than
   merging it into the old folder: the invalid `StatutReservationRepository.java`
   has been removed and must not remain in your source tree.
2. In IntelliJ: **File → Open**, select this folder containing `pom.xml`, then
   load the Maven project. Set the Project SDK and Maven runner JRE to Java 17
   or newer. Reload Maven after opening.
3. Open a terminal in the folder containing `pom.xml`.
4. In PowerShell, run:

   ```powershell
   .\mvnw.cmd clean spring-boot:run
   ```

   Alternatively, run `AutolocApplication.main()` in IntelliJ.
5. Wait for the console message `Started AutolocApplication` and Tomcat on port
   `8080`. Keep the application running.
6. Open **http://localhost:8080/swagger-ui/index.html** in your browser.

The short URL http://localhost:8080/swagger-ui.html redirects to the same UI.
OpenAPI JSON: http://localhost:8080/v3/api-docs.

For Linux/macOS:

```bash
chmod +x mvnw
./mvnw clean spring-boot:run
```

## 2. Database choices

### Default: start immediately without MySQL

The default `local` profile uses an H2 file database in `data/`.
Tables are created automatically, and data survives application restarts.
It starts empty. This is a local development database, separate from your MySQL
records. To stop the application, press Ctrl+C.

### Use your MySQL database

Start MySQL and verify the host, port and credentials. The original defaults are
`localhost:3306`, database `autolocdb`, user `root`, empty password.
The user needs permission to create the database and tables, or create the database
first and grant table access.

In PowerShell:

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "YOUR_MYSQL_PASSWORD"
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

For an empty MySQL password, use `$env:DB_PASSWORD = ""`.
To change the database address, set `DB_URL`, for example:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/autolocdb?createDatabaseIfNotExist=true"
```

Linux/macOS:

```bash
export DB_USERNAME=root
export DB_PASSWORD='YOUR_MYSQL_PASSWORD'
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

In IntelliJ, set active profile to `mysql` (or environment variable
`SPRING_PROFILES_ACTIVE=mysql`) and supply the `DB_USERNAME`, `DB_PASSWORD`
and optional `DB_URL` environment variables in the run configuration.
Swagger uses the same URLs with either database profile.

## 3. Try Swagger

Expand **agence-controller → POST /agence/ajouter → Try it out**.
Replace the request body with:

```json
{
  "nom": "AutoLoc Tunis",
  "ville": "Tunis",
  "adresse": "Centre-ville",
  "telephone": "20000000"
}
```

Click **Execute**. Do not send `idAgence` when adding an agency: the database
creates it. Copy the returned ID, then try `GET /agence/get/{id}`.
For `PUT /agence/modifier`, include the existing ID and the full desired record:

```json
{
  "idAgence": 1,
  "nom": "AutoLoc Sousse",
  "ville": "Sousse",
  "adresse": "Centre-ville",
  "telephone": "20000000"
}
```

Replace `1` with the actual returned ID. PUT replaces the record's fields;
fields you omit are not preserved. Delete with `DELETE /agence/supprimer/{id}`.

All nine entity groups keep the same original route pattern:

| Group | Routes (prefix plus suffix) |
| --- | --- |
| agence, client, contrat, employe, equipement, maintenance, paiement, reservation, vehicule | POST `/ajouter`, PUT `/modifier`, DELETE `/supprimer/{id}`, GET `/get/{id}`, GET `/getAll` |

Dates use `YYYY-MM-DD`. Enum values are case sensitive:

| Field | Accepted values |
| --- | --- |
| Reservation.statut | EN_ATTENTE, CONFIRMEE, ANNULEE, TERMINEE |
| Vehicule.statut | DISPONIBLE, LOUE, MAINTENANCE |
| Vehicule.categorie | CITADINE, BERLINE, SUV, UTILITAIRE |
| Employe.role | AGENT, MANAGER |
| Paiement.modePaiement | CARTE, ESPECES, VIREMENT |

## 4. Corrections

- Removed `StatutReservationRepository`: `StatutReservation` is an enum, not a
  JPA entity. The invalid repository prevented the Spring application from
  starting, so Swagger could not be served.
- Replaced the duplicate `int`/`long` agency methods with a single `Long` ID
  throughout the controller, service and repository. Agency deletion now
  actually deletes the record.
- All entities return HTTP 404 for missing records. Invalid/missing IDs return
  HTTP 400. Updates cannot silently create a missing record, and creation
  rejects a supplied ID.
- Added transaction boundaries and final constructor-injected dependencies.
- Added checks for invalid email formats, negative amounts/rates and reversed
  reservation/maintenance date ranges.
- Fixed contract JSON creation when `valide` is omitted; it defaults to `false`.
  Validation responses include the fields and reasons that failed.
- Added explicit Swagger settings and API metadata, while keeping the compatible
  Spring Boot 4.0 / springdoc 3.0 dependency pair.
- Separated local and MySQL configuration; made MySQL credentials configurable
  through environment variables.
- Simplified Lombok compiler configuration and retained parameter metadata.
- Added integration tests for startup, all nine CRUD groups, error cases,
  validation, Swagger resources and the 45 documented API paths.

The original entity fields and independent tables are preserved. Client–vehicle
reservation relationships, authentication and rental availability rules were not
present in the supplied source; this correction does not invent those features.

## 5. Test and build

Windows PowerShell:

```powershell
.\mvnw.cmd clean verify
```

Linux/macOS:

```bash
./mvnw clean verify
```

Tests use a separate in-memory H2 database under the `test` profile and do not
modify your local or MySQL data. After building:

```bash
java -jar target/autoloc-0.0.1-SNAPSHOT.jar
```

A live MySQL server is not needed for the tests. See `VERIFICATION.md` for the
actual checks performed on this delivery.

## 6. If Swagger does not open

- **Connection refused / cannot reach page:** the backend has not started or
  has stopped. Read the first error in the terminal. Wait for `Started
  AutolocApplication` before opening Swagger.
- **Port 8080 already in use:** stop the previous instance, or in PowerShell run
  `$env:SERVER_PORT = "8081"` before starting, then open
  `http://localhost:8081/swagger-ui/index.html`.
- **Failed to load API definition:** open `/v3/api-docs` directly and inspect
  the backend error. Reload Maven and restart the corrected project. Ensure
  `StatutReservationRepository.java` and old compiled classes are gone; use
  `clean` in the launch command.
- **MySQL connection/access denied:** start MySQL and correct the environment
  variables, or run the default local profile to open Swagger immediately.
- **Missing getters/setters in IntelliJ:** reload Maven and enable annotation
  processing. Maven configures Lombok for command-line builds.
- **Java/Maven download error:** check `java -version`, `JAVA_HOME`, internet
  access and your local proxy settings. Use your machine's own Maven settings.
