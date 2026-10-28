# Verification — 2026-10-28

Result: **BUILD SUCCESS** with Java 17.0.20 and Maven Wrapper 3.9.16.
A clean Maven `verify` build compiled the source, ran the integration tests and
produced the Spring Boot executable JAR.

**12 tests executed; 0 failures; 0 errors; 0 skipped.**

The tests run against a real embedded Tomcat server and an isolated H2 database.
They verify:

- Create, get, list, update and delete for each of the nine entity groups.
- Rejection of IDs on create, missing IDs on update and invalid/nonpositive IDs.
- HTTP 404 for missing records and updates/deletes of missing records.
- Agency IDs larger than the 32-bit integer range.
- Invalid email formats, negative amounts/rates, reversed date ranges,
  invalid enum values and malformed JSON.
- `/swagger-ui.html` redirects successfully to the Swagger page.
- Swagger's JavaScript bundle, initializer and `/v3/api-docs/swagger-config`
  return HTTP 200.
- `/v3/api-docs` returns HTTP 200, the AutoLoc API title and 45 documented paths.

A separate smoke check started the packaged JAR using the default `local`
profile, confirmed that the file-backed H2 database was created, and retrieved
Swagger HTML and the OpenAPI JSON successfully over HTTP.

MySQL configuration is included, but no live MySQL server was available for
verification. The automated tests do not modify a MySQL database. These checks
verify the supplied CRUD API; full car-rental business workflows were not part
of the original implementation.
