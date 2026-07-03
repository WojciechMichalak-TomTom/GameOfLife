package org.example.model

import kotlin.random.Random

const val BOARD_SIZE = 5

class Board (var grid: Array<IntArray> = Array<IntArray>(BOARD_SIZE) {
    IntArray(BOARD_SIZE) {
        if (Random.nextBoolean())
            1
        else
            0
    }
}) {



    fun nextStep() {
        val newGrid = Array(BOARD_SIZE) { IntArray(BOARD_SIZE) }
            for (i in 0 until BOARD_SIZE) {
                for (j in 0 until BOARD_SIZE) {
                    val neighbors = getNeighbors(i, j)

                    if (grid[i][j] == 1) {
                        //Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
                        if (neighbors < 2)
                            newGrid[i][j] = 0
                        //Any live cell with two or three live neighbours lives on to the next generation.
                        else if (neighbors == 2 || neighbors == 3)
                            newGrid[i][j] = 1
                        //Any live cell with more than three live neighbours dies, as if by overpopulation.
                        else
                            newGrid[i][j] = 0
                    } else {
                        //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                        if (neighbors == 3)
                            newGrid[i][j] = 1
                    }

                }
            }

        this.grid = newGrid
    }

    private fun getNeighbors(x: Int, y: Int): Int {
        val nums = arrayOf(-1, 0, 1)
        var neighbors = 0

        for (numX in nums) {
            for (numY in nums) {
                if (numX == 0 && numY == 0)
                    continue

                val targetX = x + numX
                val targetY = y + numY

                if (targetX < 0 || targetX > BOARD_SIZE - 1 || targetY < 0 || targetY > BOARD_SIZE - 1) {
                    continue
                }

                if (grid[targetX][targetY] == 1)
                    neighbors++
            }
        }

        return neighbors
    }
}