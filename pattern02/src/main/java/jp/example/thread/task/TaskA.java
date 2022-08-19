package jp.example.thread.task;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import jp.example.thread.App;
import jp.example.thread.model.TaskModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskA implements Runnable {

    private static final int WAIT_COUNT = 2;

    private TaskModel taskModel;
    private Queue<TaskModel> queue;
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        System.out.println(String.format("TaskA : id = %d start",taskModel.getId()));
        try {
            for(int i=0;i<WAIT_COUNT;i++){
                System.out.println(String.format("TaskA : id = %d running.", taskModel.getId()));
                Thread.sleep(App.WAIT_MILLISECOND);
            }
            queue.add(taskModel);
        } catch (InterruptedException e){
            e.printStackTrace();
            countDownLatch.countDown();
        } finally {
            System.out.println(String.format("TaskA : id = %d finish",taskModel.getId()));
        }
    }

}
