package org.example.game.core

import kotlin.random.Random

class Board(val cells: MutableMap<Position, CellState>,
            private val rule: Rule) {

    fun nextStep(): Board {

        val newCells = HashMap<Position, CellState>()
        val positionsToCheck = HashSet<Position>()

        for (position in cells.keys) {
            positionsToCheck.add(position)
            positionsToCheck.addAll(getNeighborsPositions(position))
        }

        for (position in positionsToCheck) {
            val state = cells[position] ?: CellState.DEAD
            val neighborsCount = getNeighborsCount(position)
            val nextState = rule.nextState(state, neighborsCount)

            if (nextState != CellState.DEAD) {
                newCells[position] = nextState
            }
        }

        return Board(newCells, rule)
    }

    private fun getNeighborsCount(position: Position): Int {
        var count = 0
        for (neighborPosition in getNeighborsPositions(position)) {
            if (cells[neighborPosition] == CellState.ALIVE) {
                count++
            }
        }

        return count
    }

    private fun getNeighborsPositions(pos: Position): List<Position> {
        val neighbors = mutableListOf<Position>()
        for (dx in -1..1) {
            for (dy in -1..1) {
                if (dx != 0 || dy != 0) {
                    neighbors.add(Position(pos.x + dx, pos.y + dy))
                }
            }
        }
        return neighbors
    }
}