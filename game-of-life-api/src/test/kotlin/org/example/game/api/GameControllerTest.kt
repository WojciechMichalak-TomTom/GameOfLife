package org.example.game.api

import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import org.assertj.core.api.Assertions.assertThat
import org.example.game.application.BoardRequestDTO
import org.example.game.application.BoardResponseDTO
import org.example.game.application.PositionDTO
import org.example.game.application.RuleParams
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus.OK

class GameControllerTest : AbstractIntegrationTest() {

    @Test
    fun `when next generation of pattern should return proper next step pattern`() {
        val bsRule: RuleParams = RuleParams.BSRule(
            birthConditionValues = setOf(3),
            survivesConditionValues = setOf(2, 3)
        )

        val request = BoardRequestDTO(
            aliveCells = listOf(
                PositionDTO(1, 1),
                PositionDTO(1, 2),
                PositionDTO(2, 1),
                PositionDTO(2, 2)
            ),
            ruleParams = bsRule
        )

        val expectedResponse = BoardResponseDTO(
            aliveCells = listOf(
                PositionDTO(1, 1),
                PositionDTO(1, 2),
                PositionDTO(2, 1),
                PositionDTO(2, 2)
            )
        )

        val response = RestAssured
            .given()
            .contentType(JSON)
            .body(request)
            .post("/game/next-step")
            .then()
            .statusCode(OK.value())
            .extract()
            .`as`(BoardResponseDTO::class.java)


        assertThat(response.aliveCells)
            .containsExactlyInAnyOrderElementsOf(expectedResponse.aliveCells)
    }
}