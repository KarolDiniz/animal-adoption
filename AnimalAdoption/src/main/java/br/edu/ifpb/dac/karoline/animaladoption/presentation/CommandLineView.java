package br.edu.ifpb.dac.karoline.animaladoption.presentation;

import br.edu.ifpb.dac.karoline.animaladoption.presentation.controller.AnimalController;
import br.edu.ifpb.dac.karoline.animaladoption.presentation.controller.UserController;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import br.edu.ifpb.dac.karoline.animaladoption.presentation.utilities.PrintView;
import br.edu.ifpb.dac.karoline.animaladoption.presentation.utilities.ScannerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineView implements CommandLineRunner {

    private final UserController userController;
    private final AnimalController animalController;
    private ScannerView scannerView;
    private PrintView printView;

    @Autowired
    public CommandLineView(UserController userController, AnimalController animalController, ScannerView scannerView, PrintView printView) {
        this.userController = userController;
        this.animalController = animalController;
        this.scannerView = scannerView;
        this.printView = printView;

    }

    @Override
    public void run(String... args) {
        int userTypeChoice = scannerView.getChoice("\n -=-=- Welcome to the \u001B[35mAnimal Adoption System\u001B[0m!-=-=-\n--------------------------------------------------\nAre you a user or an admin?", new String[]{"User", "Admin"});

        if (userTypeChoice == 1) {
            handleUser();
        } else if (userTypeChoice == 2) {
            handleAdmin();
        }
    }

    private void handleUser() {
        String username = scannerView.Input("\nEnter your username: ");
        User user = null;

        try {
            user = userController.findByUsername(username);
        } catch (IllegalArgumentException e) {
            printView.print("\u001B[31mUser not found.\u001B[0m");

            String createUserChoice = scannerView.Input("\n\u001B[33mDo you want to create a new user? (S/N): \u001B[0m ").toUpperCase();

            if (createUserChoice.equals("S")) {
                user = new User();
                user.setUsername(username);
                userController.createUser(user);
                printView.print("\u001B[32mUser '" + username + "' created successfully!\u001B[0m");
            } else {
                printView.print("\u001B[31mExiting...\u001B[0m");
                return;
            }
        }

        boolean exitMenu = false;
        while (!exitMenu) {
            String[] userOptions = {"View animals", "Adopt an animal", "View my animals", "Exit\n"};
            int userMenuChoice = scannerView.getChoice("\u001B[36m -=-=-[ Menu ]-=-=- \u001B[0m\n", userOptions);

            switch (userMenuChoice) {
                case 1:
                    displayAnimals();
                    break;
                case 2:
                    adoptAnimal(user);
                    break;
                case 3:
                    viewAllAnimalsWithUser(user.getId());
                    break;
                case 4:
                    exitMenu = true;
                    printView.print("\u001B[31mExiting...\u001B[0m");
                    break;
                default:
                    printView.print("\u001B[31mInvalid choice. Please select a valid option.\u001B[0m");
                    break;
            }
        }
    }

    private void handleAdmin() {
        String adminToken = scannerView.Input("Enter the admin token: ");

        if ("roleadmin".equals(adminToken)) {
            int adminMenuChoice;
            do {
                String[] adminOptions = {"Create animal", "Update animal", "View all animals", "Delete animal", "View all users", "Delete user", "Exit"};
                adminMenuChoice = scannerView.getChoice(" -=-=-[ Menu ]-=-=- ", adminOptions);

                switch (adminMenuChoice) {
                    case 1:
                        createAnimal();
                        break;
                    case 2:
                        updateAnimal();
                        break;
                    case 3:
                        viewAllAnimals();
                        break;
                    case 4:
                        deleteAnimal();
                        break;
                    case 5:
                        viewAllUsers();
                        break;
                    case 6:
                        deleteUser();
                        break;
                    case 7:
                        printView.print("\u001B[31mExiting...\u001B[0m");
                        break;
                    default:
                        printView.print("\u001B[31mInvalid choice. Please select a valid option.\u001B[0m");
                        break;
                }
            } while (adminMenuChoice != 7);
        } else {
            printView.print("\u001B[31m...Invalid admin-token.\u001B[0m");
        }
    }

    public void viewAllAnimalsWithUser(long id){
        List<Animal> userAnimals =   userController.getUserAnimals(id);

        if (userAnimals.isEmpty()) {
            printView.print("\u001B[31mYou don't have any animals associated with your ID.\u001B[0m");
        } else {
            printView.print("Animals associated with your ID:");
            for (Animal animal : userAnimals) {
                printView.print(animal.getId() + ". " + animal.getName() + " (" + animal.getSpecies() + ")");
            }
        }
    }


    private void viewAllUsers() {
        List<User> users = userController.getAllUsers();
        for (User user : users) {
            printView.print("User ID: " + user.getId() + "\nUsername: " + user.getUsername() + "\nAnimals associated with this user: ");
            viewAllAnimalsWithUser(user.getId());
            printView.print("-------------------\n");
        }
    }

    private void deleteUser() {
        Long userId = scannerView.getInputLong("Enter the ID of the user you want to delete: ");
        userController.deleteUser(userId);
        printView.print("User deleted successfully.");
    }

    private void createAnimal() {
        Animal animal = new Animal();
        animal.setName(scannerView.Input("Enter animal name: "));
        animal.setSpecies(scannerView.Input("Enter animal species: "));
        animal.setDescription(scannerView.Input("Enter animal description: "));

        animalController.createAnimal(animal);
        printView.print("Animal created successfully. ");
    }

    private void updateAnimal() {
        Long animalId = scannerView.getInputLong("Enter the ID of the animal you want to update: ");
        Animal animal = animalController.getAnimalById(animalId);

        if (animal != null) {
            animal.setName(scannerView.Input("Enter new animal name: "));
            animal.setSpecies(scannerView.Input("Enter new animal species: "));
            animal.setDescription(scannerView.Input("Enter new animal description: "));

            animalController.updateAnimal(animalId, animal);
            printView.print("Animal updated successfully.");
        } else {
            printView.print("Animal not found.");
        }
    }

        private void viewAllAnimals () {
            List<Animal> animals = animalController.getAllAnimals();
            animals.forEach(animal -> printView.print(animal.getId() + ". " + animal.getName() + " (" + animal.getSpecies() + ")"));
        }

        private void deleteAnimal () {
            Long animalId = scannerView.getInputLong("Enter the ID of the animal you want to delete: ");
            animalController.deleteAnimal(animalId);
            printView.print("\u001B[32mAnimal deleted successfully.\u001B[0m");
        }

        private void displayAnimals () {
            List<Animal> animals = animalController.getAllAnimals();
            if (animals.isEmpty()) {
                printView.print("\u001B[31mNo animals available for adoption.\u001B[0m");
            } else {
                printView.print("\u001B[36mList of available animals for adoption:\u001B[0m");
                animals.forEach(animal -> {
                    printView.print(animal.getId() + ". " + animal.getName() + " (" + animal.getSpecies() + ")");
                });
            }
        }

        private void adoptAnimal (User user) {
            displayAnimals();
            Long animalId = scannerView.getInputLong("Enter the ID of the animal you want to adopt: ");
            Animal animal = animalController.getAnimalById(animalId);

            if (animal != null && animal.getOwner() == null) {
                animal.setOwner(user);
                Animal updatedAnimal = animalController.updateAnimal(animalId, animal);

                if (updatedAnimal != null) {
                    printView.print("\u001B[32mCongratulations! You adopted " + updatedAnimal.getName() + ".\u001B[0m");
                } else {
                    printView.print("\u001B[31mFailed to adopt the animal.\u001B[0m");
                }
            } else {
                printView.print("\u001B[31mAnimal not found or already adopted.\u001B[0m");
            }
        }


}