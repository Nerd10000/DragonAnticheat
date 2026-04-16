package ac.dragon.utils;

import java.security.SecureRandom;

public class TransactionUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static long generateNonce() {
        return secureRandom.nextLong();
    }
}
