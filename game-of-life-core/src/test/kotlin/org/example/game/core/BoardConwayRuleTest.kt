package org.example.game.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoardConwayRuleTest {


    private val conwayRule = BSRule(birthCondition = setOf(3), survivesCondition = setOf(2, 3))

    private fun createBoard(positions: List<Position>): Board {
        val cells = HashMap<Position, CellState>()
        for (pos in positions) {
            cells[pos] = CellState.ALIVE
        }
        return Board(cells)
    }

    @Test
    fun `when next generation of 3x3 grid pattern should return proper next step pattern`() {
        // D D A
        // D A A
        // A D A
        val board = createBoard(listOf(
            Position(0, 2),
            Position(1, 1), Position(1, 2),
            Position(2, 0), Position(2, 2)
        ))

        val board2 = board.nextStep(conwayRule)

        // D A A D
        // D D A A
        // D D A D
        val expectedCells = hashMapOf(
            Position(0, 1) to CellState.ALIVE,
            Position(0, 2) to CellState.ALIVE,
            Position(1, 2) to CellState.ALIVE,
            Position(1, 3) to CellState.ALIVE,
            Position(2, 2) to CellState.ALIVE
        )

        assertEquals(expectedCells, board2.cells)
    }

    @Test
    fun `when next generation of empty board should return empty board`() {
        val board = createBoard(emptyList())

        val board2 = board.nextStep(conwayRule)

        val expectedCells = hashMapOf<Position, CellState>()

        assertEquals(expectedCells, board2.cells)
    }

    @Test
    fun `when next generation of live cell with fewer than 2 neighbours should cell die`() {
        // A A
        val board = createBoard(listOf(
            Position(0, 0), Position(0, 1)
        ))

        val board2 = board.nextStep(conwayRule)

        val expectedCells = hashMapOf<Position, CellState>()

        assertEquals(expectedCells, board2.cells)
    }

    @Test
    fun `when next generation of live cell with 2 or 3 neighbours should cell live`() {
        // A A
        // A A
        val board = createBoard(listOf(
            Position(0, 0), Position(0, 1),
            Position(1, 0), Position(1, 1)
        ))

        val board2 = board.nextStep(conwayRule)

        // A A
        // A A
        val expectedCells = hashMapOf(
            Position(0, 0) to CellState.ALIVE, Position(0, 1) to CellState.ALIVE,
            Position(1, 0) to CellState.ALIVE, Position(1, 1) to CellState.ALIVE
        )

        assertEquals(expectedCells, board2.cells)
    }

    @Test
    fun `when next generation of live cell with more than 3 should cell die`() {
        // A D A
        // D A D
        // A D A
        val board = createBoard(listOf(
            Position(0, 0), Position(0, 2),
            Position(1, 1),
            Position(2, 0), Position(2, 2)
        ))

        val board2 = board.nextStep(conwayRule)

        // D A D
        // A D A
        // D A D
        val expectedCells = hashMapOf(
            Position(0, 1) to CellState.ALIVE,
            Position(1, 0) to CellState.ALIVE,
            Position(1, 2) to CellState.ALIVE,
            Position(2, 1) to CellState.ALIVE
        )

        assertEquals(expectedCells, board2.cells)
    }

    @Test
    fun `when next generation of dead cell with 3 neighbours should cell live`() {
        // A A
        // A D
        val board = createBoard(listOf(
            Position(0, 0), Position(0, 1),
            Position(1, 0)
        ))

        val board2 = board.nextStep(conwayRule)

        // A A
        // A A
        val expectedCells = hashMapOf(
            Position(0, 0) to CellState.ALIVE, Position(0, 1) to CellState.ALIVE,
            Position(1, 0) to CellState.ALIVE, Position(1, 1) to CellState.ALIVE
        )

        assertEquals(expectedCells, board2.cells)
    }
}