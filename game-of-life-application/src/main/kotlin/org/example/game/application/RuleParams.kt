package org.example.game.application

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

enum class RuleType {
    BS_RULE
}

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = RuleParams.BSRule::class, name = "BS_RULE")
)
sealed class RuleParams(val type: RuleType) {
    data class BSRule(
        val birthConditionValues: Set<Int>,
        val survivesConditionValues: Set<Int>
    ) : RuleParams(RuleType.BS_RULE)
}