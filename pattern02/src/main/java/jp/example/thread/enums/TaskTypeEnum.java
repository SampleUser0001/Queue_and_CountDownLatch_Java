package jp.example.thread.enums;

public enum TaskEnum {
    TaskA{
        @Override
        public void finish(TaskModel task){
            task.isFinishA(true);
        }
    },
    TaskB{
        @Override
        public void finish(TaskModel task){
            task.isFinishB(true);
        }
    }

    public abstract void finish(TaskModel task);

    private TaskEnum(){}
}
