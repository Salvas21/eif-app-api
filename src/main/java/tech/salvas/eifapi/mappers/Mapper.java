package tech.salvas.eifapi.mappers;

public interface Mapper<T, U> {
    U toDTO(T t);

    T toEntity(U u);
}
