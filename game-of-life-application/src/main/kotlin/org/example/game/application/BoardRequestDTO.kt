package org.example.game.application

data class PositionDTO(
    val x: Int = 0,
    val y: Int = 0
)

data class BoardRequestDTO(
    val aliveCells: List<PositionDTO> = emptyList(),
    val ruleParams: RuleParams
)


data class BoardResponseDTO(
    val aliveCells: List<PositionDTO> = emptyList()
)