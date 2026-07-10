package org.example.game.core

interface Rule {
    fun nextState(state: CellState, neighbors: Int): CellState
}