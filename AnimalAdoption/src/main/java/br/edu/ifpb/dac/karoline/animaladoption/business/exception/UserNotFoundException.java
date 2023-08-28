package br.edu.ifpb.dac.karoline.animaladoption.business.exception;
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
