package aggregationData;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class JsonAggregationThreadTest {

    @Test
    public void testThreadParsGeneral() throws Exception {
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        Thread threadPut = new Thread(new JsonParsThread(queue));
        threadPut.start();
        threadPut.join();
        Assert.assertEquals(queue.size(), 4);
    }

    @Test
    public void testThreadParsElement() throws Exception {
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        BlockingQueue<String> queueSave = new PriorityBlockingQueue<>();
        JsonParsThread jsonParsThread = new JsonParsThread(queue);
        Thread threadPut = new Thread(jsonParsThread);
        threadPut.start();
        threadPut.join();
        Thread threadPars = new Thread(new JsonAggregationThread(queue, queueSave, jsonParsThread));
        threadPars.start();
        threadPars.join();
        Assert.assertEquals(queue.size(), 0);
        Assert.assertEquals(queueSave.size(), 5);
    }
}