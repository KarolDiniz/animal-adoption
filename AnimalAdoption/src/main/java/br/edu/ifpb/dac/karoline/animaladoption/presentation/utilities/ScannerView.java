package br.edu.ifpb.dac.karoline.animaladoption.presentation.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerView {

    private Scanner scanner;

    private PrintView printView;

    @Autowired
    public ScannerView(Scanner scanner, PrintView printView) {
        this.scanner = scanner;
        this.printView = printView;
    }

    public int getChoice(String prompt, String[]options){
        printView.print(prompt);
        printView.printMenu(options);
        return getInputInt("Enter your choice: ");
    }

    public int getInputInt(String prompt){
        printView.print(prompt);
        return scanner.nextInt();
    }
    public long getInputLong(String prompt) {
        printView.print(prompt);
        return scanner.nextLong();
    }

    public String Input(String prompt){
        printView.print(prompt);
        return scanner.next();
    }
}
