package org.example.game.application

import org.example.game.core.Board
import org.example.game.core.ConwayRule
import org.example.game.core.Rule

class GameEngineService : CalculateNextStepUseCase {

    private val rules: Map<String, Rule> = mapOf(
        "CONWAY" to ConwayRule(),
    )

    override fun calculateNextStep(
        board: Board,
        ruleName: String
    ): Board {

        val selectedRule = rules[ruleName] ?: throw IllegalArgumentException("Rule $ruleName not found")

        return board.nextStep(selectedRule)
    }
}