package com.gabriel.backend_challenge.testUtil;

import com.gabriel.backend_challenge.entity.enuns.RoleEnum;
import com.github.javafaker.Faker;

import java.util.Random;

public class TestUtil {


    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    public static int generateRandomPrime(int min, int max) {
        int num;
        do {
            num = generateRandomNumber(min, max);
        } while (!isPrime(num));
        return num;
    }

    private static int generateRandomNumber(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static RoleEnum generateRandomRole() {
        RoleEnum[] roles = RoleEnum.values();
        return roles[RANDOM.nextInt(roles.length)];
    }

    public static String generateRandomName() {
        String fullName;
        do {
            fullName = FAKER.name().fullName();
        } while (containsNumber(fullName));
        return fullName;
    }

    private static boolean containsNumber(String input) {
        return input.matches(".*\\d.*");
    }

}