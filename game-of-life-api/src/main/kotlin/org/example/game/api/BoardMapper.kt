package org.example.game.api

import org.example.game.core.BasicCellState
import org.example.game.core.Board
import org.example.game.core.Rule


class BoardMapper {

    fun toDto(board: Board): BoardDTO {
        return BoardDTO(grid = board.grid.map { row ->
            row.map { cell ->
                when (cell) {
                    BasicCellState.ALIVE -> CellStateDTO.ALIVE
                    BasicCellState.DEAD -> CellStateDTO.DEAD
                }
            }
        })
    }

    fun toEntity(dto: BoardDTO, rule: Rule): Board {
        val size = dto.grid.size
        val grid = Array(size) { Array(size) { BasicCellState.DEAD } }

        for (x in 0 until size) {
            for (y in 0 until size) {
                grid[x][y] = when (dto.grid[x][y]) {
                    CellStateDTO.ALIVE -> BasicCellState.ALIVE
                    CellStateDTO.DEAD -> BasicCellState.DEAD
                }
            }
        }

        return Board(grid, rule)
    }
}