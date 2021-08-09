package LocalUtils;

import java.util.Locale;

public class OSDetection extends UtilsBaseClass {

    public static final class OsCheck {
        /**
         * types of Operating Systems
         */
        public enum OSType {
            Windows, MacOS, Linux, Other
        }

        // cached result of OS detection
        public static OSType detectedOS;

        // cached result of Base File Path depending on operating System
        public static String baseFilePath;

        // cached result of Base File Path depending on operating System
        public static String baseDownloadPath;

        static String getBaseFilePath(){
            return baseFilePath;
        }


        /**
         * 1. Determine what type of System the Test suite is running on (linux/windows), set a baseFilepath accordingly
         * 2. Set a baseDownloadPath, if any files get downloaded during a test they will be routed to this folder.
         */
        static void setBaseFilePath(){
            if (detectedOS == OSType.Windows) {
                baseFilePath = System.getProperty("user.dir");
            } else if (detectedOS == OSType.Linux) {
                baseFilePath = System.getenv("WORKSPACE");
            }

            baseDownloadPath = baseFilePath + "/GeneratedFiles/FileDownloads";
        }

        /**
         * detect the operating system from the os.name System property and cache
         * the result
         *
         * @return OSType - the operating system detected
         */
        public static OSType getOperatingSystemType() {
            if (detectedOS == null) {
                String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
                if ((OS.contains("mac")) || (OS.contains("darwin"))) {
                    detectedOS = OSType.MacOS;
                } else if (OS.contains("win")) {
                    detectedOS = OSType.Windows;
                } else if (OS.contains("nux")) {
                    detectedOS = OSType.Linux;
                } else {
                    detectedOS = OSType.Other;
                }
            }
            return detectedOS;
        }
    }

}
