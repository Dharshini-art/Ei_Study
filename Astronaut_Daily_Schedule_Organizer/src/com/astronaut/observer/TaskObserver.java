package com.astronaut.observer;

import com.astronaut.model.Task;

public interface TaskObserver {
    void onTaskConflict(String message, Task conflictingTask);
}