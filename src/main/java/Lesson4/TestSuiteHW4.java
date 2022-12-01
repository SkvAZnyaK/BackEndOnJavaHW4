package Lesson4;

import Lesson3.AbstractTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestSuiteHW4 extends AbstractTest {

    ResponseSpecification responseSpec1 = null;
    ResponseSpecification responseSpec2 = null;
    RequestSpecification requestSpec1 = null;
    RequestSpecification requestSpec2 = null;
    RequestSpecification requestSpec3 = null;

    @BeforeEach
    void beforeTest() {
        responseSpec1 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .expectHeader("Connection", "keep-alive")
                .expectHeader("Content-Type", "application/json")
                .build();
        responseSpec2 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .expectHeader("Connection", "keep-alive")
                .expectHeader("Content-Type", "application/json;charset=utf-8")
                .build();

        requestSpec1 = new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .addQueryParam("number", 1)
                .log(LogDetail.ALL)
                .build();
        requestSpec2 = new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .log(LogDetail.ALL)
                .build();
        requestSpec3 = new RequestSpecBuilder()
                .addQueryParam("hash", getHash())
                .addQueryParam("apiKey", getApiKey())
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void testCase1() {
        given()
                .spec(requestSpec1)
                .queryParam("cuisine", "Vietnamese")
                .queryParam("maxCalories", 300)
                .expect()
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase2() {
        given()
                .spec(requestSpec1)
                .queryParam("titleMatch", "Pizza")
                .response()
                .expect()
                //.body("results", containsString("Pizza") )
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase3() {
        given()
                .spec(requestSpec1)
                .queryParam("cuisine", "French")
                .queryParam("includeIngredients", "cheese, onion, garlic")
                .queryParam("fillIngredients", "true")
                .response()
                .expect()
                //.body("results", containsString("cheese") )
                //.body("results", containsString("onion") )
                //.body("results", containsString("garlic") )
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase4() {
        given()
                .spec(requestSpec1)
                .queryParam("cuisine", "Greek")
                .queryParam("type", "soup")
                .queryParam("titleMatch", "Orzo Soup")
                .response()
                .expect()
                //.body("results", containsString("Orzo Soup") )
                .body("number", is(1))
                .when()
                .get(getSearchRecipesUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase5() {
        given()
                .spec(requestSpec1)
                .queryParam("cuisine", "Russian")
                .response()
                .expect()
                .body("totalResults", lessThan(1))
                .when()
                .get(getSearchRecipesUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase6() {
        given()
                .spec(requestSpec2)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title" , "Burger")
                .response()
                .expect()
                .body("cuisines", contains("American"))
                .when()
                .post(getClassifyCuisineUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase7() {
        given()
                .spec(requestSpec2)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title" , "Pasta")
                .response()
                .expect()
                .body("cuisines", contains("Mediterranean", "European", "Italian"))
                .when()
                .post(getClassifyCuisineUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase8() {
        given()
                .spec(requestSpec2)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "African Chicken Peanut Stew")
                .response()
                .expect()
                .body("cuisines", contains("African"))
                .when()
                .post(getClassifyCuisineUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase9() {
        given()
                .spec(requestSpec2)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Red Kidney Bean Jambalaya")
                .response()
                .expect()
                .body("cuisines", contains("Creole",
                        "Cajun"))
                .when()
                .post(getClassifyCuisineUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase10() {
        given()
                .spec(requestSpec2)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Hummus and Za'atar")
                .response()
                .expect()
                .body("cuisines", contains("Middle Eastern"))
                .when()
                .post(getClassifyCuisineUrl())
                .then()
                .spec(responseSpec1);
    }

    @Test
    void testCase11() {
        String mealPlanId = given()
                .spec(requestSpec3)
//                .body(Lesson4/MealPlannerRequestHW4.json)
                .body(("{\n"
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
                        +"}"))
                .when()
                .post(getMealPlannerUrl()+getUserName()+"items")
                .then()
                .spec(responseSpec2)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .spec(requestSpec3)
                .delete(getMealPlannerUrl()+getUserName()+"items/"+mealPlanId)
                .then()
                .spec(responseSpec2);

    }

    @Test
    void testCase12() {
        String shoppingListID = given()
                .spec(requestSpec3)
                //.body(Lesson4/AddToShoppingListHW4.json)
                .body("{\n"
                        +"\"item\": \"1 package baking powder\",\n"
                        +"\"aisle\": \"Baking\",\n"
                        +"\"parse\": true\n"
                        +"}")
                .when()
                .post(getMealPlannerUrl()+getUserName()+"shopping-list/items")
                .then()
                .spec(responseSpec1)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .spec(requestSpec3)
                .when()
                .get(getMealPlannerUrl()+getUserName()+"shopping-list")
                .then()
                .spec(responseSpec2);

        given()
                .spec(requestSpec3)
                .when()
                .delete(getMealPlannerUrl()+getUserName()+"shopping-list/items/"+shoppingListID)
                .then()
                .spec(responseSpec1);
    }

}