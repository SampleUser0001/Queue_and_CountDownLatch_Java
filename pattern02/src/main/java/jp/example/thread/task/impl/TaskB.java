package jp.example.thread.task.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jp.example.thread.model.TaskModel;
import jp.example.thread.task.Task;

@Getter
public class TaskB extends Task {

    public boolean exec(){
        System.out.println("TaskB : start . " + this.taskModel);
        System.out.println("TaskB : finish . " + this.taskModel);
        return true;
    }

    public int execTime() {
        return 3;
    }

    @Builder
    public TaskB(TaskModel taskModel){
        super(taskModel);
    }
}
