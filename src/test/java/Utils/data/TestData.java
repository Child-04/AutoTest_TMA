package Utils.data;

import Config.FileUploadConfig;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class TestData {
    // Base URL
    public static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";
    public static Path BASE_PATH = Paths.get("src/test/resources/files");
    public static Path DOWNLOAD_PATH = Paths.get("src/test/resources/files/downloadFile");

    // Common credentials
    public static final String VALID_USERNAME = "Admin";
    public static final String INVALID_USERNAME = "wrongName";
    public static final String VALID_PASSWORD = "admin123";
    public static final String INVALID_PASSWORD = "wrongPassword";

    //header table
    public static final String USERNAME = "Username";
    public static final String USER_ROLE = "User Role";
    public static final String EMPLOYEE_NAME = "Employee Name";
    public static final String STATUS = "Status";
    // Dropdown values
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_ESS = "ESS";
    public static final String STATUS_ENABLED = "Enabled";
    public static final String STATUS_DISABLED = "Disabled";

    // Employee Password
    public static final String  EMPLOYEEPASSWORD = "employee123";

    //Text input
    public static final String NAME = "Tuyet Nhi";
    public static final String EMAIL = "nhint@example.com";
    public static final String CURRENT_ADDRESS = "123 Le Loi\nDistrict 1\n HCMC";
    public static final String PERMANENT_ADDRESS = "456 Tran Hung Dao\nDistrict 5\nHCMC";

    //File

    public static final FileUploadConfig SAMPLE_FILE = new FileUploadConfig("example.txt", "src/test/resources/files/uploadFile");
    public static final FileUploadConfig IMAGE_FILE = new FileUploadConfig("image.png", "src/test/resources/files/uploadFile");

    // Create Unique Name
    private static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    private static final String CHAR_POOL =
//            "abcdefghijklmnopqrstuvwxyz" +  // chữ thường
//            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +  // chữ hoa
            "0123456789"; //+                  // số
//            "!@#$%^&*()-_=+[]{};:,.<>?";    // ký tự đặc biệt

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomUserId(int length){
        if (length <= 0 || length > 10){
            throw new IllegalArgumentException("Độ dài phải trong khoảng 1 - 10 ký tự.");
        }
        StringBuilder userId = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHAR_POOL.length());
            userId.append(CHAR_POOL.charAt(index));
        }

        return userId.toString();
    }

    public static String generateUniqueFirstName() {
        return "FirstName_" + getTimestamp();
    }

    public static String generateUniqueLastName() {
        return "LastName_" + getTimestamp();
    }

    public static String generateUniqueUsername() {
        return "Username_" + getTimestamp();
    }

    public static String generateUniqueMiddleName(){
        return "Middle_" + getTimestamp();
    }

    //file name
    public static final String CREDENTIALS_FILE_NAME = "users.csv";

}
