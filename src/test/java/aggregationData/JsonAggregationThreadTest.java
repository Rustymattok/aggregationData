package aggregationData;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class JsonAggregationThreadTest {

    @Test
    public void testThreadParsGeneral() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        JsonParsThread jsonParsThread =new JsonParsThread(queue);
        FutureTask<String> futureTask1 = new FutureTask<String>(jsonParsThread);
        exec.execute(futureTask1);
        while (true) {
            if(futureTask1.isDone()){
                System.out.println("Done");
                // заканчиваем работу executor service
                exec.shutdown();
                break;
            }
        }
        Assert.assertEquals(queue.size(), 4);
    }

    @Test
    public void testThreadParsElement() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        BlockingQueue<String> queueSave = new PriorityBlockingQueue<>();
        JsonParsThread jsonParsThread = new JsonParsThread(queue);
        JsonAggregationThread jsonAggregationThread = new JsonAggregationThread(queue, queueSave, jsonParsThread);
        FutureTask<String> futureTask2 = new FutureTask<String>(jsonAggregationThread);
        FutureTask<String> futureTask1 = new FutureTask<String>(jsonParsThread);
        exec.execute(futureTask1);
        exec.execute(futureTask2);
        while (true) {
            if(futureTask1.isDone() && futureTask2.isDone()){
                System.out.println("Done");
                exec.shutdown();
                break;
            }
        }
        Assert.assertEquals(queue.size(), 0);
        Assert.assertEquals(queueSave.size(), 5);
    }
}