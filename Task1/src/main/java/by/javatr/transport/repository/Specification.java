package by.javatr.transport.repository;

import by.javatr.transport.entity.TrainPassenger;

import java.util.List;

public interface Specification<T> {
    boolean match(T bean);

}
