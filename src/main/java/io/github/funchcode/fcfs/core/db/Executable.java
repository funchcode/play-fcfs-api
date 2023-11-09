package io.github.funchcode.fcfs.core.db;

@FunctionalInterface
public interface Executable<T> {

    void execute(T domain);

}
