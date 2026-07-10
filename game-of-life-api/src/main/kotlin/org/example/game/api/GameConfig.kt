package org.example.game.api.org.example.game.api

import org.example.game.api.BoardMapper
import org.example.game.application.CalculateNextStepUseCase
import org.example.game.application.GameEngineService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//TODO check if is is correct. Purpose - keep spring only in this module, application module has no spring dependency
@Configuration
open class GameConfig {

    @Bean
    open fun calculateNextStepUseCase(): CalculateNextStepUseCase {
        return GameEngineService()
    }

    @Bean
    open fun boardMapper(): BoardMapper {
        return BoardMapper()
    }

}