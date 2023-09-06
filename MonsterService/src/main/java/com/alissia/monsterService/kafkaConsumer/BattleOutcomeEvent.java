package com.alissia.monsterService.kafkaConsumer;

import com.alissia.monsterService.enums.BattleOutcome;

public record BattleOutcomeEvent(Long monsterId, BattleOutcome outcome) {
}
