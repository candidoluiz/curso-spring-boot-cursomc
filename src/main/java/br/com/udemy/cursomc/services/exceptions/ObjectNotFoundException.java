package br.com.udemy.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3793916470562252567L;

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
