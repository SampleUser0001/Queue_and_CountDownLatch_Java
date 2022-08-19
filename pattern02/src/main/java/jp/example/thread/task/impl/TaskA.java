package jp.example.thread.task.impl;

import lombok.Builder;
import lombok.Getter;
import jp.example.thread.model.TaskModel;
import jp.example.thread.task.Task;

@Getter
public class TaskA extends Task {

    public boolean exec(){
        System.out.println("TaskA : start . " + this.taskModel);
        System.out.println("TaskA : finish . " + this.taskModel);
        return true;
    }

    public int execTime() {
        return 2;
    }

    @Builder
    public TaskA (TaskModel taskModel){
        super(taskModel);
    }

}
