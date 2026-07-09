package org.example.game

import kotlin.random.Random

class Board(val grid: Array<Array<BasicCellState>>,
            private val rule: Rule) {

    fun randomize(): Board {
        val size = grid.size
        val newGrid = Array(size) { Array(size) { BasicCellState.DEAD } }
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (Random.nextBoolean()) {
                    newGrid[i][j] = BasicCellState.ALIVE
                } else {
                    newGrid[i][j] = BasicCellState.DEAD
                }
            }
        }
        return Board(newGrid, rule)
    }

    fun nextStep(): Board {
        val size = grid.size
        val newGrid = Array(size) { Array(size) { BasicCellState.DEAD } }
            for (i in 0 until size) {
                for (j in 0 until size) {
                    val neighbors = getNeighborsCount(i, j)
                    newGrid[i][j] = rule.nextState(grid[i][j], neighbors)
                }
            }

        return Board(newGrid, rule)
    }

    private fun getNeighborsCount(x: Int, y: Int): Int {
        var neighbors = 0

        for (numX in -1..1) {
            for (numY in -1..1) {
                if (numX != 0 || numY != 0) {
                    val targetX = x + numX
                    val targetY = y + numY

                    if (isInsideGrid(targetX, targetY) && grid[targetX][targetY] == BasicCellState.ALIVE) {
                        neighbors++
                    }
                }

            }
        }

        return neighbors
    }

    private fun isInsideGrid(x: Int, y: Int): Boolean {
        return !(x < 0 || x > grid.size - 1 || y < 0 || y > grid.size - 1)
    }
}