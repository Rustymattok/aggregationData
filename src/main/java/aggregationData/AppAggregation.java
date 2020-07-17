package aggregationData;

import java.util.concurrent.*;

/**
 * Application for pars url and sorted info to other file.
 */
public class AppAggregation {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        BlockingQueue<String> queueSave = new PriorityBlockingQueue<>();
        JsonParsThread jsonParsThread = new JsonParsThread(queue);
        JsonAggregationThread jsonAggregationThread = new JsonAggregationThread(queue,queueSave,jsonParsThread);
        FileInputThread fileInputThread = new FileInputThread(queueSave);
        exec.submit(jsonParsThread);
        exec.submit(jsonAggregationThread);
        exec.submit(fileInputThread);
        exec.shutdown();
    }
}
