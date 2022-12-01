package Lesson3;

import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestSuite1 extends AbstractTest{

    @Test
    void testCase1() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Vietnamese")
                .queryParam("maxCalories", 300)
                .queryParam("number", 1)
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                //.body("amount", lessThan(300))
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl());
    }

    @Test
    void testCase2() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch", "Pizza")
                .queryParam("number", 1)
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                //.body("results", containsString("Pizza") )
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl());
    }

    @Test
    void testCase3() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "French")
                .queryParam("includeIngredients", "cheese, onion, garlic")
                .queryParam("fillIngredients", "true")
                .queryParam("number", 1)
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                //.body("results", containsString("cheese") )
                //.body("results", containsString("onion") )
                //.body("results", containsString("garlic") )
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl());
    }

    @Test
    void testCase4() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Greek")
                .queryParam("type", "soup")
                .queryParam("titleMatch", "Orzo Soup")
                .queryParam("number", 1)
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                //.body("results", containsString("Orzo Soup") )
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl());
    }

    @Test
    void testCase5() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Russian")
                .queryParam("number", 1)
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                .body("totalResults", lessThan(1))
                .when()
                .get(getSearchRecipesUrl());
    }

    @Test
    void testCase6() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title" , "Burger")
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                .body("cuisines", contains("American"))
                .when()
                .post(getClassifyCuisineUrl());
    }

    @Test
    void testCase7() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title" , "Pasta")
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                .body("cuisines", contains("Mediterranean", "European", "Italian"))
                .when()
                .post(getClassifyCuisineUrl());
    }

    @Test
    void testCase8() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "African Chicken Peanut Stew")
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                .body("cuisines", contains("African"))
                .when()
                .post(getClassifyCuisineUrl());
    }

    @Test
    void testCase9() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Red Kidney Bean Jambalaya")
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                .body("cuisines", contains("Creole",
                        "Cajun"))
                .when()
                .post(getClassifyCuisineUrl());
    }

    @Test
    void testCase10() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Hummus and Za'atar")
                .response()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .expect()
                .body("cuisines", contains("Middle Eastern"))
                .when()
                .post(getClassifyCuisineUrl());
    }

    @Test
    void testCase11() {
        String mealPlanId = given()
                .queryParam("hash", getHash())
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        +"\"date\": 1589500800,\n"
                        +"\"slot\": 1,\n"
                        +"\"position\": 0,\n"
                        +"\"type\": \"INGREDIENTS\",\n"
                        +"\"value\": {\n"
                        +"\"ingredients\": [\n"
                        +"{\n"
                        +"\"name\": \"1 banana\"\n"
                        +"}\n"
                        +"]\n"
                        +"}\n"
                        +"}")
                .when()
                .post(getMealPlannerUrl()+getUserName()+"items")
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", getHash())
                .queryParam("apiKey", getApiKey())
                .delete(getMealPlannerUrl()+getUserName()+"items/"+mealPlanId)
                .then()
                .statusCode(200)
                .time(lessThan(2000L));

    }

}
