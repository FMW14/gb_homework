package com.vtb.javacourses.lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class DemoRecursiveTask extends RecursiveTask<Integer> {
    private int[] data;

    public DemoRecursiveTask(int[] data) {
        this.data = data;
    }

    private List<DemoRecursiveTask> createSubTask() {
        return new ArrayList<>(Arrays.asList(
                new DemoRecursiveTask(Arrays.copyOfRange(data, 0, data.length / 2)),
                new DemoRecursiveTask(Arrays.copyOfRange(data, data.length / 2, data.length))
        ));
    }

    @Override
    protected Integer compute() {
        if (this.data.length > 100_000) {
            List<DemoRecursiveTask> subtasks = createSubTask();

            for (DemoRecursiveTask subtask : subtasks) {
                subtask.fork();
            }

            int result = 0;

            for (DemoRecursiveTask subtask : subtasks) {
                result = subtask.join();
            }

            return result;
        } else {
            return IntStream.of(data).max().getAsInt();
        }
    }
}
