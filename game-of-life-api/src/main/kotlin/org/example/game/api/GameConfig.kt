package org.example.game.api.org.example.game.api

import org.example.game.application.CalculateNextStepUseCase
import org.example.game.application.GameEngineService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GameConfig {
    @Bean
    open fun calculateNextStepUseCase(): CalculateNextStepUseCase {
        return GameEngineService()
    }
}