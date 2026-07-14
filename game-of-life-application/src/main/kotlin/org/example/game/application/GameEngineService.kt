package org.example.game.application

import org.example.game.core.BSRule
import org.example.game.core.Board
import org.example.game.core.CellState
import org.example.game.core.Position

class GameEngineService : CalculateNextStepUseCase, GenerateRandomBoardUseCase {


    override fun calculateNextStep(
        boardRequest: BoardRequestDTO,
    ): BoardResponseDTO {

        boardRequest.ruleParams.type

        val rule = when (boardRequest.ruleParams) {
            is RuleParams.BSRule -> {
                BSRule(boardRequest.ruleParams.birthConditionValues, boardRequest.ruleParams.survivesConditionValues)
            }
        }
        val board = boardRequest.toDomain()


        return board.nextStep(rule).toDto()
    }

    override fun generateRandom(width: Int, height: Int): BoardResponseDTO {
        val board = Board.createRandom(Pair(width, height))
        return board.toDto()
    }
}

private fun Board.toDto(): BoardResponseDTO {
    val aliveCells = this.cells.keys.map { position ->
        PositionDTO(position.x, position.y)
    }
    return BoardResponseDTO(aliveCells)
}

private fun BoardRequestDTO.toDomain(): Board {

    val cells = HashMap<Position, CellState>()

    for (positionDTO in this.aliveCells) {
        cells[Position(positionDTO.x, positionDTO.y)] = CellState.ALIVE
    }

    return Board(cells)
}