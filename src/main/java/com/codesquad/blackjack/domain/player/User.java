package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.MessageType;
import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.dto.ChatDto;
import com.codesquad.blackjack.dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Entity
public class User extends AbstractPlayer {

    public static final GuestUser GUEST_USER = new GuestUser();
    private static final int DEFAULT_CHIP_AMOUNT = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false, length = 20)
    private String userId;

    @Size(min = 4, max = 20)
    @Column(nullable = false, length = 20)
    private String password;

    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @Embedded
    private Chip chip = new Chip(DEFAULT_CHIP_AMOUNT);

    public User() {
    }

    public User(String name) {
        super(name);
    }

    public User(String name, Chip chip) {
        super(name);
        this.chip = chip;
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

    public void update(User loginUser, User target) {
        if (!matchUserId(loginUser.userId)) {
            throw new RuntimeException();
        }

        if (!matchPassword(target.password)) {
            throw new RuntimeException();
        }

        this.name = target.name;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    private boolean matchUserId(String userId) {
        return this.userId.equals(userId);
    }

    public UserDto _toUserDto(MessageType type) {
        return new UserDto(type, getName(), getCardsDto(), this.chip);
    }

    public ChatDto _toChatDto(String type) {
        return new ChatDto(type, this.name);
    }

    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }

}