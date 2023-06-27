package com.sitronics.test.mandate;

import java.util.Random;

/**
 * Please do not change this class!
 * This class is used for test.
 */
public class TestLibrary {
    private static final int MANDATE_EXTRA_UPPER_BOUND = 7;
    private static final int MANDATE_EXTRA_LOWER_BOUND = -1;
    private static final int EXCEPTION_BOUND = 4;
    private static final int NULL_BOUND = 5;
    private static final int GETCON_PARAMS = 4;
    private static final String GETCON_SEPARATOR = ":";
    private final Random random;

    /**
     * Default constructor.
     *
     * @throws RuntimeException If something goes wrong.
     */
    public TestLibrary() throws RuntimeException {
        random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("An error occurred during library installation.");
        }
    }

    /**
     * Mandate obtaining method from the Linux library.
     *
     * @return Integer as string, which is the pure mandate level.
     * @throws UnsatisfiedLinkError If something inside the library goes wrong.
     */
    public String getmac() throws UnsatisfiedLinkError {
        return getMandateLevel();
    }

    /**
     * Mandate obtaining method from the Linux library.
     *
     * @return string in the N:N:N:N format, where the first number is a level value.
     * @throws UnsatisfiedLinkError If something inside the library goes wrong.
     */
    public String getcon() throws UnsatisfiedLinkError {
        String mandateLevel = getMandateLevel();
        if (mandateLevel == null) {
            return null;
        }
        return buildGetconMandateLevel(mandateLevel);
    }

    private String getMandateLevel() throws UnsatisfiedLinkError {
        int mandateLevel = random.nextInt(MANDATE_EXTRA_UPPER_BOUND) + MANDATE_EXTRA_LOWER_BOUND;
        if (mandateLevel == EXCEPTION_BOUND) {
            throw new UnsatisfiedLinkError("Library cannot find mandate level.");
        }
        if (mandateLevel == NULL_BOUND) {
            return null;
        }
        return String.valueOf(mandateLevel);
    }

    private String buildGetconMandateLevel(String mandateLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(mandateLevel);
        for (int i = 1; i < GETCON_PARAMS; i++) {
            sb.append(GETCON_SEPARATOR);
            sb.append(random.nextInt());
        }
        return sb.toString();
    }
}
