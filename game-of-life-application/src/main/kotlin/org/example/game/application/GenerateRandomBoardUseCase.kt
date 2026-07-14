package org.example.game.application

interface GenerateRandomBoardUseCase {
    fun generateRandom(width: Int, height: Int): BoardResponseDTO
}