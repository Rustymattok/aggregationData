package aggregationData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Application for pars url and sorted info to other file.
 */
public class AppAggregation {
    public static void main(String[] args) {
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        BlockingQueue<String> queueSave = new PriorityBlockingQueue<>();
        JsonParsThread jsonParsThread = new JsonParsThread(queue);
        JsonAggregationThread jsonAggregationThread = new JsonAggregationThread(queue,queueSave,jsonParsThread);
        FileInputThread fileInputThread = new FileInputThread(queueSave);
        Thread threadPut = new Thread(jsonParsThread);
        Thread threadPars = new Thread(jsonAggregationThread);
        Thread threadSave = new Thread(fileInputThread);
        threadPars.start();
        threadSave.start();
        threadPut.start();
    }
}
