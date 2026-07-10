package org.example.game.core

class ConwayRule : Rule {
    override fun nextState(state: CellState, neighbors: Int): CellState {
        return if (neighbors == 3 || (neighbors == 2 && state == CellState.ALIVE)) {
            CellState.ALIVE
        } else {
            CellState.DEAD
        }
    }
}
