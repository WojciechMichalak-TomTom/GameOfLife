package org.example.game.core

import kotlin.random.Random

class Board(val cells: MutableMap<Position, CellState>,
            private val rule: Rule) {

//    fun randomize(): Board {
//        val size = cells.size
//        val newGrid = Array(size) { Array(size) { CellState.DEAD } }
//        for (i in 0 until size) {
//            for (j in 0 until size) {
//                if (Random.nextBoolean()) {
//                    newGrid[i][j] = CellState.ALIVE
//                } else {
//                    newGrid[i][j] = CellState.DEAD
//                }
//            }
//        }
//        return Board(newGrid, rule)
//    }

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