package org.example.model

import kotlin.random.Random

class Board (val size: Int) {
    var grid: Array<IntArray> = Array(this@Board.size){ IntArray(this@Board.size) }

    fun randomize() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                grid[i][j] = Random.nextInt(0, 2)
            }
        }
    }

    fun nextStep() {
        val newGrid = Array(size) { IntArray(size) }
            for (i in 0 until size) {
                for (j in 0 until size) {
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
        var neighbors = 0

        for (numX in -1..1) {
            for (numY in -1..1) {
                if (numX == 0 && numY == 0)
                    continue

                val targetX = x + numX
                val targetY = y + numY

                if (targetX < 0 || targetX > size - 1 || targetY < 0 || targetY > size - 1) {
                    continue
                }

                if (grid[targetX][targetY] == 1)
                    neighbors++
            }
        }

        return neighbors
    }
}