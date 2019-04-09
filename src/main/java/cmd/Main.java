package cmd;

import api.*;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void lineDrawler() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void screenStopper() {
        System.out.println("Press ENTER to go back");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static int getAnInteger() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.print("Give a valid number");
            }
        }
    }


    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.gameLoader();
        GameModelling gameModelling = new GameModelling(menu.getAllGame());
        boolean exit = false;
        int meta = 0;


        do {
            clearScreen();
            System.out.println("Select your choice:");
            System.out.println("1. List games by platform");
            System.out.println("2. List all game");
            System.out.println("3. Show favorite lists");
            System.out.println("4. Add a game to the list");
            System.out.println("5. Create a favorite list");
            System.out.println("6. Add game to a favorite list");
            System.out.println("7. Remove game from a list");
            System.out.println("8. Remove a favorite list");
            System.out.println("9. To Exit");
            Scanner sc = new Scanner(System.in);
            int choice = getAnInteger();
            switch (choice) {
                case 1:
                    clearScreen();
                    System.out.println("Select a platform:");
                    System.out.println("1: PS4");
                    System.out.println("2: Xbox");
                    System.out.println("3: Nintendo");
                    System.out.println("4: PC");
                    System.out.println("5: Crossplatform");
                    int platformSelect = getAnInteger();
                    if (platformSelect == 1) {
                        gameModelling.getGamesByPlatform("PS4game");
                    } else if (platformSelect == 2) {
                        gameModelling.getGamesByPlatform("XboxOneGame");
                    } else if (platformSelect == 3) {
                        gameModelling.getGamesByPlatform("NintendoGame");
                    } else if (platformSelect == 4) {
                        gameModelling.getGamesByPlatform("PCGame");
                    } else if (platformSelect == 5) {
                        gameModelling.getGamesByPlatform("Crossplatform");
                    }
                    screenStopper();
                    break;
                case 2:
                    clearScreen();
                    for (Game g : gameModelling.getGames()) {
                        lineDrawler();
                        System.out.println(g);
                    }
                    screenStopper();
                    break;
                case 3:
                    clearScreen();
                    if (gameModelling.getFavoritLists().isEmpty()) {
                        System.out.println("list not yet created");
                    } else {
                        for (FavoriteList f : gameModelling.getFavoritLists()) {
                            int i = 1;
                            System.out.println(i + "f");
                            i++;
                        }
                    }
                    screenStopper();
                    break;
                case 4:
                    clearScreen();
                    String access = "a";
                    String plat = "a";
                    String genre = "a";
                    List<String> atts = new ArrayList<>();
                    System.out.println("Give a name");
                    String name = sc.nextLine();
                    atts.add(name);
                    System.out.println("Give a developer");
                    String developer = sc.nextLine();
                    atts.add(developer);
                    System.out.println("Give a publisher");
                    String publisher = sc.nextLine();
                    atts.add(publisher);
                    System.out.println("Give a metacritic");
                    int metacritic = getAnInteger();
                    while (true) {
                        try {
                            System.out.println("Give a genre: ");
                            genre = sc.nextLine();
                            genre = genre.toUpperCase();
                            Genre genre1 = Genre.valueOf(genre);
                            genre = String.valueOf(genre1);
                            if (gameModelling.enumValidator(genre)) {
                                break;
                            } /*
                        } catch (InvalidEnumException e) {
                            System.out.println("Please try again.");
                            screenStopper();
                            continue; */
                        } catch (IllegalArgumentException e) {
                            System.out.println("Please try again.");
                            screenStopper();
                            continue;
                        }
                    }


                    do {
                        System.out.println("Select the accessibility:");
                        System.out.println("1. Disk");
                        System.out.println("2. Digital");
                        int subChoice = getAnInteger();
                        switch (subChoice) {
                            case 1:
                                access = "Disk";
                                atts.add(access);
                                break;
                            case 2:
                                do {
                                    System.out.println("Type in the downloadsize");
                                    int downsize = getAnInteger();
                                    access = String.valueOf(downsize);
                                    atts.add(access);
                                } while (access.equals("a"));
                                break;
                        }
                    } while (access.equals("a"));
                    do {
                        System.out.println("Select a platform");
                        System.out.println("1. PS4");
                        System.out.println("2. XboxOne");
                        System.out.println("3. Nintendo");
                        System.out.println("4. PC");
                        System.out.println("5. Crossplatform");
                        int subChoice = getAnInteger();
                        switch (subChoice) {
                            case 1:
                                plat = "PS4game";
                                break;
                            case 2:
                                plat = "XboxOneGame";
                                break;
                            case 3:
                                plat = "NintendoGame";
                                break;
                            case 4:
                                plat = "PCGame";
                                break;
                            case 5:
                                plat = "Crossplatform";
                                break;
                        }
                    } while (plat.equals("a"));
                    atts.add(plat);
                    gameModelling.addGame(name, developer, publisher, metacritic, genre, access, plat);
                    break;
                case 5:
                    clearScreen();
                    System.out.print("Give a name: ");
                    String newFavListName = sc.nextLine();
                    gameModelling.createFavoriteList(newFavListName);
                    screenStopper();
                    break;
                case 6:
                    clearScreen();
                    if (gameModelling.getFavoritLists().isEmpty()){
                        System.out.println("Favorite list not yet created!");
                        screenStopper();
                    }else {
                        System.out.println("Games:");
                        for (Game g : gameModelling.getGames()) {
                            lineDrawler();
                            System.out.println(g);
                        }
                        System.out.println();
                        System.out.println("Lists:");
                        for (FavoriteList fl : gameModelling.getFavoritLists()) {
                            System.out.println(fl.getName());
                        }
                        System.out.println("Type the name of a game:");
                        String addGame = sc.nextLine();
                        System.out.println("Type the name of a list:");
                        String usedList = sc.nextLine();
                        gameModelling.addGameToAFavlist(addGame, usedList);
                    }

                    break;
                case 7:
                    clearScreen();
                    for (FavoriteList fl : gameModelling.getFavoritLists()) {
                        System.out.println(fl.getName());
                        lineDrawler();
                        for (Game g : fl.getFavGames()) {
                            System.out.println(g);
                        }
                    }
                    System.out.println("Type the name of the game:");
                    String gameToDel = sc.nextLine();
                    System.out.println("Type the name of the list:");
                    String usedList = sc.nextLine();
                    gameModelling.removeFromFavoriteList(gameToDel, usedList);
                    break;
                case 8:
                    clearScreen();
                    if (gameModelling.getFavoritLists().isEmpty()) {
                        System.out.println("Favorite list not yet created");
                    } else {
                        int j = 1;
                        for (FavoriteList fl : gameModelling.getFavoritLists()) {
                            System.out.println(j + " " + fl.getName());
                            j++;
                        }
                        System.out.println("Give the number of the list that you want to delete: ");
                        int listToDelete = getAnInteger();
                        gameModelling.removeFavoriteList(listToDelete);
                    }

                    break;
                case 9:
                    exit = true;
                    break;
            }


        } while (!exit);
    }

}
