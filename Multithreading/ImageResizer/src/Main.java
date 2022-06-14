import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:/Users/user/Desktop/test1";
        String dstFolder = "C:/Users/user/Desktop/test2";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int sizeThread = files.length / 6;

        File[] files1 = new File[sizeThread];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageSize imageSize1 = new ImageSize(files1,newWidth,dstFolder,start);
        imageSize1.start();

        File[] files2 = new File[sizeThread];
        System.arraycopy(files, sizeThread, files2, 0, files2.length);
        ImageSize imageSize2 = new ImageSize(files2,newWidth,dstFolder,start);
        imageSize2.start();

        File[] files3 = new File[sizeThread];
        System.arraycopy(files, sizeThread*2, files3, 0, files3.length);
        ImageSize imageSize3 = new ImageSize(files3,newWidth,dstFolder,start);
        imageSize3.start();

        File[] files4 = new File[sizeThread];
        System.arraycopy(files, sizeThread*3, files4, 0, files4.length);
        ImageSize imageSize4 = new ImageSize(files4,newWidth,dstFolder,start);
        imageSize4.start();

        File[] files5 = new File[sizeThread];
        System.arraycopy(files, sizeThread*4, files5, 0, files5.length);
        ImageSize imageSize5 = new ImageSize(files5,newWidth,dstFolder,start);
        imageSize5.start();

        File[] files6 = new File[files.length - sizeThread*5];
        System.arraycopy(files, sizeThread*5, files6, 0, files.length-sizeThread*5);
        ImageSize imageSize6 = new ImageSize(files6,newWidth,dstFolder,start);
        imageSize6.start();

    }
}
