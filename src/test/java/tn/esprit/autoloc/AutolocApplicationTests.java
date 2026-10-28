package tn.esprit.autoloc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AutolocApplicationTests {
    @LocalServerPort
    private int port;

    private final HttpClient http = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL).build();
    private final JsonMapper json = JsonMapper.builder().build();

    static Stream<Arguments> entities() {
        return Stream.of(
                Arguments.of("agence", "Agence", "nom", "Agence Tunis", "Agence Sousse"),
                Arguments.of("client", "Client", "nom", "Client A", "Client B"),
                Arguments.of("contrat", "Contrat", "dateSignature", "2026-10-28", "2026-10-29"),
                Arguments.of("employe", "Employe", "nom", "Employe A", "Employe B"),
                Arguments.of("equipement", "Equipement", "libelle", "GPS", "Siege enfant"),
                Arguments.of("maintenance", "Maintenance", "description", "Vidange", "Pneus"),
                Arguments.of("paiement", "Paiement", "datePaiement", "2026-10-28", "2026-10-29"),
                Arguments.of("reservation", "Reservation", "statut", "EN_ATTENTE", "CONFIRMEE"),
                Arguments.of("vehicule", "Vehicule", "immatriculation", "123 TU 456", "789 TU 012"));
    }

    @ParameterizedTest
    @MethodSource("entities")
    void crudAndErrorsWorkForEveryEntity(String route, String type, String field,
                                         String initialValue, String updatedValue) throws Exception {
        String base = "/" + route;
        String idField = "id" + type;
        String initialBody = "{\"" + field + "\":\"" + initialValue + "\"}";
        HttpResponse<String> created = request("POST", base + "/ajouter", initialBody);
        assertThat(created.statusCode()).as(created.body()).isEqualTo(200);
        long id = json.readTree(created.body()).path(idField).asLong();
        assertThat(id).isPositive();

        HttpResponse<String> retrieved = request("GET", base + "/get/" + id, null);
        assertThat(retrieved.statusCode()).isEqualTo(200);
        assertThat(json.readTree(retrieved.body()).path(field).asText()).isEqualTo(initialValue);

        String updateBody = "{\"" + idField + "\":" + id + ",\"" + field
                + "\":\"" + updatedValue + "\"}";
        HttpResponse<String> updated = request("PUT", base + "/modifier", updateBody);
        assertThat(updated.statusCode()).as(updated.body()).isEqualTo(200);
        assertThat(json.readTree(updated.body()).path(idField).asLong()).isEqualTo(id);
        HttpResponse<String> afterUpdate = request("GET", base + "/get/" + id, null);
        assertThat(json.readTree(afterUpdate.body()).path(field).asText()).isEqualTo(updatedValue);
        HttpResponse<String> all = request("GET", base + "/getAll", null);
        assertThat(all.statusCode()).isEqualTo(200);
        assertThat(all.body()).contains(updatedValue);

        assertThat(request("POST", base + "/ajouter", updateBody).statusCode()).isEqualTo(400);
        assertThat(request("PUT", base + "/modifier", initialBody).statusCode()).isEqualTo(400);
        assertThat(request("GET", base + "/get/0", null).statusCode()).isEqualTo(400);
        assertThat(request("GET", base + "/get/invalid", null).statusCode()).isEqualTo(400);
        assertThat(request("DELETE", base + "/supprimer/" + id, null).statusCode()).isEqualTo(200);
        HttpResponse<String> missing = request("GET", base + "/get/" + id, null);
        assertThat(missing.statusCode()).isEqualTo(404);
        assertThat(json.readTree(missing.body()).path("detail").asText()).contains(type);
        assertThat(request("DELETE", base + "/supprimer/" + id, null).statusCode()).isEqualTo(404);
        assertThat(request("PUT", base + "/modifier", updateBody).statusCode()).isEqualTo(404);
        assertThat(request("GET", base + "/get/" + id, null).statusCode()).isEqualTo(404);
    }

    @Test
    void swaggerAndOpenApiAreServedByTheRunningApplication() throws Exception {
        HttpResponse<String> page = request("GET", "/swagger-ui.html", null);
        assertThat(page.statusCode()).isEqualTo(200);
        assertThat(page.body()).contains("Swagger UI", "swagger-ui-bundle.js");
        assertThat(request("GET", "/swagger-ui/swagger-ui-bundle.js", null).statusCode()).isEqualTo(200);
        assertThat(request("GET", "/swagger-ui/swagger-initializer.js", null).statusCode()).isEqualTo(200);
        assertThat(request("GET", "/v3/api-docs/swagger-config", null).statusCode()).isEqualTo(200);
        HttpResponse<String> response = request("GET", "/v3/api-docs", null);
        assertThat(response.statusCode()).as(response.body()).isEqualTo(200);
        JsonNode spec = json.readTree(response.body());
        assertThat(spec.path("info").path("title").asText()).isEqualTo("AutoLoc API");
        assertThat(spec.path("paths").size()).isEqualTo(45);
        for (String route : new String[]{"agence", "client", "contrat", "employe", "equipement",
                "maintenance", "paiement", "reservation", "vehicule"}) {
            assertThat(spec.path("paths").has("/" + route + "/ajouter")).isTrue();
            assertThat(spec.path("paths").has("/" + route + "/get/{id}")).isTrue();
        }
        assertThat(spec.path("components").path("schemas").path("Reservation")
                .path("properties").has("dateRangeValid")).isFalse();
    }

    @Test
    void validatesDatesAmountsEmailEnumsAndMalformedJson() throws Exception {
        for (String route : new String[]{"reservation", "maintenance"}) {
            assertThat(request("POST", "/" + route + "/ajouter",
                    "{\"dateDebut\":\"2026-10-29\",\"dateFin\":\"2026-10-28\"}").statusCode())
                    .isEqualTo(400);
        }
        assertThat(request("POST", "/paiement/ajouter", "{\"montant\":-1}").statusCode()).isEqualTo(400);
        assertThat(request("POST", "/contrat/ajouter", "{\"montantTotal\":-1}").statusCode()).isEqualTo(400);
        assertThat(request("POST", "/vehicule/ajouter", "{\"tarifJournalier\":-1}").statusCode()).isEqualTo(400);
        assertThat(request("POST", "/client/ajouter", "{\"email\":\"invalid\"}").statusCode()).isEqualTo(400);
        assertThat(request("POST", "/reservation/ajouter", "{\"statut\":\"INVALID\"}").statusCode()).isEqualTo(400);
        assertThat(request("POST", "/agence/ajouter", "{").statusCode()).isEqualTo(400);
    }

    @Test
    void agencyIdsAreNotTruncatedToAnInteger() throws Exception {
        assertThat(request("GET", "/agence/get/2147483648", null).statusCode()).isEqualTo(404);
    }

    private HttpResponse<String> request(String method, String path, String body) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path))
                .header("Content-Type", "application/json");
        builder.method(method, body == null ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(body));
        return http.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }
}
