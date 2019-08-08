package by.davydenko.petbook.service.validator;

public interface Validator <T> {
    boolean valid(T obj);
}
