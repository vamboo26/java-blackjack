package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;

public class UserDto {
    private String name;
    private Chip chip;

    public UserDto(String name, Chip chip) {
        this.name = name;
        this.chip = chip;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", chip=" + chip +
                '}';
    }
}
