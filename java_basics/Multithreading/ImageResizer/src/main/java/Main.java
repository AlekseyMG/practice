import java.io.File;

public class Main {
    public static int newWidth = 300;
    public static void main(String[] args) {
        String srcFolder = "./images/src";
        String dstFolder = "./images/dst";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int cores = 6;
        int additionalFiles = files.length % cores;
        int filesPerCore = files.length / cores;
        int filesInProcess = 0;
        int additionalFile = 0;
        long start = System.currentTimeMillis();

        for (int i = 0; i < cores; i++) {
            if (additionalFiles > 0) {
                additionalFile = 1;
            } else {
                additionalFile = 0;
            }
            File[] files1 = new File[filesPerCore + additionalFile];
            System.arraycopy(files, filesInProcess, files1, 0, files1.length);
            ImageResizer imageResizer = new ImageResizer(files1, newWidth, dstFolder, start);
            filesInProcess = filesInProcess + filesPerCore + additionalFile;
            additionalFiles = additionalFiles - additionalFile;
            imageResizer.start();
        }
    }
}
