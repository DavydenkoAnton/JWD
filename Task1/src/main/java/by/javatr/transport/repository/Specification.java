package by.javatr.transport.repository;

public interface Specification<T> {
    boolean match(T bean);
}
