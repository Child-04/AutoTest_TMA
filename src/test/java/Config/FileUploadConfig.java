package Config;

import java.nio.file.Paths;

public class FileUploadConfig {
    private final String fileName;
    private final String relativePath;

    public FileUploadConfig(String fileName, String relativePath) {
        this.fileName = fileName;
        this.relativePath = relativePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFullPath() {
        return Paths.get(System.getProperty("user.dir"), relativePath, fileName).toAbsolutePath().toString();
    }

}