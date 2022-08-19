package jp.example.thread.task;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import jp.example.thread.App;
import jp.example.thread.model.TaskModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskB implements Runnable {

    private static final int WAIT_COUNT = 3;

    private Queue<TaskModel> queue;
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        TaskModel taskModel = null;
        while(countDownLatch.getCount() != 0){
            taskModel = queue.poll();
            if(taskModel != null){
                System.out.println(String.format("TaskB : id = %d start",taskModel.getId()));
                try {
                    for(int i=0;i<WAIT_COUNT;i++){
                        System.out.println(String.format("TaskB : id = %d running.", taskModel.getId()));
                        Thread.sleep(App.WAIT_MILLISECOND);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                    System.out.println(String.format("TaskB : id = %d finish . CountDown : %d",taskModel.getId(), countDownLatch.getCount()));
                }
            }    
        }
       
        
    }



}
