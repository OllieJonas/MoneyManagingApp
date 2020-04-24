package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Util class to generate states (extra bits of authentication for Monzo). {@link MonzoHttpClient}
 */
public class StateGenerator {

    /**
     * Secure random, used for generating random state.
     */
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates the state.
     *
     * @return A random number as a String used as the state.
     */
    public static String generate() {
        return new BigInteger(130, random).toString(32);
    }
}
