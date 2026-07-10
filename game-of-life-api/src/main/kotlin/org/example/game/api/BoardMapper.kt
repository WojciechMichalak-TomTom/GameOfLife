package org.example.game.api

import org.example.game.core.CellState
import org.example.game.core.Board
import org.example.game.core.Position
import org.example.game.core.Rule


class BoardMapper {

    fun toDto(board: Board): BoardDTO {
        val aliveCells = board.cells.keys.map { position ->
            PositionDTO(position.x, position.y)
        }
        return BoardDTO(aliveCells)
    }

    fun toEntity(dto: BoardDTO): Board {

        val cells = HashMap<Position, CellState>()

        for (positionDTO in dto.aliveCells) {
            cells[Position(positionDTO.x, positionDTO.y)] = CellState.ALIVE
        }

        return Board(cells)
    }
}