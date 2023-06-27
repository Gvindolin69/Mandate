package com.sitronics.test.mandate;

public class LinuxBinding {
    /**
     * Get the mandate level of the Linux application.
     *
     * @return Mandate level as string: 0 or 1 or 2.
     * @throws UnsatisfiedLinkError If the library cannot determine the level of authority.
     */
    public String getLinuxLevel() throws UnsatisfiedLinkError {
        final String defaultLevel = "0";

        String context = new TestLibrary().getcon();

        if (null != context) {
            String[] parts = context.split(":");
            if (parts.length >= 4) {
                return parts[0].toLowerCase();
            }
        }

        return defaultLevel;

    }

}
