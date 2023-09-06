package com.alissia.heroService.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateHero(
        @NotBlank(message = "Name must not be blank") String name,
        @NotBlank(message = "Ability must not be blank") String ability
) { }
