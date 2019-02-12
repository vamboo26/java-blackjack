package com.codesquad.blackjack.view;

import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static boolean isContinue() {
        System.out.println("1. continue  2. quit");
        return sc.nextInt() == 1;
    }
}
