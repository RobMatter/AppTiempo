package com.example.apptiempo.utils;

// Clase genérica para encapsular los datos junto con su estado (éxito, error, cargando).
// Es muy útil en arquitecturas como MVVM para comunicar el estado de una operación desde el repositorio a la UI.
public class Resource<T> {

    // Los datos obtenidos (si la operación fue exitosa).
    public final T data;
    // El mensaje de error (si la operación falló).
    public final String message;
    // El estado de la operación.
    public final Status status;

    // Constructor privado para crear instancias de Resource a través de los métodos estáticos.
    private Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    // Crea un recurso en estado de éxito con los datos correspondientes.
    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    // Crea un recurso en estado de error con un mensaje y opcionalmente con datos.
    public static <T> Resource<T> error(String msg, T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    // Crea un recurso en estado de carga, opcionalmente con datos.
    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    // Enumeración que define los posibles estados de una operación de carga de datos.
    public enum Status { SUCCESS, ERROR, LOADING }
}
