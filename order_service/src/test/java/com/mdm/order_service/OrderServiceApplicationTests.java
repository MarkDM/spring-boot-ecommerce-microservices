package com.mdm.order_service;

import com.mdm.order_service.dto.OrderRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.math.BigDecimal;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @Autowired
    private PostgreSQLContainer postgreSQLContainer;

    @LocalServerPort
    private Integer port;

    private RestClient restClient;


    @BeforeAll
    static void beforeAll() {
        System.out.println("\n\nStarting tests...\n\n");
    }

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();

        System.out.println("PostgreSQL Container is running: " + postgreSQLContainer.isRunning());
        System.out.println("RestAssured configured to use url: " + RestAssured.baseURI);
        System.out.println("RestAssured configured to use port: " + port);
    }


    @Test
    void ShouldCreateOrder() {

        var orderRequest = new OrderRequest( "TESTSKU", BigDecimal.valueOf(1000.52), 10);

        var orderRequestJson = String.format(Locale.US, """
                {
                    "skuCode": "%s",
                    "price": %.2f,
                    "quantity": %d
                }
                """,
                orderRequest.skuCode(),
                orderRequest.price(),
                orderRequest.quantity()
        );


        given()
                .header("Content-Type", "application/json")
                .body(orderRequestJson)
                .when()
                .post("/api/order")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("skuCode", equalTo("TESTSKU"))
                .body("price", equalTo(1000.52f))
                .body("quantity", equalTo(10));
    }

}
