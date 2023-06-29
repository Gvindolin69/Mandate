package com.sitronics.test.mandate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mandate {
    private static final Logger log = LogManager.getLogger(Mandate.class);
    private static MandateType mandateType = null;
    private static final int[] digits = new int[]{1, 2};


    private static MandateType getMandateType() {
        int n = (int)Math.floor(Math.random() * digits.length);
        switch (n){
            case 1:
                try {
                    new LinuxBinding();
                    mandateType = MandateType.LINUX;
                    return mandateType;
                } catch (Exception e) {
                    log.error(e);
                }
            case 2:
                try {
                    new WindowsBinding();
                    mandateType = MandateType.WINDOWS;
                    return mandateType;
                } catch (Exception e) {
                    log.error(e);
                }
            default:
                mandateType = MandateType.NONE;
                return mandateType;
        }
    }

    private static String getLevel() {
        try{
            if (MandateType.LINUX.equals(getMandateType())) {
                LinuxBinding lb = new LinuxBinding();
                return lb.getLinuxLevel();
            } else if (MandateType.WINDOWS.equals(getMandateType())) {
                WindowsBinding wb = new WindowsBinding();
                return wb.getWindowsLevel();
            }
        }catch (UnsatisfiedLinkError error) {
            log.error(error);
        }

        return "0";
    }

    // Main method
    public static String getMandate() {
        if(!Mandate.getMandateType().equals(MandateType.NONE)) {
            return changedLevel();
        }

        log.error("The mandate level of the process could not be determined.");

        mandateType = null;
        return "0";
    }

    private static String changedLevel() {
        log.info("The mechanism of mandate levels has been found " + Mandate.getMandateType());
        String level = Mandate.getLevel();
        log.info("The mandate level of the process has been determined: " + level);

        return level;
    }
}
