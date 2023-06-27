package com.sitronics.test.mandate;

public class WindowsBinding {
    /**
     * Get the mandate level of the Windows application.
     *
     * @return Mandate level as string: 0 or 1 or 2.
     * @throws UnsatisfiedLinkError If the library cannot determine the level of authority.
     */
    public String getWindowsLevel() throws UnsatisfiedLinkError {
        final String defaultLevel = "0";

        String level = new TestLibrary().getmac();

        if (null != level) {
            return level;
        }

        return defaultLevel;

    }

}
