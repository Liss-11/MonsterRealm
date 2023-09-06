package com.alissia.heroService.kafkaProducer;

import com.alissia.heroService.enums.BattleOutcome;

public record BattleOutcomeEvent(Long monsterId, BattleOutcome outcome) { }

