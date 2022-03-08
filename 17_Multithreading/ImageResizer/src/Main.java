import java.io.File;

public class Main {

    private static final int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "/Users/markguranov/Downloads/src";
        String dstFolder = "/Users/markguranov/Downloads/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        assert files != null;
        int middle = files.length / 2;

        File[] files1 = new File[middle];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder);
        Thread thread1 = new Thread(resizer1);

        File[] files2 = new File[files.length - middle];
        System.arraycopy(files, middle, files2, 0, files2.length);
        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder);
        Thread thread2 = new Thread(resizer2);

        thread1.start();
        thread2.start();

//        new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                System.out.println(i);
//            }
//        }).start();

        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        System.out.println("Count of objects in src folder: " + files.length);
    }
}
