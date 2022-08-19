package jp.example.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.example.thread.enums.TaskTypeEnum;
import jp.example.thread.model.TaskModel;
import jp.example.thread.task.impl.TaskA;
import jp.example.thread.task.impl.TaskB;
import jp.example.thread.task.wrapper.FinishPooling;
import jp.example.thread.task.wrapper.StartPooling;

/**
 * Hello world!
 *
 */
public class App {

    public static final long WAIT_MILLISECOND = 500;

    public static final int THREAD_COUNT = 100;
    public static final int TASK_COUNT = 2;

    public void exec(String[] args) throws InterruptedException {
        System.out.println("start : " + new Date());
        ExecutorService taskAExec = Executors.newFixedThreadPool(1);
        ExecutorService taskBExec = Executors.newFixedThreadPool(THREAD_COUNT);

        Queue<TaskModel> queueAToB = new LinkedList<TaskModel>();

        CountDownLatch countDownLatch = new CountDownLatch(TASK_COUNT);

        for(int i=0;i<TASK_COUNT;i++ ) {
            taskAExec.execute(
                StartPooling.builder()
                            .task(
                                TaskA.builder()
                                     .taskModel(new TaskModel(i, "value : " + i, false, false))
                                     .build())
                            .taskType(TaskTypeEnum.TaskA)
                            .queue(queueAToB)
                            .build()
            );
        }
        for(int i=0;i<THREAD_COUNT;i++){
            taskBExec.execute(
                FinishPooling.builder()
                             .task(
                                TaskB.builder().build())
                             .taskType(TaskTypeEnum.TaskB)
                             .queue(queueAToB)
                             .countDownLatch(countDownLatch)
                             .build()
            );
            
        }
        
        taskAExec.shutdown();
        taskBExec.shutdown();

        countDownLatch.await();


        System.out.println("finish : " + new Date());
    }

    public static void main( String[] args ) throws InterruptedException {
        new App().exec(args);
    }
}
