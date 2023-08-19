package br.edu.ifpb.dac.karoline.animaladoption.presentation;

import br.edu.ifpb.dac.karoline.animaladoption.business.controller.AnimalController;
import br.edu.ifpb.dac.karoline.animaladoption.business.controller.UserController;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineApp implements CommandLineRunner {

    private final UserController userController;
    private final AnimalController animalController;
    private final Scanner scanner;

    public CommandLineApp(UserController userController, AnimalController animalController) {
        this.userController = userController;
        this.animalController = animalController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) {
        int userTypeChoice = getUserChoice("\n -=-=- Welcome to the \u001B[35mAnimal Adoption System\u001B[0m!-=-=-\n--------------------------------------------------\nAre you a user or an admin?", new String[]{"User", "Admin"});

        if (userTypeChoice == 1) {
            handleUser();
        } else if (userTypeChoice == 2) {
            handleAdmin();
        }
        scanner.close();
    }

    private void handleUser() {
        String username = getUserInput("\nEnter your username: ");
        User user = null;

        try {
            user = userController.findByUsername(username);
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[31mUser not found.\u001B[0m");

            String createUserChoice = getUserInput("\n\u001B[33mDo you want to create a new user? (S/N): \u001B[0m ").toUpperCase();

            if (createUserChoice.equals("S")) {
                user = new User();
                user.setUsername(username);
                userController.createUser(user);
                System.out.println("\u001B[32mUser '" + username + "' created successfully!\u001B[0m");
            } else {
                System.out.println("\u001B[31mExiting...\u001B[0m");
                return;
            }
        }

        boolean exitMenu = false;
        while (!exitMenu) {
            String[] userOptions = {"View animals", "Adopt an animal", "View my animals", "Exit\n"};
            int userMenuChoice = getUserChoice("\u001B[36m -=-=-[ Menu ]-=-=- \u001B[0m\n", userOptions);

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
                    System.out.println("\u001B[31mExiting...\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please select a valid option.\u001B[0m");
                    break;
            }
        }
    }

    private void handleAdmin() {
        String adminToken = getUserInput("Enter the admin token: ");

        if ("roleadmin".equals(adminToken)) {
            int adminMenuChoice;
            do {
                String[] adminOptions = {"Create animal", "Update animal", "View all animals", "Delete animal", "View all users", "Delete user", "Exit"};
                adminMenuChoice = getUserChoice(" -=-=-[ Menu ]-=-=- ", adminOptions);

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
                        System.out.println("\u001B[31mExiting...\u001B[0m");
                        break;
                    default:
                        System.out.println("\u001B[31mInvalid choice. Please select a valid option.\u001B[0m");
                        break;
                }
            } while (adminMenuChoice != 7);
        } else {
            System.out.println("\u001B[31m...Invalid admin-token.\u001B[0m");
        }
    }

    public void viewAllAnimalsWithUser(long id){
        List<Animal> userAnimals =   userController.getUserAnimals(id);

        if (userAnimals.isEmpty()) {
            System.out.println("\u001B[31mYou don't have any animals associated with your ID.\u001B[0m");
        } else {
            System.out.println("Animals associated with your ID:");
            for (Animal animal : userAnimals) {
                System.out.println(animal.getId() + ". " + animal.getName() + " (" + animal.getSpecies() + ")");
            }
        }
    }


    private void viewAllUsers() {
        List<User> users = userController.getAllUsers();
        for (User user : users) {
            System.out.println("User ID: " + user.getId() + "\nUsername: " + user.getUsername() + "\nAnimals associated with this user: ");
            viewAllAnimalsWithUser(user.getId());
            System.out.println("-------------------\n");
        }
    }

    private void deleteUser() {
        Long userId = getUserInputLong("Enter the ID of the user you want to delete: ");
        userController.deleteUser(userId);
        System.out.println("User deleted successfully.");
    }

    private void createAnimal() {
        Animal animal = new Animal();

        animal.setName(getUserInput("Enter animal name: "));
        animal.setSpecies(getUserInput("Enter animal species: "));
        animal.setDescription(getUserInput("Enter animal description: "));

        animalController.createAnimal(animal);
        System.out.println("Animal created successfully. ");
    }

    private void updateAnimal() {
        Long animalId = getUserInputLong("Enter the ID of the animal you want to update: ");
        Animal animal = animalController.getAnimalById(animalId);

        if (animal != null) {
            animal.setName(getUserInput("Enter new animal name: "));
            animal.setSpecies(getUserInput("Enter new animal species: "));
            animal.setDescription(getUserInput("Enter new animal description: "));

            animalController.updateAnimal(animalId, animal);
            System.out.println("Animal updated successfully.");
        } else {
            System.out.println("Animal not found.");
        }
    }

        private void viewAllAnimals () {
            List<Animal> animals = animalController.getAllAnimals();
            animals.forEach(animal -> System.out.println(animal.getId() + ". " + animal.getName() + " (" + animal.getSpecies() + ")"));
        }

        private void deleteAnimal () {
            Long animalId = getUserInputLong("Enter the ID of the animal you want to delete: ");
            animalController.deleteAnimal(animalId);
            System.out.println("\u001B[32mAnimal deleted successfully.\u001B[0m");
        }

        private void displayAnimals () {
            List<Animal> animals = animalController.getAllAnimals();

            if (animals.isEmpty()) {
                System.out.println("\u001B[31mNo animals available for adoption.\u001B[0m");
            } else {
                System.out.println("\u001B[36mList of available animals for adoption:\u001B[0m");
                animals.forEach(animal -> {
                    System.out.println(animal.getId() + ". " + animal.getName() + " (" + animal.getSpecies() + ")");
                });
            }
        }

        private void adoptAnimal (User user) {
            displayAnimals();

            Long animalId = getUserInputLong("Enter the ID of the animal you want to adopt: ");
            Animal animal = animalController.getAnimalById(animalId);

            if (animal != null && animal.getOwner() == null) {
                animal.setOwner(user);
                Animal updatedAnimal = animalController.updateAnimal(animalId, animal);

                if (updatedAnimal != null) {
                    System.out.println("\u001B[32mCongratulations! You adopted " + updatedAnimal.getName() + ".\u001B[0m");
                } else {
                    System.out.println("\u001B[31mFailed to adopt the animal.\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mAnimal not found or already adopted.\u001B[0m");
            }
        }

        private void printMenu (String[]options){
            for (int i = 0; i < options.length; i++) {
                System.out.println("[" +(i + 1) + "] - " + options[i]);
            }
        }
        private String getUserInput (String prompt){
            System.out.print(prompt);
            return scanner.next();
        }
        private int getUserChoice (String prompt, String[]options){
            System.out.println(prompt);
            printMenu(options);
            return getUserInputInt("Enter your choice: ");
        }
        private int getUserInputInt (String prompt){
            System.out.print(prompt);
            return scanner.nextInt();
        }
        private long getUserInputLong(String prompt) {
            System.out.print(prompt);
            return scanner.nextLong();
        }

}