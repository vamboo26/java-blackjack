package com.codesquad.blackjack.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ChipTest {
    private Chip chip;

    @Before
    public void setUp() throws Exception {
        chip = Chip.of(1000);
    }

    @Test
    public void halfTest() {
        assertThat(chip.half()).isEqualTo(Chip.of(500));
    }

    @Test
    public void sumTest() {
        assertThat(chip.sum(Chip.of(500))).isEqualTo(Chip.of(1500));
    }

    @Test
    public void blackjackTest() {
        assertThat(chip.blackjack()).isEqualTo(Chip.of(1500));
    }
}