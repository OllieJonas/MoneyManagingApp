package me.csed2.moneymanager.utils;

import lombok.experimental.UtilityClass;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Util class to generate states (extra bits of authentication for Monzo). {@link MonzoHttpClient}
 */
@UtilityClass
public class StateGenerator {

    /**
     * Secure random, used for generating random state.
     */
    private final SecureRandom random = new SecureRandom();

    /**
     * Generates the state.
     *
     * @return A random number as a String used as the state.
     */
    public String generate() {
        return new BigInteger(130, random).toString(32);
    }
}
