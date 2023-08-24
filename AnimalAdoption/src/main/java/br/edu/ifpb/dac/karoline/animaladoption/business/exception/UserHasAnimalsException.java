package br.edu.ifpb.dac.karoline.animaladoption.business.exception;

public class UserHasAnimalsException extends RuntimeException {
    public UserHasAnimalsException(String message) {
        super(message);
    }
}

