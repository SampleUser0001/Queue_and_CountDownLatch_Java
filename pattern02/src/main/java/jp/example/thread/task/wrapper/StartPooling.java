package jp.example.thread.task.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jp.example.thread.enums.TaskTypeEnum;
import jp.example.thread.model.TaskModel;
import jp.example.thread.task.Task;

import java.util.Queue;

@Getter
public class StartPooling extends Pooling {

    private Queue<TaskModel> queue;

    @Override
    public void run() {
        this.taskExec();
        queue.add(this.getTask().getTaskModel());
    }

    @Builder
    public StartPooling(Task task, TaskTypeEnum taskType, Queue<TaskModel> queue) {
        super(task, taskType);
        this.queue = queue;
    }
    
}
