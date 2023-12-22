package com.registrationform.util;

import java.util.Random;

public class GenerateCaptcha {
    public static int[] generateNums(){
        Random random = new Random();
        int num1 = random.nextInt(30);
        int num2 = random.nextInt(30);
        return new int[]{num1,num2};
    }
}
