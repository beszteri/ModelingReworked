package cmd;

import Exceptions.AlreadyAddedToTheListException;
import Exceptions.ListAlreadyCreatedException;
import Exceptions.NoSuchGameException;
import Exceptions.NoSuchListException;
import api.*;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner sc = new Scanner(System.in);

    private static void lineDrawler() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void screenStopper() {
        System.out.println("Press ENTER to go back");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static int getAnInteger() {
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
        boolean exit = false;
        GameModelling gameModelling = new GameModelling(menu.getAllGame());
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
            System.out.println("9. Show games from favorite lists");
            System.out.println("0. To Exit");
            Scanner sc = new Scanner(System.in);
            int choice = getAnInteger();
            switch (choice) {
                case 1:
                    listGameByPlatform(gameModelling);
                    break;
                case 2:
                    showAllGame(gameModelling);
                    break;
                case 3:
                    showFavoriteList(gameModelling);
                    break;
                case 4:
                    handleAddGame(gameModelling, sc);
                    break;
                case 5:

                    createFavList(gameModelling, sc);
                    break;
                case 6:
                    addGameToAFavList(gameModelling, sc);
                    break;
                case 7:
                    removeGameFromAFavList(gameModelling, sc);
                    break;
                case 8:
                    removeAFavList(gameModelling);
                    break;
                case 9:
                    showGamesFromFavList(gameModelling);
                    break;
                case 0:
                    exit = true;
                    break;
            }


        } while (!exit);
    }

    private static void showGamesFromFavList(GameModelling gameModelling) {
        for (FavoriteList fl : gameModelling.getFavoritLists()){
            System.out.println("");
            System.out.println(fl.getName());
            lineDrawler();
            for(Game g : fl.getFavGames()){
                System.out.println(g.getName());
            }
        }
        lineDrawler();
    }

    private static void removeAFavList(GameModelling gameModelling) {
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
    }

    private static void removeGameFromAFavList(GameModelling gameModelling, Scanner sc) {
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
    }

    private static void addGameToAFavList(GameModelling gameModelling, Scanner sc) {
        clearScreen();
        if (gameModelling.getFavoritLists().isEmpty()) {
            System.out.println("Favorite list not yet created!");
            screenStopper();
        } else {
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

            try {
                gameModelling.addGameToAFavlist(addGame, usedList);
            } catch (AlreadyAddedToTheListException | NoSuchGameException e) {
                System.out.println("No such game or already added to the list");
            } catch (NoSuchListException e) {
                System.out.println("No such list");
            } catch (MetacriticException e) {
                System.out.println("Wrong list");
            }
        }
    }

    private static void createFavList(GameModelling gameModelling, Scanner sc) {
        clearScreen();
        System.out.print("Give a name: ");
        String newFavListName = sc.nextLine();
        System.out.println("1. Games with good reviews");
        System.out.println("2. Games with bad reviews");
        int listPicker = getAnInteger();
        switch (listPicker) {
            case 1:
                try {
                    gameModelling.createFavoriteList(newFavListName, true);
                } catch (ListAlreadyCreatedException e) {
                    System.out.println("List already created");
                }
                screenStopper();
                break;
            case 2:
                try {
                    gameModelling.createFavoriteList(newFavListName, false);
                } catch (ListAlreadyCreatedException e) {
                    System.out.println("List already created");
                }
                screenStopper();
                break;
        }
    }

    private static void showFavoriteList(GameModelling gameModelling) {
        clearScreen();
        if (gameModelling.getFavoritLists().isEmpty()) {
            System.out.println("list not yet created");
        } else {
            int i = 1;
            for (FavoriteList f : gameModelling.getFavoritLists()) {
                System.out.println(i + " " + f.getName());
                i++;
            }
        }
        screenStopper();
    }

    private static void listGameByPlatform(GameModelling gameModelling) {
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
        for (Game g : gameModelling.getTempList()) {
            System.out.println(g.getName());
        }
        gameModelling.getTempList().clear();
        screenStopper();
    }

    private static void showAllGame(GameModelling gameModelling) {
        clearScreen();
        for (Game g : gameModelling.getGames()) {
            lineDrawler();
            System.out.println(g);
        }
        screenStopper();
    }

    private static void handleAddGame(GameModelling gameModelling, Scanner sc) {
        clearScreen();
        String access = "a";
        String plat = "a";
        Genre genre;
        System.out.println("Give a name");
        String name = sc.nextLine();
        System.out.println("Give a developer");
        String developer = sc.nextLine();
        System.out.println("Give a publisher");
        String publisher = sc.nextLine();
        System.out.println("Give a metacritic");
        int metacritic = getAnInteger();
        System.out.println("Give a genre: ");
        genre = Genre.valueOf(sc.nextLine().toUpperCase());
        do {
            System.out.println("Select the accessibility:");
            System.out.println("1. Disk");
            System.out.println("2. Digital");
            int subChoice = getAnInteger();
            switch (subChoice) {
                case 1:
                    access = "Disk";
                    break;
                case 2:
                    do {
                        System.out.println("Type in the downloadsize");
                        int downsize = getAnInteger();
                        access = String.valueOf(downsize);
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
        gameModelling.addGame(name, developer, publisher, metacritic, genre, access, plat);
    }
}
