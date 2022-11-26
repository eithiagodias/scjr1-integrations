package br.com.fiap.agrodrones.microservice.api;

public class GetInfoException extends Exception {
    public GetInfoException() {
    }

    // Constructor that accepts a message
    public GetInfoException(String message) {
        super(message);
    }
}
