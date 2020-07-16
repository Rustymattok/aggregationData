package aggregationData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

/**
 * Thread has resp for pars url link below to general json data.
 */
public class JsonParsThread implements Runnable {
    private BlockingQueue<ParsInfo> queue;

    /**
     * @param queue - pars elements.
     */
    public JsonParsThread(BlockingQueue<ParsInfo> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        parse();
    }

    public boolean isAlive(){
        return Thread.currentThread().isInterrupted();
    }

    public void cancle(){
        System.out.println(Thread.currentThread().getName() + "  interupted");
        Thread.currentThread().interrupt();
    }

    public void parse() {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = null;
        String urlPars = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
        try {
            URL url = new URL(urlPars);
            parser = factory.createParser(url);
            JsonToken jsonToken = parser.nextToken();
            while (!parser.isClosed()) {
                jsonToken = parser.nextToken();
                if (jsonToken.equals(JsonToken.END_ARRAY)) {
                    cancle();
                    return;
                }
                String fieldName = parser.getCurrentName();
                if (jsonToken.equals(JsonToken.START_OBJECT)) {
                    ParsInfo parsInfo = new ParsInfo();
                    while (!jsonToken.equals(JsonToken.END_OBJECT)) {
                        jsonToken = parser.nextToken();
                        fieldName = parser.getCurrentName();
                        if ("id".equals(fieldName)) {
                            parsInfo.setId(parser.getValueAsString());
                        }
                        if ("sourceDataUrl".equals(fieldName)) {
                            parsInfo.setSourceDataUrl(parser.getValueAsString());
                        }
                        if ("tokenDataUrl".equals(fieldName)) {
                            parsInfo.setTokenDataUrl(parser.getValueAsString());
                        }
                    }
                    queue.add(parsInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
