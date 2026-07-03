package org.example.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun nextStepTest() {
        val board = Board()

        val arr = arrayOf(
            intArrayOf(0, 0, 1),
            intArrayOf(0, 1, 1),
            intArrayOf(1, 0, 1)
        )
        board.grid = arr

        board.nextStep()

        val expectedGrid = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 0, 1),
            intArrayOf(0, 0, 1)
        )

        Assertions.assertArrayEquals(expectedGrid, board.grid)

        board.nextStep()

        val expectedGridSecondStep = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 0, 1),
            intArrayOf(0, 0, 0)
        )

        Assertions.assertArrayEquals(expectedGridSecondStep, board.grid)

    }

    @Test
    fun nextStepTest2() {
        val board = Board()

        val arr = arrayOf(
            intArrayOf(1, 0, 1),
            intArrayOf(0, 1, 0),
            intArrayOf(1, 0, 1)
        )
        board.grid = arr

        board.nextStep()

        val expectedGrid = arrayOf(
            intArrayOf(0, 1, 0),
            intArrayOf(1, 0, 1),
            intArrayOf(0, 1, 0)
        )

        Assertions.assertArrayEquals(expectedGrid, board.grid)

        board.nextStep()
        Assertions.assertArrayEquals(expectedGrid, board.grid)
    }

    @Test
    fun randomizeTest() {
        val board = Board()
        val board2 = Board()

        board.randomize()
        board2.randomize()

        //TODO check
        Assertions.assertFalse { board.grid.contentDeepEquals(board2.grid) }
    }

}