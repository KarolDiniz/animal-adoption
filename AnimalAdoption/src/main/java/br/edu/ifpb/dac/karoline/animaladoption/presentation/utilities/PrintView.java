package br.edu.ifpb.dac.karoline.animaladoption.presentation.utilities;

import org.springframework.stereotype.Component;

@Component
public class PrintView {

    public void print(String mensagem){
        System.out.println(mensagem);
    }

    public void printMenu (String[]options){
        for (int i = 0; i < options.length; i++) {
            System.out.println("[" +(i + 1) + "] - " + options[i]);
        }
    }
}
