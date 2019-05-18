package by.javatr.transport.repository;

import java.util.List;

public interface Repository<T> {

    List<T> find(Specification<T> spec);

    List<T> sort(Specification<T> spec);

}
