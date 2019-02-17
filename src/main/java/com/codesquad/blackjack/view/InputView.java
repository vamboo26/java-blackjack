package com.codesquad.blackjack.view;

import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static int inputBettingChip() {
        System.out.println("*** 배팅할 칩의 개수를 입력해주세요.");
        return sc.nextInt();
    }

    public static boolean isContinue() {
        System.out.println("*** 1. continue  2. quit");
        return sc.nextInt() == 1;
    }

    public static int isDouble() {
        System.out.println("*** 1. hit       2. stand        3. double");
        return sc.nextInt();
    }

    public static int isHit() {
        System.out.println("*** 1. hit       2. stand");
        return sc.nextInt();
    }

    public static String inputPlayerName() {
        System.out.println("*** 플레이어의 이름을 입력해주세요.");
        return sc.nextLine();
    }
}
