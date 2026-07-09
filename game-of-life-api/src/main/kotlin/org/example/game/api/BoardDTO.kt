package org.example.game.api

data class PositionDTO(
    val x: Int,
    val y: Int
)

data class BoardDTO(
    val aliveCells: List<PositionDTO>
)
