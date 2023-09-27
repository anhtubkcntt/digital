package com.digital.shop.utils;

import java.util.Random;

public class CommonUtils {

    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999999);
        return String.format("%07d", number);
    }
}
