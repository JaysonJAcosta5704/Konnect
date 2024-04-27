package onetomany;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class nishi_systemtest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }



    @Test
    public void testCreateReport() {
        JSONObject newReport = new JSONObject();
        try {
            newReport.put("reportDetail", "Annual sales report");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(newReport.toString())
                .when()
                .post("/reports/");

        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"success\"}", response.getBody().asString());
    }

    @Test
    public void testDeleteReport() {
        // Assuming there's a report with ID 1 to delete
        Response response = RestAssured.given()
                .when()
                .delete("/reports/1");

        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"success\"}", response.getBody().asString());
    }

    @Test
    public void testCreateUser() {
        JSONObject newUser = new JSONObject();
        try {
            newUser.put("username", "testUser1691");
            newUser.put("emailId", "testUser@example.com");
            newUser.put("userPassword", "testPass");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(newUser.toString())
                .when()
                .post("/users/");

        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"success\"}", response.getBody().asString());
    }


    @Test
    public void testGetMatchById() {
        // Assuming there's a match with ID 1
        Response response = RestAssured.given()
                .when()
                .get("/matches/1");
        assertEquals(200, response.getStatusCode());
        // Add assertions for the expected match data
    }

    @Test
    public void testCreateMatch() {
        JSONObject newMatch = new JSONObject();
        try {
            newMatch.put("name", "Test Match");
            // Add other match fields as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(newMatch.toString())
                .when()
                .post("/matches");
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"success\"}", response.getBody().asString());
    }

    @Test
    public void testUpdateMatch() {
        // Assuming there's a match with ID 1 to update
        JSONObject updatedMatch = new JSONObject();
        try {
            updatedMatch.put("name", "Updated Match");
            // Add other updated match fields as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(updatedMatch.toString())
                .when()
                .put("/matches/1");
        assertEquals(200, response.getStatusCode());
        // Add assertions for the expected updated match data
    }


}
