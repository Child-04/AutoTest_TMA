package Utils;

import Config.FileUploadConfig;

public class TestData {
    // Base URL (có thể đọc từ file config)
    public static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";

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

    //Text input
    public static final String NAME = "Tuyet Nhi";
    public static final String EMAIL = "nhint@example.com";
    public static final String CURRENT_ADDRESS = "123 Le Loi\nDistrict 1\n HCMC";
    public static final String PERMANENT_ADDRESS = "456 Tran Hung Dao\nDistrict 5\nHCMC";

    //File

    public static final FileUploadConfig SAMPLE_FILE = new FileUploadConfig("example.txt", "src/test/resources/files");
    public static final FileUploadConfig IMAGE_FILE = new FileUploadConfig("image.png", "src/test/resources/files");


}
