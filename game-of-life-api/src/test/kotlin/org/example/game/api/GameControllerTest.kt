package org.example.game.api

import io.restassured.RestAssured
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameControllerTest : AbstractIntegrationTest() {

    @Test
    fun `when next generation of 4x4 grid pattern should return proper next step pattern`() {

        val request = """
            {
              "grid": [
                ["DEAD", "DEAD", "DEAD", "DEAD"],
                ["DEAD", "ALIVE", "ALIVE", "DEAD"],
                ["DEAD", "ALIVE", "ALIVE", "DEAD"],
                ["DEAD", "DEAD", "DEAD", "DEAD"]
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
            .body("grid[1][1]", equalTo("ALIVE"))
            .body("grid[1][2]", equalTo("ALIVE"))
            .body("grid[2][1]", equalTo("ALIVE"))
            .body("grid[2][2]", equalTo("ALIVE"))
    }
}