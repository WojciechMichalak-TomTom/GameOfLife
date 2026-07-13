package org.example.game.api

import org.example.game.core.ConwayRule
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/game")
class GameController {

    private val boardMapper = BoardMapper()
    private val conwayRule = ConwayRule()

    @CrossOrigin(origins = ["http://localhost:5173"])
    @PostMapping("/next-step")
    fun next(@RequestBody boardDTO: BoardDTO): ResponseEntity<BoardDTO> {
        val board = boardMapper.toEntity(boardDTO, conwayRule)
        val nextBoard = board.nextStep()
        return ResponseEntity.ok(boardMapper.toDto(nextBoard))
    }

}