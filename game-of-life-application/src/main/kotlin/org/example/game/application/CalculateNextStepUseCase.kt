package org.example.game.application


interface CalculateNextStepUseCase {
    fun calculateNextStep(boardRequest: BoardRequestDTO): BoardResponseDTO
}