package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import lombok.*;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Player {

    public static final GuestUser GUEST_USER = new GuestUser();
    private static final int DEFAULT_CHIP_AMOUNT = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Embedded
    private Chip chip = new Chip(DEFAULT_CHIP_AMOUNT);

    @Transient
    private Cards cards = new Cards();

    @Builder
    public User(String userId, String password, String name) {
        checkNotNull(userId);
        checkNotNull(password);
        checkNotNull(name);
        checkArgument(2 <= userId.length() && userId.length() <= 10,
                "userId must be between 2 and 10 characters");
        checkArgument(2 <= password.length() && password.length() <= 15,
                "password must be between 2 and 15 characters");
        checkArgument(2 <= name.length() && name.length() <= 15,
                "name must be between 2 and 15 characters");

        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public int getAmount() {
        return chip.getAmount();
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