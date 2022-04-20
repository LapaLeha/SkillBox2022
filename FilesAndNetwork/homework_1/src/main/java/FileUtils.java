import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    private static boolean error = false;

    public static boolean isError() {
        return error;
    }

    public static long calculateFolderSize(String path) {
        error = false;
        long result = 0;
        try {
            final File folder = new File(path);
            for (final File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    result += calculateFolderSize(file.getAbsolutePath());
                } else {
                    result += Files.size(Path.of(file.getPath()));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            error = true;
        }
        return result;
    }
}
