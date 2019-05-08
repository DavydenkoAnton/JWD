package by.javatr.transport.repository;

import java.util.List;

public interface Repository<T> {

    List<T> find(Specification<T> spec);
}
