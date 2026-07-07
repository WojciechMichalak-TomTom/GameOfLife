package org.example.game

class ConwayRule : Rule {
    override fun next(state: BasicCellState, neighbors: Int): BasicCellState {
        return if (state == BasicCellState.DEAD) {
            if (neighbors == 3) {
                BasicCellState.ALIVE
            } else {
                BasicCellState.DEAD
            }
        } else {
            if (neighbors == 2 || neighbors == 3) {
                BasicCellState.ALIVE
            } else {
                BasicCellState.DEAD
            }
        }
    }

}

interface Rule {
    fun next(state: BasicCellState, neighbors: Int): BasicCellState
}