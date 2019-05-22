package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class User implements Player {

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

    @Transient
    private Cards cards = new Cards();

    public User() {}

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

        //TODO
        // 폼에서 데이터 던질 때, 유저객체로 받아서 데이터바인딩하는데
        // 어떻게 setter없이 로직구현할지 고민하기
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

    public boolean isGuestUser() {
        return false;
    }

    @Override
    public void initialize() {
        this.cards = new Cards();
    }

    @Override
    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public boolean isBurst() {
        return this.cards.isBurst();
    }

    @Override
    public boolean isBlackjack() {
        return this.cards.isBlackjack();
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }

}