package com.sitronics.test.mandate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main application entry point.
 */
public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    private static final int TEST_ITERATIONS = 1000;
    private static final int LOWEST_MANDATE = 0;
    private static final int HIGHEST_MANDATE = 2;

    /**
     * Main function for app starts.
     *
     * @param args console arguments.
     */
    public static void main(String[] args) {
        int passedMandateIterations = 1;

        try {
            for (; passedMandateIterations <= TEST_ITERATIONS; passedMandateIterations++) {
                String level = Mandate.getMandate();
                int levelAsInt = Integer.parseInt(level);

                if (levelAsInt < LOWEST_MANDATE || levelAsInt > HIGHEST_MANDATE) {
                    break;
                }
            }
        } catch (Throwable e) {
            log.error(e);
        }

        if (passedMandateIterations == TEST_ITERATIONS) {
            log.info("All iterations successfully passed!");
        } else {
            log.error("Broken on the " + passedMandateIterations + " iteration.");
        }
    }
}
