package org.example.game.api

import org.example.game.application.CalculateNextStepUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/game")
class GameController (
    private val calculateNextStepUseCase: CalculateNextStepUseCase,
    private val boardMapper: BoardMapper
    ){

    @CrossOrigin(origins = ["http://localhost:5173"])
    @PostMapping("/next-step")
    fun next(@RequestBody boardDTO: BoardDTO,
             @RequestParam(defaultValue = "CONWAY") ruleName: String
             ): ResponseEntity<BoardDTO> {
        val currentBoard = boardMapper.toEntity(boardDTO)

        val nextBoard = calculateNextStepUseCase.calculateNextStep(currentBoard, ruleName)

        return ResponseEntity.ok(boardMapper.toDto(nextBoard))
    }

}