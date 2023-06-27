package com.sitronics.test.mandate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mandate {
    private static final Logger log = LogManager.getLogger(Mandate.class);
    private static MandateType mandateType = null;

    private static MandateType getMandateType() {
        if (null == mandateType) {
            try {
                new LinuxBinding();
                mandateType = MandateType.LINUX;
            } catch (Exception e) {
            }
        }
        if (null == mandateType) {
            try {
                new WindowsBinding();
                mandateType = MandateType.WINDOWS;
            } catch (Exception e) {
            }
        }
        if (null == mandateType) {
            mandateType = MandateType.NONE;
        }
        return mandateType;
    }

    private static String getLevel() {
        String level = "0";
        if (MandateType.LINUX == getMandateType()) {
            LinuxBinding lb = new LinuxBinding();
            level = lb.getLinuxLevel();
        } else if (MandateType.WINDOWS == getMandateType()) {
            WindowsBinding wb = new WindowsBinding();
            level = wb.getWindowsLevel();
        }
        return level;
    }

    // Main method
    public static String getMandate() {
        String level;
        switch (Mandate.getMandateType()) {
            case LINUX:
            case WINDOWS:
                log.info("The mechanism of mandate levels has been found " + Mandate.getMandateType());
                level = Mandate.getLevel();
                log.info("The mandate level of the process has been determined: " + level);
                break;
            default:
                log.error("The mandate level of the process could not be determined.");
                level = "0";
                break;
        }
        mandateType = null;
        return level;
    }
}
