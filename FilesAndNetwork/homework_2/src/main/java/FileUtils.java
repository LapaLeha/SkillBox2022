import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory){

        try {
            final File folder = new File(sourceDirectory);
            for (final File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    Files.createDirectory(Path.of(destinationDirectory + "/" + file.getName()));
                    copyFolder(file.getPath(), (destinationDirectory + "/" + file.getName()));
                } else if (file.isFile()) {
                    byte[] fileCopy = Files.readAllBytes(file.toPath());
                    FileOutputStream fileOutputStream = new FileOutputStream(destinationDirectory+
                            "/"+file.getName());
                    fileOutputStream.write(fileCopy);
                    fileOutputStream.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
