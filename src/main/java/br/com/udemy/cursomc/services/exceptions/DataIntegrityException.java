package br.com.udemy.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {
    private static final long serialVersionUID = -3793916470562252567L;

    public DataIntegrityException(String msg){
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause){
        super(msg, cause);
    }
}
