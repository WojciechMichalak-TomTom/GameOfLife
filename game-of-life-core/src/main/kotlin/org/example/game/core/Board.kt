package org.example.game.core

import kotlin.random.Random


class Board(val cells: Map<Position, CellState>) {

    companion object {
        fun createRandom(dimensions: Pair<Int, Int>): Board {
            val (width, height) = dimensions
            val cells = HashMap<Position, CellState>()

            val startX = -width / 2
            val endX = width / 2

            val startY = -height / 2
            val endY = height / 2

            for (x in startX..endX) {
                for (y in startY..endY) {
                    if (Random.nextBoolean()) {
                        cells[Position(x, y)] = CellState.ALIVE
                    }
                }
            }
            return Board(cells)
        }
    }


    fun nextStep(rule: Rule): Board {

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

        return Board(newCells)
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