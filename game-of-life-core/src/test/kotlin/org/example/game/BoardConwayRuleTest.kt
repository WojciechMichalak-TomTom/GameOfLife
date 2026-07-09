package org.example.game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BoardConwayRuleTest {

    val D = BasicCellState.DEAD
    val A = BasicCellState.ALIVE

    @Test
    fun `when next generation of 3x3 grid pattern should return proper next step pattern`() {
        val grid = arrayOf(
            arrayOf(D, D, A),
            arrayOf(D, A, A),
            arrayOf(A, D, A)
        )
        val board = Board(grid, ConwayRule())

        val board2 = board.nextStep()

        val expectedGrid = arrayOf(
            arrayOf(D, A, A),
            arrayOf(D, D, A),
            arrayOf(D, D, A)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))

    }


    @Test
    fun `when next generation of empty board should return empty board`() {
        val grid = arrayOf(
            arrayOf(D, D, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )
        val board = Board(grid, ConwayRule())

        val board2 = board.nextStep()

        val expectedGrid = arrayOf(
            arrayOf(D, D, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `when next generation of live cell with fewer than 2 neighbours should cell die`() {
        val grid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )
        val board = Board(grid, ConwayRule())


        val board2 = board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(D, D, D),
            arrayOf(D, D, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `when next generation of live cell with 2 or 3 neighbours should cell live`() {
        val grid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, A, D),
            arrayOf(D, D, D)
        )
        val board = Board(grid, ConwayRule())


        val board2 = board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, A, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `when next generation of live cell with more than 3 should cell die`() {
        val grid = arrayOf(
            arrayOf(A, A, A),
            arrayOf(A, A, A),
            arrayOf(A, A, A)
        )
        val board = Board(grid, ConwayRule())


        val board2 =  board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(A, D, A),
            arrayOf(D, D, D),
            arrayOf(A, D, A)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

    @Test
    fun `when next generation of dead cell with 3 neighbours should cell live`() {
        val grid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, D, D),
            arrayOf(D, D, D)
        )
        val board = Board(grid, ConwayRule())


        val board2 = board.nextStep()


        val expectedGrid = arrayOf(
            arrayOf(A, A, D),
            arrayOf(A, A, D),
            arrayOf(D, D, D)
        )

        Assertions.assertTrue(expectedGrid.contentDeepEquals(board2.grid))
    }

}