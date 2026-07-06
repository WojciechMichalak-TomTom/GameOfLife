package org.example.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun nextStepTest() {
        val board = Board(3)

        val arr = arrayOf(
            intArrayOf(0, 0, 1),
            intArrayOf(0, 1, 1),
            intArrayOf(1, 0, 1)
        )
        board.grid = arr

        board.nextStep()

        var expectedGrid = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 0, 1),
            intArrayOf(0, 0, 1)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))

        board.nextStep()

        expectedGrid = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 0, 1),
            intArrayOf(0, 0, 0)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))

    }

    @Test
    fun nextStepTest2() {
        val board = Board(3)

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

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))

        board.nextStep()
        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))
    }

    @Test
    fun randomizeTest() {
        val board = Board(3)
        val board2 = Board(3)

        board.randomize()
        board2.randomize()

        //TODO check
        Assertions.assertFalse { board.grid.contentDeepEquals(board2.grid) }
    }


    @Test
    fun emptyBoardTest() {
        val board = Board(3)

        val arr = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        board.grid = arr

        board.nextStep()

        val expectedGrid = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))

        board.nextStep()
        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))
    }

    @Test
    fun fullBoardTest() {
        val board = Board(3)

        val arr = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1)
        )
        board.grid = arr

        board.nextStep()

        val expectedGrid = arrayOf(
            intArrayOf(1, 0, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 1)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board.grid))
    }


}