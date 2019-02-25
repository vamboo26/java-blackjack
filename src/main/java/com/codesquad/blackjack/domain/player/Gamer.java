package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Gamer extends AbstractPlayer {
    private static final int DEFAULT_CHIP_AMOUNT = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false, length = 20)
    private String userId;

    @Size(min = 6, max = 20)
    @Column(nullable = false, length = 20)
    private String password;

    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @OneToOne
//    @JoinColumn(foreignKey = @ForeignKey(name = "fk_gamer_chip"))
    private Chip chip = new Chip(DEFAULT_CHIP_AMOUNT);

    public Gamer(String name) {
        super(name);
    }

    public Gamer(String name, Chip chip) {
        super(name);
        this.chip = chip;
    }

    @Override
    public Gamer initialize() {
        return new Gamer(getName(), chip);
    }

    @Override
    public UserDto _toUserDto() {
        return new UserDto(getName(), getCardsDto(), this.chip);
    }

    public void betChip(int bettingChip) {
        this.chip = chip.subtract(bettingChip);
    }

    public void winPrize(Chip prize) {
        this.chip = chip.sum(prize);
    }

    public boolean checkChip(int bettingChip) {
        return this.chip.isOver(bettingChip);
    }

    public boolean isBankruptcy() {
        return this.chip.isZero();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }
}