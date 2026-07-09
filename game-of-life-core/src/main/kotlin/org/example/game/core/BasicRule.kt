package org.example.game.core

class ConwayRule : Rule {
    override fun nextState(state: BasicCellState, neighbors: Int): BasicCellState {
        return if (neighbors == 3 || (neighbors == 2 && state == BasicCellState.ALIVE)) {
            BasicCellState.ALIVE
        } else {
            BasicCellState.DEAD
        }
    }

}

interface Rule {
    fun nextState(state: BasicCellState, neighbors: Int): BasicCellState
}