package tech.salvas.eifapi.mappers;

public interface Mapper<T, U> {
    public U toDTO(T t);
    public T toEntity(U u);
}
