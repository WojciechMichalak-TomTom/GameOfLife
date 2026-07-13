package org.example.game.api

import io.restassured.RestAssured
import org.hamcrest.Matchers.hasItems
import org.junit.jupiter.api.Test

class GameControllerTest : AbstractIntegrationTest() {

    @Test
    fun `when next generation of pattern should return proper next step pattern`() {

        val request = """
            {
              "aliveCells": [
                {"x": 1, "y": 1},
                {"x": 1, "y": 2},
                {"x": 2, "y": 1},
                {"x": 2, "y": 2}
              ]
            }
        """.trimIndent()

        RestAssured
            .given()
            .contentType("application/json")
            .body(request)
            .post("/game/next-step")
            .then()
            .statusCode(200)
            .body("aliveCells", hasItems(
            mapOf("x" to 1, "y" to 1),
            mapOf("x" to 1, "y" to 2),
            mapOf("x" to 2, "y" to 1),
            mapOf("x" to 2, "y" to 2)
        ))
    }
}