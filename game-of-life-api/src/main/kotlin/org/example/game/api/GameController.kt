package org.example.game.api

import org.example.game.application.BoardRequestDTO
import org.example.game.application.BoardResponseDTO
import org.example.game.application.CalculateNextStepUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController (
    private val calculateNextStepUseCase: CalculateNextStepUseCase
    ){

    @CrossOrigin(origins = ["http://localhost:5173"])
    @PostMapping("/next-step")
    fun next(
        @RequestBody boardDTO: BoardRequestDTO
    ): ResponseEntity<BoardResponseDTO> {


        return ResponseEntity.ok(calculateNextStepUseCase.calculateNextStep(boardDTO))
    }

}