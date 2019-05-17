package com.codesquad.blackjack.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ChipTest {
    public Chip CHIP_1000;

    @Before
    public void setUp() throws Exception {
        CHIP_1000 = new Chip(1000);
    }

    @Test
    public void sumTest() {
        assertThat(CHIP_1000.sum(new Chip(500))).isEqualTo(new Chip(1500));
    }

    @Test
    public void blackjackTest() {
        assertThat(CHIP_1000.blackjack()).isEqualTo(new Chip(2500));
    }

}