package Utils; // 👈 chỉnh lại package cho đúng với cấu trúc project của bạn

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllureCommandRunner {

    public static void runCommand(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("cmd.exe", "/c", command);
            builder.redirectErrorStream(true);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\n➡️ Command exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
