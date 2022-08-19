package jp.example.thread.task;

import jp.example.thread.model.TaskModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Task {
    protected TaskModel taskModel;

    public abstract boolean exec();
    public abstract int execTime();

}
