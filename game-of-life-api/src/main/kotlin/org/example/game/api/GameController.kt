package org.example.game.api

import org.example.game.application.BoardRequestDTO
import org.example.game.application.BoardResponseDTO
import org.example.game.application.CalculateNextStepUseCase
import org.example.game.application.GenerateRandomBoardUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = ["http://localhost:5173"])
class GameController (
    private val calculateNextStepUseCase: CalculateNextStepUseCase,
    private val generateRandomBoardUseCase: GenerateRandomBoardUseCase
    ){

    @PostMapping("/next-step")
    fun next(
        @RequestBody boardDTO: BoardRequestDTO
    ): ResponseEntity<BoardResponseDTO> {


        return ResponseEntity.ok(calculateNextStepUseCase.calculateNextStep(boardDTO))
    }

    @GetMapping("/random")
    fun getRandomBoard(
        @RequestParam(defaultValue = "10") width: Int,
        @RequestParam(defaultValue = "10") height: Int
    ): ResponseEntity<BoardResponseDTO> {

        val randomBoard = generateRandomBoardUseCase.generateRandom(width, height)
        return ResponseEntity.ok(randomBoard)
    }

}