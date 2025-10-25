package Utils.security;

import java.util.Base64;

public class SecurityUtil {
    public static String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String decode(String encoded) {
        try {
            return new String(Base64.getDecoder().decode(encoded));
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to decode text: " + e.getMessage());
            return encoded;
        }
    }
}
