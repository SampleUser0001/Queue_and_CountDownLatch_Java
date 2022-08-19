package jp.example.thread.task.wrapper;

import jp.example.thread.enums.TaskTypeEnum;
import jp.example.thread.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Pooling implements Runnable {
    protected Task task;
    protected TaskTypeEnum taskType;

    public boolean taskExec() {
        this.task.exec();
        for(int i=0 ; i<this.task.execTime() ; i++) {
            System.out.println("Pooling : " + this.task.getTaskModel());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.taskType.finish(this.task.getTaskModel());
        System.out.println("Pooling Finish : " + this.task.getTaskModel());
        return this.taskType.getFinishStatus(this.task.getTaskModel());
    }

}
