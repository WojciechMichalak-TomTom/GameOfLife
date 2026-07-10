package org.example.game.application

import org.example.game.core.Board

interface CalculateNextStepUseCase {
    fun calculateNextStep(board: Board, ruleName: String) : Board
}