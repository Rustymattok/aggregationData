package aggregationData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Thread has resp for save pars data to file.
 */
public class FileInputThread implements Runnable {
    private BlockingQueue<String> queueSave;

    /**
     * @param queueSave - element for save.
     */
    public FileInputThread(BlockingQueue<String> queueSave) {
        this.queueSave = queueSave;
    }


    public void cancle() {
        System.out.println(Thread.currentThread().getName() + "  interupted");
        Thread.currentThread().interrupt();
    }

    public boolean isAlive() {
        return Thread.currentThread().isInterrupted();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            FileWriter fileWriter = null;
            String json = null;
            try {
                fileWriter = new FileWriter("D:\\CODE_WORK\\aggregationData\\src\\main\\java\\aggregationData\\note.txt", true);
                json = queueSave.take();
                if (json.equals("stop")) {
                    cancle();
                    return;
                }
                StringBuilder stringBuilderJson = new StringBuilder(json).append("\n");
                fileWriter.write(String.valueOf(stringBuilderJson));
                fileWriter.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
