package aggregationData;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Thread has resp for pars each elements by url link below to general json data.
 */
public class JsonAggregationThread implements Callable<String> {
    private BlockingQueue<ParsInfo> queue;
    private BlockingQueue<String> queueSave;
    private JsonParsThread thread;

    /**
     * @param queue     - put queue blocking for pars elements in general json data.
     * @param queueSave - put queue blocking string parsed elements from queue data.
     */

    public JsonAggregationThread(BlockingQueue<ParsInfo> queue, BlockingQueue<String> queueSave, JsonParsThread thread) {
        this.queue = queue;
        this.queueSave = queueSave;
        this.thread = thread;
    }

    @Override
    public String call() {
        initAggreation();
        return Thread.currentThread().getName();
    }

    public void cancle(){
        System.out.println(Thread.currentThread().getName() + "  interupted");
        queueSave.add("stop");
        Thread.currentThread().interrupt();
    }

    public boolean isAlive(){
        return Thread.currentThread().isInterrupted();
    }

    public void initAggreation() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                ParsInfo parsInfo = queue.take();
                ObjectMapper objectMapper = new ObjectMapper();
                URL url1 = new URL(parsInfo.getSourceDataUrl());
                URL url2 = new URL(parsInfo.getTokenDataUrl());
                CameraUrlData cameraUrlData = objectMapper.readValue(url1, CameraUrlData.class);
                CameraValueData cameraValueData = objectMapper.readValue(url2, CameraValueData.class);
                Camera camera = new Camera();
                camera.setId(parsInfo.getId());
                camera.setTtl(cameraValueData.getTtl());
                camera.setValue(cameraValueData.getValue());
                camera.setUrlType(cameraUrlData.getUrlType());
                camera.setVideoUrl(cameraUrlData.getVideoUrl());
                String json = objectMapper.writeValueAsString(camera);
                queueSave.add(json);
                if (queue.isEmpty() && !thread.isAlive()) {
                    cancle();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
