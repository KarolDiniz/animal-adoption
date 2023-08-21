package br.edu.ifpb.dac.karoline.animaladoption.presentation.utilities;

import br.edu.ifpb.dac.karoline.animaladoption.presentation.utilities.PrintView;
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

    public String Scanner(){
        return scanner.nextLine();
    }

    public int getUserChoice (String prompt, String[]options){
        printView.print(prompt);
        printView.printMenu(options);
        return getUserInputInt("Enter your choice: ");
    }

    public int getUserInputInt (String prompt){
        printView.print(prompt);
        return scanner.nextInt();
    }
    public long getUserInputLong(String prompt) {
        printView.print(prompt);
        return scanner.nextLong();
    }

    public String getUserInput (String prompt){
        printView.print(prompt);
        return scanner.next();
    }
}
