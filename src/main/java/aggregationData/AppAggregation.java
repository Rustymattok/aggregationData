package aggregationData;

import java.util.concurrent.*;

/**
 * Application for pars url and sorted info to other file.
 */
public class AppAggregation {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        BlockingQueue<ParsInfo> queue = new PriorityBlockingQueue<>();
        BlockingQueue<String> queueSave = new PriorityBlockingQueue<>();
        JsonParsThread jsonParsThread = new JsonParsThread(queue);
        JsonAggregationThread jsonAggregationThread = new JsonAggregationThread(queue,queueSave,jsonParsThread);
        FileInputThread fileInputThread = new FileInputThread(queueSave);
        // создаем 3 future таска для 3х callable объектов
        FutureTask<String> futureTask1 = new FutureTask<String>(jsonParsThread);
        FutureTask<String> futureTask2 = new FutureTask<String>(jsonAggregationThread);
        FutureTask<String> futureTask3 = new FutureTask<String>(fileInputThread);
        // екзекьютор с размером пула 3 потока
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // стартуем
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        executor.execute(futureTask3);
        // выполняем в бесконечном цикле, пока
        // executor service не закончит выполнение всех future тасков
        while (true) {
            if(futureTask1.isDone() && futureTask2.isDone() && futureTask3.isDone()){
                System.out.println("Done");
                // заканчиваем работу executor service
                executor.shutdown();
                return;
            }
        }
    }
}
