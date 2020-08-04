package com.vtb.javacourses.lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DemoRecursiveTask extends RecursiveTask<Integer> {
    private final int[] data;
    private final int start;
    private final int end;

    public DemoRecursiveTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    private List<DemoRecursiveTask> createSubTask(int offset) {
        return new ArrayList<>(Arrays.asList(
                new DemoRecursiveTask(data, start, start + offset),
                new DemoRecursiveTask(data, start + offset, end)
        ));
    }

    private int getMaxFromRange(int startIndex, int endIndex) {
        int maxElement = Integer.MIN_VALUE;
        for (int i = startIndex; i < endIndex; i++) {
            if (maxElement < data[i]) {
                maxElement = data[i];
            }
        }

        return maxElement;
    }

    @Override
    protected Integer compute() {
        int curLength = end - start;

        int offset = curLength / 2;
        if (curLength > 100_000) {
            List<DemoRecursiveTask> subtasks = createSubTask(offset);

            for (DemoRecursiveTask subtask : subtasks) {
                subtask.fork();
            }

            int result = 0;

            for (DemoRecursiveTask subtask : subtasks) {
                result = subtask.join();
            }

            return result;
        } else {
            return getMaxFromRange(start, end);
        }
    }
}
