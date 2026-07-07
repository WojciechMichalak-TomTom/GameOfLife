package org.example.game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BoardConwayRuleTest {

    val D = BasicCellState.DEAD
    val A = BasicCellState.ALIVE

    @Test
    fun `3x3 grid pattern next generation test`() {
        val grid = arrayOf(
            arrayOf(D, D, A),
            arrayOf(D, A, A),
            arrayOf(A, D, A)
        )
        val board = Board(3, grid, ConwayRule())

        val board2 = board.nextStep()

        val expectedGrid = arrayOf(
            arrayOf(D, A, A),
            arrayOf(D, D, A),
            arrayOf(D, D, A)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))

    }


    @Test
    fun `Empty board remains empty in next generation test`() {
        val grid = arrayOf(
            arrayOf(D, D, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )
        val board = Board(3, grid, ConwayRule())

        val board2 = board.nextStep()

        val expectedGrid = arrayOf(
            arrayOf(D, D, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `Live cell with fewer than to 2 neighbours dies test`() {
        val grid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )
        val board = Board(3, grid, ConwayRule())


        val board2 = board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(D, D, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `Live cell with 2 or 3 neighbours lives test`() {
        val grid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, A, D),
            arrayOf(D, D, D)
        )
        val board = Board(3, grid, ConwayRule())


        val board2 = board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, A, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `Live cell with more than 3 neighbours dies test`() {
        val grid = arrayOf(
            arrayOf(A, A, A),
            arrayOf(A, A, A),
            arrayOf(A, A, A)
        )
        val board = Board(3, grid, ConwayRule())


        val board2 =  board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(A, D, A),
            arrayOf(D, D, D),
            arrayOf(A, D, A)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `Dead cell with 3 neighbours becomes live cell test`() {
        val grid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, D, D),
            arrayOf(D, D, D)
        )
        val board = Board(3, grid, ConwayRule())


        val board2 = board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, A, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

}