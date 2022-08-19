package jp.example.thread.task;

@AllArgsConstructor
public class Pooling implements Runnable {

    private Runnable task;
    private ExecutorService exec;
    private TaskModel taskModel;
    private int waitCount;
    private TaskTypeEnum taskType;

    public void run() {
        exec.execute(task);
        for(int i=0 ; i<this.waitCount ; i++) {
            System.out.println("Pooling : " + taskModel);
        }
        this.taskType.finish(taskModel);
    }
}
