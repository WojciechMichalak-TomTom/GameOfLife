package org.example.game.core


//BS represents born/survives which is classic mode
//B value - new cell is born when it has B neighbors
//S value - cell survives if it has S neighbors
class BSRule(
    private val birthCondition: Set<Int>,
    private val survivesCondition: Set<Int>
) : Rule {
    override fun nextState(state: CellState, neighbors: Int): CellState {

        return if (state == CellState.ALIVE) {
            if (survivesCondition.contains(neighbors)) {
                CellState.ALIVE
            } else {
                CellState.DEAD
            }
        } else {
            if (birthCondition.contains(neighbors)) {
                CellState.ALIVE
            } else {
                CellState.DEAD
            }
        }
    }
}
