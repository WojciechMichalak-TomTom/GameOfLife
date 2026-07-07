package org.example.model

import kotlin.random.Random

class Board (val size: Int, val grid: Array<Array<CellState>> = Array(size) { Array(size) { CellState.DEAD } }) {

    fun randomize() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (Random.nextBoolean()) {
                    grid[i][j] = CellState.ALIVE
                } else {
                    grid[i][j] = CellState.DEAD
                }
            }
        }
    }

    fun nextStep(): Board {
        val newGrid = Array(size) { Array(size) { CellState.DEAD } }
            for (i in 0 until size) {
                for (j in 0 until size) {
                    val neighbors = getNeighborsCount(i, j)

                    if (grid[i][j] == CellState.ALIVE) {
                        //Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
                        if (neighbors < 2) {
                            newGrid[i][j] = CellState.DEAD
                        }
                        //Any live cell with two or three live neighbours lives on to the next generation.
                        else if (neighbors == 2 || neighbors == 3) {
                            newGrid[i][j] = CellState.ALIVE
                        }
                        //Any live cell with more than three live neighbours dies, as if by overpopulation.
                        else {
                            newGrid[i][j] = CellState.DEAD
                        }
                    } else {
                        //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                        if (neighbors == 3) {
                            newGrid[i][j] = CellState.ALIVE
                        }
                    }

                }
            }

        return Board(size, newGrid)
    }

    private fun getNeighborsCount(x: Int, y: Int): Int {
        var neighbors = 0

        for (numX in -1..1) {
            for (numY in -1..1) {
                if (numX != 0 || numY != 0) {
                    val targetX = x + numX
                    val targetY = y + numY

                    if (isInsideGrid(targetX, targetY) && grid[targetX][targetY] == CellState.ALIVE) {
                        neighbors++
                    }
                }

            }
        }

        return neighbors
    }

    private fun isInsideGrid(x: Int, y: Int): Boolean {
        return !(x < 0 || x > size - 1 || y < 0 || y > size - 1)
    }
}