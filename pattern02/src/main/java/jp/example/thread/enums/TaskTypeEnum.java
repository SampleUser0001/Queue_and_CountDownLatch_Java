package jp.example.thread.enums;

import jp.example.thread.model.TaskModel;

public enum TaskTypeEnum {
    TaskA() {
        @Override
        public void finish(TaskModel task){
            task.setFinishA(true);
        }
        @Override
        public boolean getFinishStatus(TaskModel task) {
            return task.isFinishA();
        }
    },
    TaskB() {
        @Override
        public void finish(TaskModel task){
            task.setFinishB(true);
        }
        @Override
        public boolean getFinishStatus(TaskModel task) {
            return task.isFinishB();
        }
    };

    public abstract void finish(TaskModel task);
    public abstract boolean getFinishStatus(TaskModel task);

    private TaskTypeEnum(){}
}
