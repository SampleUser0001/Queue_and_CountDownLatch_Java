package jp.example.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.example.thread.model.TaskModel;
import jp.example.thread.task.TaskA;
import jp.example.thread.task.TaskB;

/**
 * Hello world!
 *
 */
public class App {

    public static final long WAIT_MILLISECOND = 500;

    public static final int THREAD_COUNT = 2;
    public static final int TASK_COUNT = 3;

    public void exec(String[] args) {
        System.out.println("start : " + new Date());
        ExecutorService taskAExce = Executors.newFixedThreadPool(1);
        ExecutorService taskBExce = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(TASK_COUNT);
        Queue<TaskModel> queueAtoB = new LinkedList<TaskModel>();
        try {
            for( int i=0; i<TASK_COUNT ; i++ ) {
                taskAExce.execute(new TaskA(new TaskModel(i, "value : " + i), queueAtoB, countDownLatch));
            }
            taskBExce.execute(new TaskB(queueAtoB, countDownLatch));

            taskAExce.shutdown();
            taskBExce.shutdown();

            countDownLatch.await();
        } catch(InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.println("finish : " + new Date());
        }

    }

    public static void main( String[] args ) {
        new App().exec(args);
    }
}
