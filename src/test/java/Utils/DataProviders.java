package Utils;

import org.testng.annotations.DataProvider;

import java.nio.file.Paths;

public class DataProviders {
    @DataProvider(name = "credentialCsvProvider")
    public static Object[][] credentialCsvProvider() throws Exception {
        var creds = CredentialUtils.readCredentialsCsv(Paths.get("src/test/resources/files/users.csv"));
        Object[][] out = new Object[creds.size()][2];
        for (int i = 0; i < creds.size(); i++) {
            out[i][0] = creds.get(i)[0];
            out[i][1] = creds.get(i)[1];
        }
        return out;
    }
}
