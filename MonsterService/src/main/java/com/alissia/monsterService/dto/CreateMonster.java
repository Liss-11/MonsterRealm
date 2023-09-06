package com.alissia.monsterService.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateMonster(
        @NotBlank(message = "Name can't be blank") String name,
        @NotBlank(message = "Type can't be blank") String type
) { }
