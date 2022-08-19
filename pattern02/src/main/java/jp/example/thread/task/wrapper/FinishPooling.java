package jp.example.thread.task.wrapper;

import lombok.Builder;
import lombok.Getter;
import jp.example.thread.task.Task;
import jp.example.thread.model.TaskModel;
import jp.example.thread.enums.TaskTypeEnum;

import java.util.Queue;

import java.util.concurrent.CountDownLatch;

@Getter
public class FinishPooling extends Pooling {

    private Queue<TaskModel> queue;
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        while(countDownLatch.getCount() != 0) {
            TaskModel taskModel = queue.poll();
            this.getTask().setTaskModel(taskModel);
            if(taskModel != null){
                this.taskExec();
                countDownLatch.countDown();
            }
        }
    }
    
    @Builder
    public FinishPooling(Task task, TaskTypeEnum taskType, Queue<TaskModel> queue, CountDownLatch countDownLatch) {
        super(task, taskType);
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }
}
