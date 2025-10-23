package Utils;

import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserContext;
import java.nio.file.Paths;

public class TraceUtil {

    public static void startTrace(BrowserContext context) {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    public static void stopTrace(BrowserContext context, String testName, boolean isFailed) {
        if (isFailed) {
            String tracePath = "src/test/resources/trace/" + testName + ".zip";
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(tracePath)));
            System.out.println("Trace saved to: " + tracePath);
        } else {
            context.tracing().stop();
        }
    }
}
